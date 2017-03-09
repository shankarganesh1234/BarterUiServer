package com.swap.transformer.review;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.swap.entity.common.UserEntity;
import com.swap.entity.review.ReviewEntity;
import com.swap.models.review.ReviewRequest;
import com.swap.models.review.ReviewResponse;
import com.swap.models.review.ReviewsResponse;

@Component
public class ReviewTransformerImpl implements ReviewTransformer {

	@Override
	public ReviewEntity createEntityFromRequest(ReviewRequest request) {
		ReviewEntity entity = new ReviewEntity();
		BeanUtils.copyProperties(request, entity);
		entity.setReviewFor(createEntityFromId(request.getReviewFor()));
		entity.setReviewBy(createEntityFromId(request.getReviewBy()));
		return entity;
	}

	@Override
	public ReviewResponse createResponse(ReviewEntity entity) {
		ReviewResponse response = new ReviewResponse();
		BeanUtils.copyProperties(entity, response);
		return response;
	}

	@Override
	public ReviewsResponse createResponses(List<ReviewEntity> entities) {
		ReviewsResponse reviewsResponse = new ReviewsResponse();
		List<ReviewResponse> reviewResponseList = new LinkedList<>();
		for(ReviewEntity entity : entities) {
			ReviewResponse reviewResponse = new ReviewResponse();
			BeanUtils.copyProperties(entity, reviewResponse);
			reviewResponseList.add(reviewResponse);
		}
		reviewsResponse.setReviews(reviewResponseList);
		return reviewsResponse;
	}
	
	/**
	 * Helper method to create entity from ID
	 * @param id
	 * @return
	 */
	private UserEntity createEntityFromId(Long id) {
		UserEntity entity = new UserEntity();
		entity.setUserId(id);
		return entity;
	}
}
