package com.swap.service.listing;

import java.util.List;

import com.swap.models.listing.Item;
import com.swap.models.listing.ListingRequest;

public interface ListingService {
	public Item getListingByItemId(Long itemId);

	public List<Item> getListingsByUserId(Long userId);

	public void listing(ListingRequest listingRequest);

	public void updateListing(ListingRequest listingRequest);
	
	public void deleteListing(Long itemId);
	
	List<Long> getListingIdsByUserId(Long userId);
}