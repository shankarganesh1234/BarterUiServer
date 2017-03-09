package com.swap.service.image;

import java.io.InputStream;

import com.swap.entity.listing.ImageEntity;

public interface ImageService {
	ImageEntity uploadImage(InputStream inputStream, String uploadedFileLocation, Long itemId);

	void updateItemTableWithImage(Long itemId, ImageEntity imageEntity);
}
