package com.swap.dao.location;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.SessionFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.swap.entity.location.LocationEntity;

@Component
public class LocationDaoImpl implements LocationDao {

	@Inject
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void createLocation(LocationEntity entity) {
		sessionFactory.getCurrentSession().save(entity);
	}

	@Override
	public void updateLocation(LocationEntity entity) {
		LocationEntity dbRecord = sessionFactory.getCurrentSession().load(LocationEntity.class, entity.getZipCode());
		BeanUtils.copyProperties(entity, dbRecord);
		sessionFactory.getCurrentSession().update(dbRecord);
	}

	@Override
	public void deleteLocation(Long zipCode) {
		Query query = sessionFactory.getCurrentSession().createQuery("delete LocationEntity where zipCode = :zip");
		query.setParameter("zip", zipCode);
		query.executeUpdate();
	}

	@Override
	public LocationEntity getLocation(Long zipCode) {
		return sessionFactory.getCurrentSession().get(LocationEntity.class, zipCode);
	}

	@Override
	public List<LocationEntity> getLocations() {
		// Create CriteriaBuilder
		CriteriaBuilder builder = sessionFactory.getCurrentSession().getCriteriaBuilder();
		// Create CriteriaQuery
		CriteriaQuery<LocationEntity> criteriaQuery = builder.createQuery(LocationEntity.class);

		// Add conditions
		Root<LocationEntity> locationEntityRoot = criteriaQuery.from(LocationEntity.class);
		criteriaQuery.select(locationEntityRoot);
		List<LocationEntity> locations = sessionFactory.getCurrentSession().createQuery(criteriaQuery).getResultList();
		return locations;
	}

}
