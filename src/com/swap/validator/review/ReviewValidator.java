package com.swap.validator.review;

import com.swap.models.review.ReviewRequest;

public interface ReviewValidator {

	void validateCreateReviewRequest(ReviewRequest request);
	void validateUpdateReviewRequest(ReviewRequest request);
}
