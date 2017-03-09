package com.swap.validator.interest;

import java.util.List;

import com.swap.models.interest.InterestRequest;

public interface InterestValidator {

	void validateCreateInterestRequest(InterestRequest request);
	void validateUpdateInterestRequest(InterestRequest request);
	void validateItemBelongsToUser(List<Long> itemIds, Long itemId);
}
