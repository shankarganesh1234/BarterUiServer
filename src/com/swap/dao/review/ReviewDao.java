package com.swap.dao.review;

import java.util.List;

import com.swap.entity.review.ReviewEntity;

public interface ReviewDao {

	void createReview(ReviewEntity entity);
	void updateReview(ReviewEntity entity);
	ReviewEntity getReviewByReviewId(Long reviewId);
	List<ReviewEntity> getReviewsForUser(Long userId);
	List<ReviewEntity> getReviewsByUser(Long userId);
	List<ReviewEntity> getReviewsForUserByUser(Long forUser, Long byUser);
	void deleteReviewByReviewId(Long id);
	void deleteReviewsForUser(Long userId);
	void deleteReviewsByUser(Long userId);
}

