package com.swap.transformer.image;

import java.io.InputStream;
import java.util.Map;

import com.swap.entity.listing.ImageEntity;

public interface ImageTransformer {
	public void writeToFile(InputStream uploadedInputStream, String uploadedFileLocation);

	public Map<String, Object> uploadImageToCloudinary(String filename);
	
	public ImageEntity createImageEntity(Map<String, Object> map);
}
