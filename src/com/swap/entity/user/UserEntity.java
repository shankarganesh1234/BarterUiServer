package com.swap.entity.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.hibernate.annotations.DynamicUpdate;
@JsonSerialize(include = Inclusion.NON_NULL)
@Entity
@Table(name="userconnection")
@DynamicUpdate
public class UserEntity {
	
	@Id
	@Column(name="userid")
	private String userId;
	
	@Column(name="providerid")
	private String providerId;
	
	@Column(name="provideruserid")
	private String providerUserId;
	
	@Column(name="rank")
	private Long rank;
	
	@Column(name="displayname")
	private String displayName;
	
	@Column(name="profileurl")
	private String profileUrl;
	
	@Column(name="imageurl")
	private String imageUrl;
	
	@Column(name="accesstoken")
	private String accessToken;
	
	@Column(name="secret")
	private String secret;
	
	@Column(name="refreshToken")
	private String refreshtoken;
	
	@Column(name="expiretime")
	private Long expireTime;

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
}