package com.swap.entity.notification;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "location")
public class NotificationEntity {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;
	
	@Column(name = "user_id")
	private String userId;
	
	@Column(name = "type")
	private String type;
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "create_timestamp")
	private Timestamp createTimestamp;

	@Column(name = "interest_id")
	private String interestId;

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

	public Timestamp getCreateTimestamp() {
		return createTimestamp;
	}

	public void setCreateTimestamp(Timestamp createTimestamp) {
		this.createTimestamp = createTimestamp;
	}

	public String getInterestId() {
		return interestId;
	}

	public void setInterestId(String interestId) {
		this.interestId = interestId;
	}
}
