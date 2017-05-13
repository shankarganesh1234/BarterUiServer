package com.swap.service.image;

import java.io.InputStream;
import java.util.List;

import org.glassfish.jersey.media.multipart.FormDataBodyPart;

import com.swap.entity.item.ImageEntity;

public interface ImageService {
	ImageEntity uploadImageToCloudinaryAndDb(InputStream inputStream, String uploadedFileLocation, Long itemId);

	void createImages(FormDataBodyPart body, Long itemId);
	
	boolean deleteImage(String publicImageId) throws Exception ;
	
	List<ImageEntity> uploadImages(FormDataBodyPart body, Long itemId);
	
	void updateItemStage(Long itemId, List<ImageEntity> images);
	
}
