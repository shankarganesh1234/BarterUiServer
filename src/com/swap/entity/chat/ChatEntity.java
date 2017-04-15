package com.swap.entity.chat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import com.swap.entity.user.UserEntity;

@JsonSerialize(include = Inclusion.NON_NULL)
@Entity
@Table(name="chat_details")
public class ChatEntity {

	@Id
	@Column(name = "channel_id")
	private String channelId;

	@OneToOne
	@JoinColumn(name = "original_user_id")
	private UserEntity originalUserId;
	
	@OneToOne
	@JoinColumn(name = "interested_user_id")
	private UserEntity interestedUserId;

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public UserEntity getOriginalUserId() {
		return originalUserId;
	}

	public void setOriginalUserId(UserEntity originalUserId) {
		this.originalUserId = originalUserId;
	}

	public UserEntity getInterestedUserId() {
		return interestedUserId;
	}

	public void setInterestedUserId(UserEntity interestedUserId) {
		this.interestedUserId = interestedUserId;
	}
	
}
