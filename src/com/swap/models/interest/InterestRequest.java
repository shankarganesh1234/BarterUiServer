package com.swap.models.interest;

import java.util.List;

public class InterestRequest {

	private Long interestId;
	private Long originalUser;
	private Long interestedUser;
	private Long originalItem;
	private List<Long> swappableItemIds;
	private Boolean oneSidedInterestFlag;
	private Boolean twoSidedInterestFlag;
	private String transactionId;
	private Boolean swapConfirmed;
	
	public Long getInterestId() {
		return interestId;
	}
	public void setInterestId(Long interestId) {
		this.interestId = interestId;
	}
	public Long getOriginalUser() {
		return originalUser;
	}
	public void setOriginalUser(Long originalUser) {
		this.originalUser = originalUser;
	}
	public Long getInterestedUser() {
		return interestedUser;
	}
	public void setInterestedUser(Long interestedUser) {
		this.interestedUser = interestedUser;
	}
	public Long getOriginalItem() {
		return originalItem;
	}
	public void setOriginalItem(Long originalItem) {
		this.originalItem = originalItem;
	}
	public List<Long> getSwappableItemIds() {
		return swappableItemIds;
	}
	public void setSwappableItemIds(List<Long> swappableItemIds) {
		this.swappableItemIds = swappableItemIds;
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
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("InterestRequest [interestId=").append(interestId).append(", originalUser=").append(originalUser)
				.append(", interestedUser=").append(interestedUser).append(", originalItem=").append(originalItem)
				.append(", swappableItemIds=").append(swappableItemIds).append(", oneSidedInterestFlag=")
				.append(oneSidedInterestFlag).append(", twoSidedInterestFlag=").append(twoSidedInterestFlag)
				.append(", transactionId=").append(transactionId).append("]");
		return builder.toString();
	}
	
	public Boolean getSwapConfirmed() {
		return swapConfirmed;
	}
	public void setSwapConfirmed(Boolean swapConfirmed) {
		this.swapConfirmed = swapConfirmed;
	}
}
