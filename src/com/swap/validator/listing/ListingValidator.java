package com.swap.validator.listing;

import com.swap.common.exceptions.SwapException;
import com.swap.entity.listing.ListingEntity;
import com.swap.models.listing.ListingRequest;

public interface ListingValidator {
	void validateListingRequest(ListingRequest listingRequest);
	
	void validateUpdateRequest(ListingRequest listingRequest);

	void validateItemId(Long itemId);
	
	void validateUserId(Long userId);

	void validateResponse(ListingEntity entity) throws SwapException;

}