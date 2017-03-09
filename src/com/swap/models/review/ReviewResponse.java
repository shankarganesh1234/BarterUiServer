package com.swap.models.review;

import com.swap.entity.common.UserEntity;

public class ReviewResponse {

	private Long reviewId;
	private String review;
	private UserEntity reviewFor;
	private UserEntity reviewBy;
	private Integer feedbackScore;
	private String comments;
	
	public String getReview() {
		return review;
	}
	public void setReview(String review) {
		this.review = review;
	}
	
	public UserEntity getReviewFor() {
		return reviewFor;
	}
	public void setReviewFor(UserEntity reviewFor) {
		this.reviewFor = reviewFor;
	}
	public UserEntity getReviewBy() {
		return reviewBy;
	}
	public void setReviewBy(UserEntity reviewBy) {
		this.reviewBy = reviewBy;
	}
	public Integer getFeedbackScore() {
		return feedbackScore;
	}
	public void setFeedbackScore(Integer feedbackScore) {
		this.feedbackScore = feedbackScore;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public Long getReviewId() {
		return reviewId;
	}
	public void setReviewId(Long reviewId) {
		this.reviewId = reviewId;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ReviewResponse [reviewId=").append(reviewId).append(", review=").append(review)
				.append(", reviewFor=").append(reviewFor).append(", reviewBy=").append(reviewBy)
				.append(", feedbackScore=").append(feedbackScore).append(", comments=").append(comments).append("]");
		return builder.toString();
	}
	
}
