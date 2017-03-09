package com.swap.validator.interest;

import java.util.List;

import org.springframework.stereotype.Component;

import com.swap.common.error.ErrorEnum;
import com.swap.common.exceptions.SwapException;
import com.swap.models.interest.InterestRequest;

@Component
public class InterestValidatorImpl implements InterestValidator {

	@Override
	public void validateCreateInterestRequest(InterestRequest request) {
		
		if(request == null)
			throw new SwapException(ErrorEnum.INVALID_INTEREST_REQUEST);
		
		if(request.getInterestedUser() == null)
			throw new SwapException(ErrorEnum.INVALID_INTERESTED_USER_ID);
			
		if(request.getOriginalUser() == null)
			throw new SwapException(ErrorEnum.INVALID_ORIGINAL_ITEM_USER_ID);
		
		if(request.getOneSidedInterestFlag() == null)
			throw new SwapException(ErrorEnum.INVALID_ONE_SIDED_INTEREST_FLAG);
		
		if(request.getOneSidedInterestFlag()) {
			
			if(request.getSwappableItemIds() == null || request.getSwappableItemIds().isEmpty())
				throw new SwapException(ErrorEnum.INVALID_SWAPPABLE_ITEMS_LIST);
			
			if(request.getSwappableItemIds().size() > 3)
				throw new SwapException(ErrorEnum.TOO_MANY_SWAPPABLE_ITEMS);
		}
		
	}
	
	@Override
	public void validateItemBelongsToUser(List<Long> itemIds, Long itemId) {
		if(itemIds == null)
			throw new SwapException(ErrorEnum.NO_ITEMS_FOUND_FOR_USER);
		
		if(!itemIds.contains(itemId))
			throw new SwapException(ErrorEnum.ITEM_DOES_NOT_BELONG_TO_USER);
	}

	@Override
	public void validateUpdateInterestRequest(InterestRequest request) {
		if(request == null)
			throw new SwapException(ErrorEnum.INVALID_INTEREST_REQUEST);

		if(request.getInterestId() == null)
			throw new SwapException(ErrorEnum.INVALID_INTERESTED_USER_ID);
		
		if(request.getInterestedUser() == null)
			throw new SwapException(ErrorEnum.INVALID_INTERESTED_USER_ID);
			
		if(request.getOriginalUser() == null)
			throw new SwapException(ErrorEnum.INVALID_ORIGINAL_ITEM_USER_ID);
		
		if(request.getOneSidedInterestFlag() == null)
			throw new SwapException(ErrorEnum.INVALID_ONE_SIDED_INTEREST_FLAG);
		
		if(request.getOneSidedInterestFlag()) {
			
			if(request.getSwappableItemIds() == null || request.getSwappableItemIds().isEmpty())
				throw new SwapException(ErrorEnum.INVALID_SWAPPABLE_ITEMS_LIST);
			
			if(request.getSwappableItemIds().size() > 3)
				throw new SwapException(ErrorEnum.TOO_MANY_SWAPPABLE_ITEMS);
		}
	}

}
