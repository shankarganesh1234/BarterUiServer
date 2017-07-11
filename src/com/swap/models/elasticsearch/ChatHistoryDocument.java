package com.swap.models.elasticsearch;

import java.util.Date;

public class ChatHistoryDocument {

	private String chatChannelId;
	private String participants;
	private String senderId;
	private String senderName;
	private String message;
	private Date messageTimestamp;
	private Long interestId;
	private String receiverId;
	private boolean isOnline;
	
	public boolean isOnline() {
		return isOnline;
	}
	public void setOnline(boolean isOnline) {
		this.isOnline = isOnline;
	}
	public String getReceiverId() {
		return receiverId;
	}
	public void setReceiverId(String receiverId) {
		this.receiverId = receiverId;
	}
	public Long getInterestId() {
		return interestId;
	}
	public void setInterestId(Long interestId) {
		this.interestId = interestId;
	}
	public String getChatChannelId() {
		return chatChannelId;
	}
	public void setChatChannelId(String chatChannelId) {
		this.chatChannelId = chatChannelId;
	}
	public String getParticipants() {
		return participants;
	}
	public void setParticipants(String participants) {
		this.participants = participants;
	}
	public String getSenderId() {
		return senderId;
	}
	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}
	public String getSenderName() {
		return senderName;
	}
	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Date getMessageTimestamp() {
		return messageTimestamp;
	}
	public void setMessageTimestamp(Date messageTimestamp) {
		this.messageTimestamp = messageTimestamp;
	}
	@Override
	public String toString() {
		return "ChatHistoryDocument [chatChannelId=" + chatChannelId + ", participants=" + participants + ", senderId="
				+ senderId + ", senderName=" + senderName + ", message=" + message + ", messageTimestamp="
				+ messageTimestamp + ", interestId=" + interestId + ", receiverId=" + receiverId + ", isOnline="
				+ isOnline + "]";
	}
}
