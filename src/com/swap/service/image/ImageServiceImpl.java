package com.swap.service.image;

import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.glassfish.jersey.media.multipart.BodyPart;
import org.glassfish.jersey.media.multipart.ContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.swap.client.CloudinaryClient;
import com.swap.common.error.ErrorEnum;
import com.swap.common.exceptions.SwapException;
import com.swap.dao.image.ImageDao;
import com.swap.dao.item.ItemDao;
import com.swap.entity.item.ImageEntity;
import com.swap.transformer.image.ImageTransformer;
import com.swap.transformer.listing.ItemTransformer;

@Service
public class ImageServiceImpl implements ImageService {
	private static final Logger logger = Logger.getLogger(ImageServiceImpl.class);

	@Inject
	ImageTransformer imageTransformer;

	@Inject
	ItemTransformer listingTransformer;

	@Inject
	private ItemDao listingDao;

	@Inject
	private ImageDao imageDao;

	@Inject
	CloudinaryClient cloudinaryClient;

	@Override
	public ImageEntity uploadImageToCloudinaryAndDb(InputStream uploadedInputStream, String uploadedFileLocation, Long itemId) {
		ImageEntity imageEntity = null;
		try {
			// save it
			imageTransformer.writeToFile(uploadedInputStream, uploadedFileLocation);

			// upload to cloudinary
			Map<String, Object> map = imageTransformer.uploadImageToCloudinary(uploadedFileLocation);

			// create image entity
			imageEntity = imageTransformer.createImageEntity(map);
			imageEntity.setItem(itemId);
			// save to image table
			imageEntity = imageDao.createImage(imageEntity);

		} catch (SwapException ex) {
			logger.error(ex);
			throw ex;
		} catch (HibernateException ex) {
			logger.error(ex);
			throw new SwapException(ErrorEnum.GET_IMAGE_FAILURE);
		} catch (Exception ex) {
			logger.error(ex);
			throw new SwapException(ErrorEnum.GET_IMAGE_FAILURE);
		}
		return imageEntity;
	}

	@Override
	public boolean deleteImage(String publicImageId) throws Exception {

		if (publicImageId == null)
			return false;

		boolean result = false;
		Cloudinary cloudinary = cloudinaryClient.getCloudinary();
		Map<String, Object> deleteResult;

		deleteResult = (Map<String, Object>) cloudinary.uploader().destroy(publicImageId, ObjectUtils.emptyMap());
		if (deleteResult != null) {
			String cloudinaryResult = String.valueOf(deleteResult.get("result"));
			if (cloudinaryResult != null && cloudinaryResult.equals("ok")) {
				result = true;
			}
		}
		return result;
	}

	@Override
	@Transactional
	public void createImages(FormDataBodyPart body, Long itemId) {
		// uplooad to cloudinary and db
		List<ImageEntity> images = uploadImages(body, itemId);
		// update item stage to published
		updateItemStage(itemId, images);
	}
	
	@Override
	public List<ImageEntity> uploadImages(FormDataBodyPart body, Long itemId) {
		List<ImageEntity> images = new LinkedList<>();
		for (BodyPart part : body.getParent().getBodyParts()) {
			InputStream inputStream = part.getEntityAs(InputStream.class);
			ContentDisposition fileDetail = part.getContentDisposition();
			if (StringUtils.isNotBlank(fileDetail.getFileName())) {
				String uploadedFileLocation = "/tmp/bartery/images/" + fileDetail.getFileName();
				// add to cloudinary and save in db
				images.add(uploadImageToCloudinaryAndDb(inputStream, uploadedFileLocation, itemId));
			}	
		}
		return images;
	}
	/**
	 * 
	 * @param itemId
	 * @param imageEntity
	 */
	@Override
	public void updateItemStage(Long itemId, List<ImageEntity> images) {
		try {
			// update item stage in item table with item id
			listingDao.updateListingForImage(itemId, images);
		} catch (SwapException ex) {
			logger.error(ex);
			throw ex;
		} catch (HibernateException ex) {
			logger.error(ex);
			throw new SwapException(ErrorEnum.GET_IMAGE_FAILURE);
		} catch (Exception ex) {
			logger.error(ex);
			throw new SwapException(ErrorEnum.GET_IMAGE_FAILURE);
		}
	}
}
