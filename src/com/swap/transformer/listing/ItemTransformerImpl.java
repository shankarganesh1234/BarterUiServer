package com.swap.transformer.listing;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.swap.common.enums.ItemConditionEnum;
import com.swap.common.enums.ItemStageEnum;
import com.swap.entity.category.CategoryEntity;
import com.swap.entity.item.ItemEntity;
import com.swap.entity.user.UserEntity;
import com.swap.models.listing.Item;
import com.swap.models.listing.ItemRequest;

@Service
public class ItemTransformerImpl implements ItemTransformer {

	@Override
	public ItemEntity createListingEntity(Item item) {
		ItemEntity listingEntity = new ItemEntity();
		BeanUtils.copyProperties(item, listingEntity);
		if (item.getCondition() != null) {
			listingEntity.setCondition(item.getCondition().name());
		}
		if (item.getItemStage() != null) {
			listingEntity.setItemStage(item.getItemStage().name());
		}
		return listingEntity;
	}

	@Override
	public Item createaListingModel(ItemEntity entity) {
		Item item = new Item();
		BeanUtils.copyProperties(entity, item);
		item.setCondition(entity.getCondition() != null ? ItemConditionEnum.fromValue(entity.getCondition().trim()) : null);
		item.setItemStage(entity.getItemStage() != null ? ItemStageEnum.fromValue(entity.getItemStage().trim()) : null);
		return item;
	}

	@Override
	public ItemEntity createListingEntityFromId(String id, boolean isItemId) {
		ItemEntity entity = new ItemEntity();
		if (isItemId) {
			entity.setItemId(Long.valueOf(id));
		} else {
			UserEntity user = new UserEntity();
			user.setUserId(id);
			entity.setUserId(user);
		}
		return entity;
	}

	@Override
	public List<Item> convertFromEntitiesToItemList(List<ItemEntity> listingEntities) {
		List<Item> items = new ArrayList<>(listingEntities.size());
		for (ItemEntity entity : listingEntities) {
			items.add(createaListingModel(entity));
		}
		return items;
	}

	@Override
	public Item convertRequestToItem(ItemRequest listingRequest, String call) {
		Item item = new Item();
		if ("POST".equals(call)) {
			item.setItemStage(ItemStageEnum.INITIALIZED);
		} else if ("PUT".equals(call)) {
			item.setItemId(listingRequest.getItemId());
			item.setItemStage(ItemStageEnum.INITIALIZED);
		}

		item.setTitle(listingRequest.getTitle());
		item.setDescription(listingRequest.getDescription());

		CategoryEntity category = new CategoryEntity();
		category.setCategoryId(listingRequest.getCategoryId());
		item.setCategoryId(category);

		item.setZipCode(listingRequest.getZipCode());

		UserEntity user = new UserEntity();
		user.setUserId(listingRequest.getUserId());
		item.setUserId(user);

		item.setQuantity(listingRequest.getQuantity());
		item.setCondition(ItemConditionEnum.fromValue(listingRequest.getCondition()));
		item.setStory(listingRequest.getStory() != null ? listingRequest.getStory() : null);
		item.setNumOfInterests(listingRequest.getNumOfInterests());
		item.setNumOfReviews(listingRequest.getNumOfReviews());
		return item;
	}
}