package com.swap.service.review;

import com.swap.models.review.ReviewRequest;
import com.swap.models.review.ReviewResponse;
import com.swap.models.review.ReviewsResponse;

public interface ReviewService {
	
	void createReview(ReviewRequest request);
	void updateReview(ReviewRequest request, Long reviewId);
	ReviewResponse getReviewByReviewId(Long reviewId);
	ReviewsResponse getReviewsForUser(String userId);
	ReviewsResponse getReviewsByUser(String userId);
	ReviewsResponse getReviewsForUserByUser(String forUser, String byUser);
	void deleteReviewByReviewId(Long id);
}
