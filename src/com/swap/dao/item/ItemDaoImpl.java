package com.swap.dao.item;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.SessionFactory;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;
import org.springframework.stereotype.Service;

import com.swap.common.components.CommonBeanUtils;
import com.swap.db.listeners.ItemEntityInterceptor;
import com.swap.entity.item.ImageEntity;
import com.swap.entity.item.ItemEntity;

@Service
public class ItemDaoImpl implements ItemDao {

	@Inject
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Inject
	private ItemEntityInterceptor listener;
	
	@Inject
	private CommonBeanUtils commonBeanUtils;

	@PostConstruct
	public void init() {
		EventListenerRegistry registry = sessionFactory.getSessionFactory().getServiceRegistry()
				.getService(EventListenerRegistry.class);
		registry.getEventListenerGroup(EventType.POST_INSERT).appendListener(this.listener);
		registry.getEventListenerGroup(EventType.POST_UPDATE).appendListener(this.listener);
		registry.getEventListenerGroup(EventType.POST_DELETE).appendListener(this.listener);
	}

	@Override
	public ItemEntity getListingByItemId(ItemEntity listingEntity) {
		return sessionFactory.getCurrentSession().get(ItemEntity.class, listingEntity.getItemId());
	}

	@Override
	public List<ItemEntity> getListingsByUserId(ItemEntity listingEntity) {
		@SuppressWarnings("unchecked")
		TypedQuery<ItemEntity> query = sessionFactory.getCurrentSession()
				.createQuery("FROM ItemEntity where user_id = :userId");
		query.setParameter("userId", listingEntity.getUserId());
		List<ItemEntity> result = query.getResultList();
		return result;
	}

	@Override
	public void createListing(ItemEntity listingEntity) {
		sessionFactory.getCurrentSession().save(listingEntity);
	}

	@Override
	public void updateListing(ItemEntity listingEntity) {
		ItemEntity dbRecord = sessionFactory.getCurrentSession().get(ItemEntity.class, listingEntity.getItemId());
		sessionFactory.getCurrentSession().update(createEntityForUpdate(listingEntity, dbRecord));
	}

	@Override
	public void deleteListing(ItemEntity listingEntity) {
		sessionFactory.getCurrentSession().delete(listingEntity);
	}

	@Override
	public List<Long> getListingIdsByUserId(Long userId) {

		CriteriaBuilder builder = sessionFactory.getCurrentSession().getCriteriaBuilder();
		// Create CriteriaQuery
		CriteriaQuery<Long> criteriaQuery = builder.createQuery(Long.class);

		// Add conditions
		Root<ItemEntity> listingEntityRoot = criteriaQuery.from(ItemEntity.class);
		criteriaQuery.select(listingEntityRoot.get("itemId"));
		criteriaQuery.where(builder.equal(listingEntityRoot.get("userId"), userId));
		// execute
		List<Long> interests = sessionFactory.getCurrentSession().createQuery(criteriaQuery).getResultList();
		return interests;
	}

	@Override
	public void updateListingForImage(Long itemId, ImageEntity imageEntity) {
		ItemEntity dbRecord = sessionFactory.getCurrentSession().get(ItemEntity.class, itemId);
		dbRecord.setImage_id(imageEntity);
		sessionFactory.getCurrentSession().update(dbRecord);
	}
	
	/**
	 * 
	 * @param source
	 * @param destination
	 * @return
	 */
	private ItemEntity createEntityForUpdate(ItemEntity source, ItemEntity destination) {
		commonBeanUtils.nullAwareBeanCopy(destination, source);
		return destination;
	}
}