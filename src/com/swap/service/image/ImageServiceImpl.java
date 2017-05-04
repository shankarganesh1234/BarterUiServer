package com.swap.service.image;

import java.io.InputStream;
import java.util.Map;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.swap.common.error.ErrorEnum;
import com.swap.common.exceptions.SwapException;
import com.swap.dao.image.ImageDao;
import com.swap.dao.item.ItemDao;
import com.swap.entity.item.ImageEntity;
import com.swap.entity.item.ItemEntity;
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

	@Override
	public ImageEntity uploadImage(InputStream uploadedInputStream, String uploadedFileLocation, Long itemId) {
		ImageEntity imageEntity = null;
		try {
			// save it
			imageTransformer.writeToFile(uploadedInputStream, uploadedFileLocation);

			// upload to cloudinary
			Map<String, Object> map = imageTransformer.uploadImageToCloudinary(uploadedFileLocation);

			// create image entity
			imageEntity = imageTransformer.createImageEntity(map);
			ItemEntity itemEntity = new ItemEntity();
			itemEntity.setItemId(itemId);
			
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
	
	public void updateItemTableWithImage(Long itemId, ImageEntity imageEntity) {
		try {
			// update image_id in item table with item id
			listingDao.updateListingForImage(itemId, imageEntity);
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

	@Override
	@Transactional
	public void createImage(InputStream uploadedInputStream, FormDataContentDisposition fileDetail, Long itemId) {
		String uploadedFileLocation = "/tmp/bartery/images/" + fileDetail.getFileName();
		ImageEntity imageEntity = uploadImage(uploadedInputStream, uploadedFileLocation, itemId);
		updateItemTableWithImage(itemId, imageEntity);
	}
}
