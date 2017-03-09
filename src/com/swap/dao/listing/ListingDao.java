package com.swap.dao.listing;

import java.util.List;

import com.swap.entity.listing.ImageEntity;
import com.swap.entity.listing.ListingEntity;

public interface ListingDao {
	ListingEntity getListingByItemId(ListingEntity listingEntity);

	List<ListingEntity> getListingsByUserId(ListingEntity listingEntity);

	void createListing(ListingEntity listingEntity);

	void updateListing(ListingEntity listingEntity);

	void deleteListing(ListingEntity listingEntity);
	
	List<Long> getListingIdsByUserId(Long userId);
	
	void updateListingForImage(Long itemId, ImageEntity imageEntity);
}
