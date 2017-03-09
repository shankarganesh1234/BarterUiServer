package com.swap.models.interest;

import java.util.List;

public class InterestsResponse {

	private List<InterestResponse> interests;

	public List<InterestResponse> getInterests() {
		return interests;
	}

	public void setInterests(List<InterestResponse> interests) {
		this.interests = interests;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("InterestsResponse [interests=").append(interests).append("]");
		return builder.toString();
	}
}
