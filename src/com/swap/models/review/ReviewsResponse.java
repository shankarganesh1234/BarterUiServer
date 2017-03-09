package com.swap.models.review;

import java.util.List;

public class ReviewsResponse {

	private List<ReviewResponse> reviews;

	public List<ReviewResponse> getReviews() {
		return reviews;
	}

	public void setReviews(List<ReviewResponse> reviews) {
		this.reviews = reviews;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ReviewsResponse [reviews=").append(reviews).append("]");
		return builder.toString();
	}
}
