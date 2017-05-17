package com.swap.models.elasticsearch;

import java.util.Date;

public class ChatHistoryDocument {

	private String chatChannelId;
	private String participants;
	private String senderId;
	private String senderName;
	private String message;
	private Date messageTimestamp;
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
		StringBuilder builder = new StringBuilder();
		builder.append("ChatHistoryDocument [chatChannelId=").append(chatChannelId).append(", participants=")
				.append(participants).append(", senderId=").append(senderId).append(", senderName=").append(senderName)
				.append(", message=").append(message).append(", messageTimestamp=").append(messageTimestamp)
				.append("]");
		return builder.toString();
	}
}
