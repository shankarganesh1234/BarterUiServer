package com.swap.transformer.image;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.swap.client.CloudinaryClient;
import com.swap.common.CommonUtil;
import com.swap.entity.item.ImageEntity;

@Service
public class ImageTransformerImpl implements ImageTransformer {
	@Inject
	CloudinaryClient cloudinaryClient;

	@Override
	public void writeToFile(InputStream uploadedInputStream, String uploadedFileLocation) {
		try {
			OutputStream out = FileUtils.openOutputStream(new File(uploadedFileLocation));
			int read = 0;
			byte[] bytes = new byte[1024];

			out = new FileOutputStream(new File(uploadedFileLocation));
			while ((read = uploadedInputStream.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> uploadImageToCloudinary(String filename) {
		Map<String, Object> uploadResult = null;
		if (filename == null)
			return uploadResult;

		Cloudinary cloudinary = cloudinaryClient.getCloudinary();
		File file = new File(filename);
		try {
			uploadResult = (Map<String, Object>) cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
		} catch (IllegalStateException | IOException e1) {
			e1.printStackTrace();
		}
		return uploadResult;
	}

	@Override
	public ImageEntity createImageEntity(Map<String, Object> map) {
		ImageEntity imageEntity = new ImageEntity();
		imageEntity.setPublic_id((String)map.get("public_id"));
		imageEntity.setVersion(Integer.toString((Integer) map.get("version")));
		imageEntity.setSignature((String) map.get("signature"));
		imageEntity.setWidth(Integer.toString((Integer) map.get("width")));
		imageEntity.setHeight(Integer.toString((Integer) map.get("height")));
		imageEntity.setFormat((String) map.get("format"));
		imageEntity.setResource_type((String) map.get("resource_type"));
		imageEntity.setUpsertDate(CommonUtil.getCurrentDate());
		imageEntity.setBytes((Integer) map.get("bytes"));
		imageEntity.setUrl((String) map.get("url"));
		imageEntity.setSecure_url((String) map.get("secure_url"));
		return imageEntity;
	}
	
}
