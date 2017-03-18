package com.swap.dao.image;

import java.util.List;

import com.swap.entity.item.ImageEntity;

public interface ImageDao {
	ImageEntity createImage(ImageEntity image);

	void updateImage(ImageEntity image);

	void deleteImage(ImageEntity image);

	ImageEntity getImageByItemId(ImageEntity image);

	List<ImageEntity> getImagesByItemId(ImageEntity image);
}
