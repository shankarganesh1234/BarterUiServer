package com.swap.dao.item;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.TypedQuery;

import org.hibernate.SessionFactory;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;
import org.springframework.stereotype.Service;

import com.swap.common.components.CommonBeanUtils;
import com.swap.db.listeners.ItemEntityInterceptor;
import com.swap.entity.item.ImageEntity;
import com.swap.entity.item.ItemEntity;
import com.swap.entity.user.UserEntity;

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
				.createQuery("FROM ItemEntity where userId = :userId");
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
	public List<Long> getListingIdsByUserId(String userId) {

		UserEntity userEntity = new UserEntity();
		userEntity.setUserId(userId);
		@SuppressWarnings("unchecked")
		TypedQuery<Long> query = sessionFactory.getCurrentSession().createQuery("select itemEntity.itemId from ItemEntity itemEntity where itemEntity.userId = :userId").setParameter("userId", userEntity);
		List<Long> items = query.getResultList();
		return items;
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