package com.swap.dao.listing;

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

import com.swap.db.listeners.ListingEntityInterceptor;
import com.swap.entity.interest.InterestEntity;
import com.swap.entity.listing.ImageEntity;
import com.swap.entity.listing.ListingEntity;

@Service
public class ListingDaoImpl implements ListingDao {

	@Inject
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Inject
	private ListingEntityInterceptor listener;

	@PostConstruct
	public void init() {
		EventListenerRegistry registry = sessionFactory.getSessionFactory().getServiceRegistry()
				.getService(EventListenerRegistry.class);
		registry.getEventListenerGroup(EventType.POST_INSERT).appendListener(this.listener);
		registry.getEventListenerGroup(EventType.POST_UPDATE).appendListener(this.listener);
		registry.getEventListenerGroup(EventType.POST_DELETE).appendListener(this.listener);
	}

	@Override
	public ListingEntity getListingByItemId(ListingEntity listingEntity) {
		return sessionFactory.getCurrentSession().get(ListingEntity.class, listingEntity.getItemId());
	}

	@Override
	public List<ListingEntity> getListingsByUserId(ListingEntity listingEntity) {
		@SuppressWarnings("unchecked")
		TypedQuery<ListingEntity> query = sessionFactory.getCurrentSession()
				.createQuery("FROM ListingEntity where user_id = :userId");
		query.setParameter("userId", listingEntity.getUserId());
		List<ListingEntity> result = query.getResultList();
		return result;
	}

	@Override
	public void createListing(ListingEntity listingEntity) {
		sessionFactory.getCurrentSession().save(listingEntity);
	}

	@Override
	public void updateListing(ListingEntity listingEntity) {
		sessionFactory.getCurrentSession().update(listingEntity);
	}

	@Override
	public void deleteListing(ListingEntity listingEntity) {
		sessionFactory.getCurrentSession().delete(listingEntity);
	}

	@Override
	public List<Long> getListingIdsByUserId(Long userId) {

		CriteriaBuilder builder = sessionFactory.getCurrentSession().getCriteriaBuilder();
		// Create CriteriaQuery
		CriteriaQuery<Long> criteriaQuery = builder.createQuery(Long.class);

		// Add conditions
		Root<ListingEntity> listingEntityRoot = criteriaQuery.from(ListingEntity.class);
		criteriaQuery.select(listingEntityRoot.get("itemId"));
		criteriaQuery.where(builder.equal(listingEntityRoot.get("userId"), userId));
		// execute
		List<Long> interests = sessionFactory.getCurrentSession().createQuery(criteriaQuery).getResultList();
		return interests;
	}

	@Override
	public void updateListingForImage(Long itemId, ImageEntity imageEntity) {
		ListingEntity dbRecord = sessionFactory.getCurrentSession().load(ListingEntity.class, itemId);
		dbRecord.setImage_id(imageEntity);
		sessionFactory.getCurrentSession().update(dbRecord);
	}
}