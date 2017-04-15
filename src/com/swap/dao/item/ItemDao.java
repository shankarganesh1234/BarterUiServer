package com.swap.dao.item;

import java.util.List;

import com.swap.entity.item.ImageEntity;
import com.swap.entity.item.ItemEntity;

public interface ItemDao {
	ItemEntity getListingByItemId(ItemEntity listingEntity);

	List<ItemEntity> getListingsByUserId(ItemEntity listingEntity);

	void createListing(ItemEntity listingEntity);

	void updateListing(ItemEntity listingEntity);

	void deleteListing(ItemEntity listingEntity);
	
	List<Long> getListingIdsByUserId(String userId);
	
	void updateListingForImage(Long itemId, ImageEntity imageEntity);
}
