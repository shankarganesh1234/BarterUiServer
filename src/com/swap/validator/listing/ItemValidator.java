package com.swap.validator.listing;

import com.swap.common.exceptions.SwapException;
import com.swap.entity.item.ItemEntity;
import com.swap.models.listing.ItemRequest;

public interface ItemValidator {
	void validateListingRequest(ItemRequest listingRequest);
	
	void validateUpdateRequest(ItemRequest listingRequest);

	void validateItemId(Long itemId);
	
	void validateUserId(String userId);

	void validateResponse(ItemEntity entity) throws SwapException;

}