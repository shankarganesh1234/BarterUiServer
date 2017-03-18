package com.swap.models.interest;

import java.util.Date;

import com.swap.entity.common.UserEntity;
import com.swap.entity.item.ItemEntity;

public class InterestResponse {

	private Long interestId;
	private UserEntity originalUser;
	private UserEntity interestedUser;
	private ItemEntity originalItemId;
	private ItemEntity swappableItemId;
	private String transactionId;
	private Boolean oneSidedInterestFlag;
	private Boolean twoSidedInterestFlag;
	private Boolean swapConfirmed;
	private Date createdOn;
	private Date updatedOn;
	public Long getInterestId() {
		return interestId;
	}
	public void setInterestId(Long interestId) {
		this.interestId = interestId;
	}
	public UserEntity getOriginalUser() {
		return originalUser;
	}
	public void setOriginalUser(UserEntity originalUser) {
		this.originalUser = originalUser;
	}
	public UserEntity getInterestedUser() {
		return interestedUser;
	}
	public void setInterestedUser(UserEntity interestedUser) {
		this.interestedUser = interestedUser;
	}
	
	public ItemEntity getOriginalItemId() {
		return originalItemId;
	}
	public void setOriginalItemId(ItemEntity originalItemId) {
		this.originalItemId = originalItemId;
	}
	public ItemEntity getSwappableItemId() {
		return swappableItemId;
	}
	public void setSwappableItemId(ItemEntity swappableItemId) {
		this.swappableItemId = swappableItemId;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public Boolean getOneSidedInterestFlag() {
		return oneSidedInterestFlag;
	}
	public void setOneSidedInterestFlag(Boolean oneSidedInterestFlag) {
		this.oneSidedInterestFlag = oneSidedInterestFlag;
	}
	public Boolean getTwoSidedInterestFlag() {
		return twoSidedInterestFlag;
	}
	public void setTwoSidedInterestFlag(Boolean twoSidedInterestFlag) {
		this.twoSidedInterestFlag = twoSidedInterestFlag;
	}
	public Boolean getSwapConfirmed() {
		return swapConfirmed;
	}
	public void setSwapConfirmed(Boolean swapConfirmed) {
		this.swapConfirmed = swapConfirmed;
	}
	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	public Date getUpdatedOn() {
		return updatedOn;
	}
	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}
}
