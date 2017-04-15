package com.swap.transformer.listing;

import java.util.List;

import com.swap.entity.item.ItemEntity;
import com.swap.models.listing.Item;
import com.swap.models.listing.ItemRequest;

public interface ItemTransformer {

	ItemEntity createListingEntity(Item item);

	Item createaListingModel(ItemEntity entity);

	ItemEntity createListingEntityFromId(String id, boolean isItemId);

	List<Item> convertFromEntitiesToItemList(List<ItemEntity> listingEntities);

	Item convertRequestToItem(ItemRequest listingRequest, String call);
}