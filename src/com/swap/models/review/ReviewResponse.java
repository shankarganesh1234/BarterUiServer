package com.swap.models.review;

public class ReviewResponse {

	private Long reviewId;
	private String review;
	private String reviewFor;
	private String reviewBy;
	private Integer feedbackScore;
	private String comments;
	
	public String getReview() {
		return review;
	}
	public void setReview(String review) {
		this.review = review;
	}
	
	public Integer getFeedbackScore() {
		return feedbackScore;
	}
	public String getReviewFor() {
		return reviewFor;
	}
	public void setReviewFor(String reviewFor) {
		this.reviewFor = reviewFor;
	}
	public String getReviewBy() {
		return reviewBy;
	}
	public void setReviewBy(String reviewBy) {
		this.reviewBy = reviewBy;
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
