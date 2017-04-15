package com.swap.models.user;

import java.util.List;

import com.swap.entity.review.ReviewEntity;
import com.swap.entity.user.UserDetailsEntity;

public class UserResponse {

	private String userId;
	private String providerId;
	private String providerUserId;
	private Long rank;
	private String displayName;
	private String profileUrl;
	private String imageUrl;
	private String accessToken;
	private String secret;
	private String refreshtoken;
	private Long expireTime;
	private UserDetailsEntity userDetails;
	private List<ReviewEntity> reviews;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getProviderId() {
		return providerId;
	}
	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}
	public String getProviderUserId() {
		return providerUserId;
	}
	public void setProviderUserId(String providerUserId) {
		this.providerUserId = providerUserId;
	}
	public Long getRank() {
		return rank;
	}
	public void setRank(Long rank) {
		this.rank = rank;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public String getProfileUrl() {
		return profileUrl;
	}
	public void setProfileUrl(String profileUrl) {
		this.profileUrl = profileUrl;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public String getSecret() {
		return secret;
	}
	public void setSecret(String secret) {
		this.secret = secret;
	}
	public String getRefreshtoken() {
		return refreshtoken;
	}
	public void setRefreshtoken(String refreshtoken) {
		this.refreshtoken = refreshtoken;
	}
	public Long getExpireTime() {
		return expireTime;
	}
	public void setExpireTime(Long expireTime) {
		this.expireTime = expireTime;
	}
	public UserDetailsEntity getUserDetails() {
		return userDetails;
	}
	public void setUserDetails(UserDetailsEntity userDetails) {
		this.userDetails = userDetails;
	}
	public List<ReviewEntity> getReviews() {
		return reviews;
	}
	public void setReviews(List<ReviewEntity> reviews) {
		this.reviews = reviews;
	}
}
