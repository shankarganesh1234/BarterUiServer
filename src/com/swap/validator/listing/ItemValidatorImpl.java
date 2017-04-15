package com.swap.validator.listing;

import org.springframework.stereotype.Service;

import com.swap.common.error.ErrorEnum;
import com.swap.common.exceptions.SwapException;
import com.swap.entity.item.ItemEntity;
import com.swap.models.listing.ItemRequest;

@Service
public class ItemValidatorImpl implements ItemValidator {

	@Override
	public void validateListingRequest(ItemRequest listingRequest) {
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
	public void validateUserId(String userId) {
		if (userId == null) {
			throw new SwapException(ErrorEnum.INVALID_USER_ID);
		}
	}

	@Override
	public void validateResponse(ItemEntity entity) throws SwapException {
		if (entity == null) {
			throw new SwapException(ErrorEnum.LISTING_DAO_ERROR);
		}
	}

	@Override
	public void validateUpdateRequest(ItemRequest listingRequest) {
		if (listingRequest == null || listingRequest.getItemId() == null)
			throw new SwapException(ErrorEnum.INVALID_LISTING_REQUEST);
		if(validateNotNullFields(listingRequest)){
			throw new SwapException(ErrorEnum.INVALID_LISTING_REQUEST);
		}
	}

	private boolean validateNotNullFields(ItemRequest listingRequest) {
		return listingRequest.getDescription() == null || listingRequest.getTitle() == null
				|| listingRequest.getCategoryId() == null || listingRequest.getUserId() == null;
	}
}