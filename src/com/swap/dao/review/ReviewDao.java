package com.swap.dao.review;

import java.util.List;

import com.swap.entity.review.ReviewEntity;

public interface ReviewDao {

	void createReview(ReviewEntity entity);
	void updateReview(ReviewEntity entity);
	ReviewEntity getReviewByReviewId(Long reviewId);
	List<ReviewEntity> getReviewsForUser(String userId);
	List<ReviewEntity> getReviewsByUser(String userId);
	List<ReviewEntity> getReviewsForUserByUser(String forUser, String byUser);
	void deleteReviewByReviewId(Long id);
	void deleteReviewsForUser(Long userId);
	void deleteReviewsByUser(Long userId);
}

