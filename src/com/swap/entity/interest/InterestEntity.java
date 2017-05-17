package com.swap.entity.interest;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;

import com.swap.entity.item.ItemEntity;
import com.swap.entity.user.UserEntity;

@Entity
@Table(name = "interest")
@DynamicUpdate
@OptimisticLocking(type = OptimisticLockType.DIRTY)
public class InterestEntity {

	@Id
	@Column(name = "interest_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long interestId;

	@ManyToOne
	@JoinColumn(name = "original_user")
	private UserEntity originalUser;

	@ManyToOne
	@JoinColumn(name = "interested_user")
	private UserEntity interestedUser;

	@ManyToOne
	@JoinColumn(name = "original_item_id")
	private ItemEntity originalItemId;

	@ManyToOne
	@JoinColumn(name = "swappable_item_id")
	private ItemEntity swappableItemId;

	@Column(name = "transaction_id")
	private String transactionId;

	@Column(name = "one_sided_interest_flag")
	private Boolean oneSidedInterestFlag;

	@Column(name = "two_sided_interest_flag")
	private Boolean twoSidedInterestFlag;

	@Column(name = "swap_confirmed")
	private Boolean swapConfirmed;

	@Column(name = "upsert_date")
	private Timestamp upsertDate;
	
	public Timestamp getUpsertDate() {
		return upsertDate;
	}

	public void setUpsertDate(Timestamp upsertDate) {
		this.upsertDate = upsertDate;
	}

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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("InterestEntity [interestId=").append(interestId).append(", originalUser=").append(originalUser)
				.append(", interestedUser=").append(interestedUser).append(", originalItemId=").append(originalItemId)
				.append(", swappableItemId=").append(swappableItemId).append(", transactionId=").append(transactionId)
				.append(", oneSidedInterestFlag=").append(oneSidedInterestFlag).append(", twoSidedInterestFlag=")
				.append(twoSidedInterestFlag).append(", swapConfirmed=").append(swapConfirmed).append(", upsertDate=")
				.append(upsertDate).append("]");
		return builder.toString();
	}
}
