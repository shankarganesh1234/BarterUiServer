package com.swap.models.review;

public class ReviewRequest {

	private String review;
	private Long reviewFor;
	private Long reviewBy;
	private Integer feedbackScore;
	private String comments;
	private Long reviewId;
	
	public String getReview() {
		return review;
	}
	public void setReview(String review) {
		this.review = review;
	}
	public Long getReviewFor() {
		return reviewFor;
	}
	public void setReviewFor(Long reviewFor) {
		this.reviewFor = reviewFor;
	}
	public Long getReviewBy() {
		return reviewBy;
	}
	public void setReviewBy(Long reviewBy) {
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
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ReviewRequest [review=").append(review).append(", reviewFor=").append(reviewFor)
				.append(", reviewBy=").append(reviewBy).append(", feedbackScore=").append(feedbackScore)
				.append(", comments=").append(comments).append("]");
		return builder.toString();
	}
	public Long getReviewId() {
		return reviewId;
	}
	public void setReviewId(Long reviewId) {
		this.reviewId = reviewId;
	}	
}
