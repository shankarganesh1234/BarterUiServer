package com.swap.validator.listing;

import org.springframework.stereotype.Service;

import com.swap.common.error.ErrorEnum;
import com.swap.common.exceptions.SwapException;
import com.swap.entity.listing.ListingEntity;
import com.swap.models.listing.ListingRequest;

@Service
public class ListingValidatorImpl implements ListingValidator {

	@Override
	public void validateListingRequest(ListingRequest listingRequest) {
		if (listingRequest == null)
			throw new SwapException(ErrorEnum.INVALID_LISTING_REQUEST);
		if(validateNotNullFields(listingRequest)){
			throw new SwapException(ErrorEnum.NOT_NULL_FIELDS_ERROR);
		}
	}

	@Override
	public void validateItemId(Long itemId) {
		if (itemId == null) {
			throw new SwapException(ErrorEnum.INVALID_ITEM_ID);
		}
	}
	
	@Override
	public void validateUserId(Long userId) {
		if (userId == null) {
			throw new SwapException(ErrorEnum.INVALID_USER_ID);
		}
	}

	@Override
	public void validateResponse(ListingEntity entity) throws SwapException {
		if (entity == null) {
			throw new SwapException(ErrorEnum.LISTING_DAO_ERROR);
		}
	}

	@Override
	public void validateUpdateRequest(ListingRequest listingRequest) {
		if (listingRequest == null || listingRequest.getItemId() == null)
			throw new SwapException(ErrorEnum.INVALID_LISTING_REQUEST);
		if(validateNotNullFields(listingRequest)){
			throw new SwapException(ErrorEnum.INVALID_LISTING_REQUEST);
		}
	}

	private boolean validateNotNullFields(ListingRequest listingRequest) {
		return listingRequest.getDescription() == null || listingRequest.getTitle() == null
				|| listingRequest.getCategoryId() == null || listingRequest.getUserId() == null;
	}
}