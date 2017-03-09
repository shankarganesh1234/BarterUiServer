package com.swap.transformer.listing;

import java.util.List;

import com.swap.entity.listing.ListingEntity;
import com.swap.models.listing.Item;
import com.swap.models.listing.ListingRequest;

public interface ListingTransformer {

	ListingEntity createListingEntity(Item item);

	Item createaListingModel(ListingEntity entity);

	ListingEntity createListingEntityFromId(Long id, boolean isItemId);

	List<Item> convertFromEntitiesToItemList(List<ListingEntity> listingEntities);

	Item convertRequestToItem(ListingRequest listingRequest, String call);
}