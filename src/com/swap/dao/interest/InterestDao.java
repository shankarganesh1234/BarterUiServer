package com.swap.dao.interest;

import java.util.List;

import com.swap.entity.interest.InterestEntity;

public interface InterestDao {

	void createInterested(InterestEntity entity);
	void updateInterested(InterestEntity entity);
	void deleteInterest(Long id);
	InterestEntity getInterestById(Long id);
	List<InterestEntity> getInterestsByInterestedUser(Long userId);
	List<InterestEntity> getInterestedByItemOwnerUser(Long userId);
	List<InterestEntity> getInterests(String userId, String itemId, boolean isOwner);
}
