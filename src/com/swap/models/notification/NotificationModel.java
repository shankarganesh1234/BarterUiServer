package com.swap.models.notification;

import java.sql.Timestamp;

public class NotificationModel {
	
	private String id;
	private String userId;
	private String type;
	private String status;
	private String interestId;
	private Timestamp createTimestamp;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getInterestId() {
		return interestId;
	}
	public void setInterestId(String interestId) {
		this.interestId = interestId;
	}
	public Timestamp getCreateTimestamp() {
		return createTimestamp;
	}
	public void setCreateTimestamp(Timestamp createTimestamp) {
		this.createTimestamp = createTimestamp;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("NotificationModel [id=").append(id).append(", userId=").append(userId).append(", type=")
				.append(type).append(", status=").append(status).append(", interestId=").append(interestId)
				.append(", createTimestamp=").append(createTimestamp).append("]");
		return builder.toString();
	}
}
