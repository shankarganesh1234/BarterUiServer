package com.swap.dao.interest;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.collections.CollectionUtils;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import com.swap.common.CommonUtil;
import com.swap.entity.interest.InterestEntity;
import com.swap.entity.item.ItemEntity;
import com.swap.entity.user.UserEntity;

@Service
public class InterestDaoImpl implements InterestDao {

	@Inject
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public InterestEntity createInterested(InterestEntity entity) {
		createOrUpdateInterest(entity);
		return entity;
		
	}
	
	/**
	 * Check if an interest already exists for the item by the interested user.
	 * If yes, then update
	 * If no, then create
	 * @param entity
	 */
	public void createOrUpdateInterest(InterestEntity entity) {

		// check if an interest already exists, then create new or update
		// existing
		// Create CriteriaBuilder
		String origUserId = String.valueOf(entity.getOriginalUser().getUserId());
		String interestedUserId = String.valueOf(entity.getInterestedUser().getUserId());
		Long originalItemId = entity.getOriginalItemId().getItemId();
		
		
		// Create CriteriaBuilder
		CriteriaBuilder builder = sessionFactory.getCurrentSession().getCriteriaBuilder();
		// Create CriteriaQuery
		CriteriaQuery<InterestEntity> criteriaQuery = builder.createQuery(InterestEntity.class);

		// Add conditions
		Root<InterestEntity> interestEntityRoot = criteriaQuery.from(InterestEntity.class);
		criteriaQuery.select(interestEntityRoot);
				
		UserEntity originalUser = new UserEntity();
		originalUser.setUserId(origUserId);
		
		UserEntity interestedUser = new UserEntity();
		interestedUser.setUserId(interestedUserId);
		
		ItemEntity originalItem = new ItemEntity();
		originalItem.setItemId(originalItemId);
		
		Predicate where = builder.conjunction();
		where = builder.and(where, builder.equal(interestEntityRoot.get("originalUser"), originalUser));
		where = builder.and(where, builder.equal(interestEntityRoot.get("interestedUser"), interestedUser));
		where = builder.and(where, builder.equal(interestEntityRoot.get("originalItemId"), originalItem));
		criteriaQuery.where(where);
		// execute
		List<InterestEntity> interests = sessionFactory.getCurrentSession().createQuery(criteriaQuery).getResultList();
		
		if(CollectionUtils.isEmpty(interests)) {
			entity.setUpsertDate(CommonUtil.getCurrentDate());
			sessionFactory.getCurrentSession().saveOrUpdate(entity);
		} else {
			InterestEntity existingInterest = interests.get(0);
			existingInterest.setSwappableItemId(entity.getSwappableItemId());
			existingInterest.setUpsertDate(CommonUtil.getCurrentDate());
			sessionFactory.getCurrentSession().saveOrUpdate(existingInterest);
		}
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public InterestEntity updateInterested(InterestEntity entity) {
		// update timestamp
		entity.setUpsertDate(CommonUtil.getCurrentDate());
		
		InterestEntity dbRecord = sessionFactory.getCurrentSession().load(InterestEntity.class, entity.getInterestId());
		copyProperties(entity, dbRecord);
		checkActiveInterests(dbRecord);
		sessionFactory.getCurrentSession().update(dbRecord);
		return entity;
	}

	@Override
	public void deleteInterest(Long id) {
		Query query = sessionFactory.getCurrentSession().createQuery("delete InterestEntity where interestId = :id");
		query.setParameter("id", id);
		query.executeUpdate();
	}

	@Override
	public InterestEntity getInterestById(Long id) {
		return sessionFactory.getCurrentSession().get(InterestEntity.class, id);
	}

	@Override
	public List<InterestEntity> getInterestsByInterestedUser(Long userId) {

		// Create CriteriaBuilder
		String userIdStr = String.valueOf(userId);
		CriteriaBuilder builder = sessionFactory.getCurrentSession().getCriteriaBuilder();
		// Create CriteriaQuery
		CriteriaQuery<InterestEntity> criteriaQuery = builder.createQuery(InterestEntity.class);

		// Add conditions
		Root<InterestEntity> interestEntityRoot = criteriaQuery.from(InterestEntity.class);
		criteriaQuery.select(interestEntityRoot);
		UserEntity user = new UserEntity();
		user.setUserId(userIdStr);
		criteriaQuery.where(builder.equal(interestEntityRoot.get("interestedUser"), user));

		// execute
		List<InterestEntity> interests = sessionFactory.getCurrentSession().createQuery(criteriaQuery).getResultList();
		return interests;
	}

	@Override
	public List<InterestEntity> getInterestedByItemOwnerUser(Long userId) {

		String userIdStr = String.valueOf(userId);
		// Create CriteriaBuilder
		CriteriaBuilder builder = sessionFactory.getCurrentSession().getCriteriaBuilder();
		// Create CriteriaQuery
		CriteriaQuery<InterestEntity> criteriaQuery = builder.createQuery(InterestEntity.class);

		// Add conditions
		Root<InterestEntity> interestEntityRoot = criteriaQuery.from(InterestEntity.class);
		criteriaQuery.select(interestEntityRoot);
		UserEntity user = new UserEntity();
		user.setUserId(userIdStr);
		criteriaQuery.where(builder.equal(interestEntityRoot.get("originalUser"), user));
		// execute
		List<InterestEntity> interests = sessionFactory.getCurrentSession().createQuery(criteriaQuery).getResultList();
		return interests;
	}

	/**
	 * Copy relevant properties from request
	 * 
	 * @param source
	 * @param destination
	 */
	private void copyProperties(InterestEntity source, InterestEntity destination) {
		if (source == null || destination == null)
			return;

		if (source.getOneSidedInterestFlag() != null)
			destination.setOneSidedInterestFlag(source.getOneSidedInterestFlag());

		if (source.getSwapConfirmed() != null)
			destination.setSwapConfirmed(source.getSwapConfirmed());

		if (source.getTwoSidedInterestFlag() != null)
			destination.setTwoSidedInterestFlag(source.getTwoSidedInterestFlag());
	}

	/**
	 * Update active interests flag in item table, if one/two sided interest
	 * flag for a particular interest is true
	 * 
	 * @param record
	 */
	private void checkActiveInterests(InterestEntity record) {

		if (record.getOneSidedInterestFlag() == true && record.getTwoSidedInterestFlag() == true) {
			record.getOriginalItemId().setActiveInterests(true);
			record.getSwappableItemId().setActiveInterests(true);
		} else {
			record.getOriginalItemId().setActiveInterests(false);
			record.getSwappableItemId().setActiveInterests(false);
		}
	}

	@Override
	public List<InterestEntity> getInterests(String userId, String itemId, boolean isOwner) {
		// Create CriteriaBuilder
		CriteriaBuilder builder = sessionFactory.getCurrentSession().getCriteriaBuilder();
		// Create CriteriaQuery
		CriteriaQuery<InterestEntity> criteriaQuery = builder.createQuery(InterestEntity.class);

		// Add conditions
		Root<InterestEntity> interestEntityRoot = criteriaQuery.from(InterestEntity.class);
		criteriaQuery.select(interestEntityRoot);
		
		UserEntity user = new UserEntity();
		user.setUserId(userId);
		
		ItemEntity item = new ItemEntity();
		item.setItemId(Long.valueOf(itemId));
		
		
		Predicate where = builder.conjunction();
		where = builder.and(where, builder.equal(interestEntityRoot.get("originalItemId"), item));
		
		if (isOwner)
			where = builder.and(where, builder.equal(interestEntityRoot.get("originalUser"), user));
		else
			where = builder.and(where, builder.equal(interestEntityRoot.get("interestedUser"), user));

		criteriaQuery.where(where);
		
		// execute
		List<InterestEntity> interests = sessionFactory.getCurrentSession().createQuery(criteriaQuery).getResultList();
		return interests;
	}

}
