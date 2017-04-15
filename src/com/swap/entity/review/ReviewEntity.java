package com.swap.entity.review;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.hibernate.annotations.UpdateTimestamp;

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
	
	@Column(name="created_on", insertable = false, updatable = false)
	private Date createdDate;
	
	@UpdateTimestamp
	@Column(name="updated_on", insertable = false)
	private Date updatedDate;
	
	@Column(name = "user_review_for")
	private String reviewFor;
	
	@Column(name = "user_review_by")
	private String reviewBy;
	
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
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
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
