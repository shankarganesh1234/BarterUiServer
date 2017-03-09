package com.swap.validator.review;

import org.springframework.stereotype.Component;

import com.swap.common.error.ErrorEnum;
import com.swap.common.exceptions.SwapException;
import com.swap.models.review.ReviewRequest;

@Component
public class ReviewValidatorImpl implements ReviewValidator {

	@Override
	public void validateCreateReviewRequest(ReviewRequest request) {
		if(request == null)
			throw new SwapException(ErrorEnum.INVALID_REVIEW_REQUEST);
	}

	@Override
	public void validateUpdateReviewRequest(ReviewRequest request) {
		if(request == null)
			throw new SwapException(ErrorEnum.INVALID_REVIEW_REQUEST);
	}

}
