package com.swap.service.image;

import java.io.InputStream;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import com.swap.entity.item.ImageEntity;

public interface ImageService {
	ImageEntity uploadImage(InputStream inputStream, String uploadedFileLocation, Long itemId);

	void updateItemTableWithImage(Long itemId, ImageEntity imageEntity);

	void createImage(InputStream uploadedInputStream, FormDataContentDisposition fileDetail, Long itemId);
}
