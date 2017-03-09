package com.swap.transformer.review;

import java.util.List;

import com.swap.entity.review.ReviewEntity;
import com.swap.models.review.ReviewRequest;
import com.swap.models.review.ReviewResponse;
import com.swap.models.review.ReviewsResponse;

public interface ReviewTransformer {

	ReviewEntity createEntityFromRequest(ReviewRequest request);
	ReviewResponse createResponse(ReviewEntity entity);
	ReviewsResponse createResponses(List<ReviewEntity> entities);
}
