package com.swap.models.notification;

import java.sql.Timestamp;

import com.swap.common.enums.NotificationStatusEnum;
import com.swap.common.enums.NotificationTypeEnum;

public class NotificationModel {
	
	private String userId;
	private NotificationTypeEnum type;
	private NotificationStatusEnum status;
	private String interestId;
	private Timestamp createTimestamp;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public NotificationTypeEnum getType() {
		return type;
	}
	public void setType(NotificationTypeEnum type) {
		this.type = type;
	}
	public NotificationStatusEnum getStatus() {
		return status;
	}
	public void setStatus(NotificationStatusEnum status) {
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
		builder.append("NotificationModel").append(", userId=").append(userId).append(", type=")
				.append(type).append(", status=").append(status).append(", interestId=").append(interestId)
				.append(", createTimestamp=").append(createTimestamp).append("]");
		return builder.toString();
	}
}
