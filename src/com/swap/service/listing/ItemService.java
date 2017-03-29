package com.swap.service.listing;

import java.util.List;

import com.swap.models.listing.Item;
import com.swap.models.listing.ItemRequest;

public interface ItemService {
	public Item getListingByItemId(Long itemId);

	public List<Item> getListingsByUserId(Long userId);

	public Item listing(ItemRequest listingRequest);

	public Item updateListing(ItemRequest listingRequest);
	
	public void deleteListing(Long itemId);
	
	List<Long> getListingIdsByUserId(Long userId);
}