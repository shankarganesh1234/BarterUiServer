package com.swap.transformer.interest;

import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.swap.common.cache.UniqueIdGenerator;
import com.swap.entity.common.UserEntity;
import com.swap.entity.interest.InterestEntity;
import com.swap.entity.listing.ListingEntity;
import com.swap.models.interest.InterestRequest;
import com.swap.models.interest.InterestResponse;

@Component
public class InterestTransformerImpl implements InterestTransformer {

	@Inject
	private UniqueIdGenerator uniqueIdGenerator;
	
	@Override
	public List<InterestEntity> createEntityList(InterestRequest request) {
		
		List<InterestEntity> entities = null;
		InterestEntity entity = null;
		
		int swappableItemsSize = request.getSwappableItemIds().size();
		String txId = getTxId();
		entities = new LinkedList<>();
		
		for(int i=0; i<swappableItemsSize; i++) {
			entity = createEntity(request, txId, i);
			entities.add(entity);
		}
		return entities;
	}
	
	/**
	 * 
	 * @param request
	 * @param txId
	 * @param i
	 * @return
	 */
	private InterestEntity createEntity(InterestRequest request, String txId, int i) {
		InterestEntity entity = new InterestEntity();
		entity.setInterestedUser(createUserEntityFromId(request.getInterestedUser()));
		entity.setSwappableItemId(createListingEntityFromItemId(request.getSwappableItemIds().get(i)));
		entity.setOriginalItemId(createListingEntityFromItemId(request.getOriginalItem()));
		entity.setOriginalUser(createUserEntityFromId(request.getOriginalUser()));
		entity.setTransactionId(txId);
		entity.setOneSidedInterestFlag(request.getOneSidedInterestFlag());
		entity.setSwapConfirmed(request.getSwapConfirmed());
		return entity;
	}
	
	/**
	 * 
	 * @param entity
	 * @return
	 */
	@Override
	public InterestResponse createResponseFromEntity(InterestEntity entity) {
		InterestResponse interestResponse = new InterestResponse();
		BeanUtils.copyProperties(entity, interestResponse);
		return interestResponse;
	}
	
	
	/**
	 * Returns UserEntity with a specified user Id
	 * @param userId
	 * @return
	 */
	private UserEntity createUserEntityFromId(Long userId) {
		
		if(userId == null)
			return null;
		
		UserEntity userEntity = new UserEntity();
		userEntity.setUserId(userId);
		return userEntity;
	}
	
	/**
	 * Returns ListingEntity with a specified item Id
	 * @param itemId
	 * @return
	 */
	private ListingEntity createListingEntityFromItemId(Long itemId) {
		
		if(itemId == null)
			return null;
		
		ListingEntity listingEntity = new ListingEntity();
		listingEntity.setItemId(itemId);
		return listingEntity;
	}
	
	/**
	 * Generate a UUID using UUID generator
	 * @return
	 */
	private String getTxId() {
		return uniqueIdGenerator.getUniqueId();
	}

	@Override
	public List<InterestResponse> createResponseListFromEntityList(List<InterestEntity> entities) {
		
		if(entities == null || entities.isEmpty())
			return null;
			
		List<InterestResponse> interestResponseList = new LinkedList<>();;
		for(InterestEntity entity : entities) {
			interestResponseList.add(createResponseFromEntity(entity));
		}
		return interestResponseList;
	}

	@Override
	public InterestEntity createUpdateEntity(InterestRequest request) {
		InterestEntity entity = new InterestEntity();
		
		entity.setInterestId(request.getInterestId());
		entity.setInterestedUser(createUserEntityFromId(request.getInterestedUser()));
		
		if(request.getSwappableItemIds() != null)
		entity.setSwappableItemId(createListingEntityFromItemId(request.getSwappableItemIds().get(0)));
			
		entity.setOriginalItemId(createListingEntityFromItemId(request.getOriginalItem()));
		entity.setOriginalUser(createUserEntityFromId(request.getOriginalUser()));
		entity.setOneSidedInterestFlag(request.getOneSidedInterestFlag());
		entity.setTwoSidedInterestFlag(request.getTwoSidedInterestFlag());
		entity.setSwapConfirmed(request.getSwapConfirmed());
		entity.setTransactionId(request.getTransactionId());
		return entity;
	}
	
}
