package com.swap.entity.review;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.hibernate.annotations.UpdateTimestamp;

import com.swap.entity.common.UserEntity;

@JsonSerialize(include = Inclusion.NON_NULL)
@Entity
@Table(name="user_reviews")
public class ReviewEntity {

	@Id
	@Column(name="review_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long reviewId;
	
	@Column(name="user_review")
	private String review;
	
	@OneToOne
	@JoinColumn(name="user_review_for")
	private UserEntity reviewFor;
	
	@ManyToOne
	@JoinColumn(name="user_review_by")
	private UserEntity reviewBy;
	
	@Column(name="feedback_score")
	private Integer feedbackScore;
	
	@Column(name="comments")
	private String comments;
	
	@Column(name="created_on", insertable = false, updatable = false)
	private Date createdDate;
	
	@UpdateTimestamp
	@Column(name="updated_on", insertable = false)
	private Date updatedDate;
	
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
