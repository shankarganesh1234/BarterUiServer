package com.swap.entity.review;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

@JsonSerialize(include = Inclusion.NON_NULL)
@Entity
@Table(name="user_reviews")
public class ReviewEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="review_id")
	private Long reviewId;
	
	@Column(name="user_review")
	private String review;
	
	@Column(name="feedback_score")
	private Integer feedbackScore;
	
	@Column(name="comments")
	private String comments;
	
	@Column(name = "user_review_for")
	private String reviewFor;
	
	@Column(name = "user_review_by")
	private String reviewBy;
	
	@Column(name = "upsert_date")
	private Timestamp upsertDate;
	
	public Timestamp getUpsertDate() {
		return upsertDate;
	}
	public void setUpsertDate(Timestamp upsertDate) {
		this.upsertDate = upsertDate;
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
	public Long getReviewId() {
		return reviewId;
	}
	public void setReviewId(Long reviewId) {
		this.reviewId = reviewId;
	}
	public String getReview() {
		return review;
	}
	public void setReview(String review) {
		this.review = review;
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
	
}
