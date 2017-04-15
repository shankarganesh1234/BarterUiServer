package com.swap.models.chat;

public class ChatDetailsResponse {
	
	private String channelId;

	private String originalUserId;
	
	private String interestedUserId;

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public String getOriginalUserId() {
		return originalUserId;
	}

	public void setOriginalUserId(String originalUserId) {
		this.originalUserId = originalUserId;
	}

	public String getInterestedUserId() {
		return interestedUserId;
	}

	public void setInterestedUserId(String interestedUserId) {
		this.interestedUserId = interestedUserId;
	}
}
