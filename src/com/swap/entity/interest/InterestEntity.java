package com.swap.entity.interest;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;
import org.hibernate.annotations.UpdateTimestamp;

import com.swap.entity.common.UserEntity;
import com.swap.entity.listing.ListingEntity;

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
	private ListingEntity originalItemId;

	@ManyToOne
	@JoinColumn(name = "swappable_item_id")
	private ListingEntity swappableItemId;

	@Column(name = "transaction_id")
	private String transactionId;

	@Column(name = "one_sided_interest_flag")
	private Boolean oneSidedInterestFlag;

	@Column(name = "two_sided_interest_flag")
	private Boolean twoSidedInterestFlag;

	@Column(name = "swap_confirmed")
	private Boolean swapConfirmed;

	@Column(name = "created_on", insertable = false, updatable = false)
	private Date createdOn;

	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="updated_on")
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

	public ListingEntity getOriginalItemId() {
		return originalItemId;
	}

	public void setOriginalItemId(ListingEntity originalItemId) {
		this.originalItemId = originalItemId;
	}

	public ListingEntity getSwappableItemId() {
		return swappableItemId;
	}

	public void setSwappableItemId(ListingEntity swappableItemId) {
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("InterestEntity [interestId=").append(interestId).append(", originalUser=").append(originalUser)
				.append(", interestedUser=").append(interestedUser).append(", originalItemId=").append(originalItemId)
				.append(", swappableItemId=").append(swappableItemId).append(", transactionId=").append(transactionId)
				.append(", oneSidedInterestFlag=").append(oneSidedInterestFlag).append(", twoSidedInterestFlag=")
				.append(twoSidedInterestFlag).append(", swapConfirmed=").append(swapConfirmed).append(", createdOn=")
				.append(createdOn).append(", updatedOn=").append(updatedOn).append("]");
		return builder.toString();
	}

}
