package com.swap.dao.review;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import com.swap.entity.review.ReviewEntity;

@Component
public class ReviewDaoImpl implements ReviewDao {

	@Inject
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void createReview(ReviewEntity entity) {
		sessionFactory.getCurrentSession().save(entity);

	}

	@Override
	public void updateReview(ReviewEntity entity) {
		ReviewEntity dbRecord = sessionFactory.getCurrentSession().load(ReviewEntity.class, entity.getReviewId());
		copyProperties(entity, dbRecord);
		sessionFactory.getCurrentSession().update(dbRecord);
	}

	@Override
	public ReviewEntity getReviewByReviewId(Long reviewId) {
		return sessionFactory.getCurrentSession().get(ReviewEntity.class, reviewId);
	}

	@Override
	public List<ReviewEntity> getReviewsForUser(String userId) {
		// Create CriteriaBuilder
		CriteriaBuilder builder = sessionFactory.getCurrentSession().getCriteriaBuilder();
		// Create CriteriaQuery
		CriteriaQuery<ReviewEntity> criteriaQuery = builder.createQuery(ReviewEntity.class);

		// Add conditions
		Root<ReviewEntity> reviewEntityRoot = criteriaQuery.from(ReviewEntity.class);
		criteriaQuery.select(reviewEntityRoot);
		criteriaQuery.where(builder.equal(reviewEntityRoot.get("reviewFor"), userId));
		// execute
		List<ReviewEntity> reviews = sessionFactory.getCurrentSession().createQuery(criteriaQuery).getResultList();
		return reviews;

	}

	@Override
	public List<ReviewEntity> getReviewsByUser(String userId) {
		// Create CriteriaBuilder
		CriteriaBuilder builder = sessionFactory.getCurrentSession().getCriteriaBuilder();
		// Create CriteriaQuery
		CriteriaQuery<ReviewEntity> criteriaQuery = builder.createQuery(ReviewEntity.class);

		// Add conditions
		Root<ReviewEntity> reviewEntityRoot = criteriaQuery.from(ReviewEntity.class);
		criteriaQuery.select(reviewEntityRoot);
		criteriaQuery.where(builder.equal(reviewEntityRoot.get("reviewBy"), userId));
		// execute
		List<ReviewEntity> reviews = sessionFactory.getCurrentSession().createQuery(criteriaQuery).getResultList();
		return reviews;
	}

	@Override
	public List<ReviewEntity> getReviewsForUserByUser(String forUser, String byUser) {
		
		List<Predicate> predicates = new ArrayList<Predicate>(2);
		
		
		// Create CriteriaBuilder
		CriteriaBuilder builder = sessionFactory.getCurrentSession().getCriteriaBuilder();
		
		// Create CriteriaQuery
		CriteriaQuery<ReviewEntity> criteriaQuery = builder.createQuery(ReviewEntity.class);

		// Add conditions
		Root<ReviewEntity> reviewEntityRoot = criteriaQuery.from(ReviewEntity.class);
		
		// create multiple predicates for condition where a=? AND b=?
		predicates.add(
				builder.equal(reviewEntityRoot.get("reviewBy"), byUser));
		
		predicates.add(
				builder.equal(reviewEntityRoot.get("reviewFor"), forUser));
		
		criteriaQuery.select(reviewEntityRoot);
		criteriaQuery.where(predicates.toArray(new Predicate[]{}));
		// execute
		List<ReviewEntity> reviews = sessionFactory.getCurrentSession().createQuery(criteriaQuery).getResultList();
		return reviews;
	}

	@Override
	public void deleteReviewByReviewId(Long id) {
		Query query = sessionFactory.getCurrentSession().createQuery("delete ReviewEntity where reviewId = :id");
		query.setParameter("id", id);
		query.executeUpdate();
	}

	@Override
	public void deleteReviewsForUser(Long userId) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteReviewsByUser(Long userId) {
		// TODO Auto-generated method stub

	}

	/**
	 * Copy relevant properties from request
	 * 
	 * @param source
	 * @param destination
	 */
	private void copyProperties(ReviewEntity source, ReviewEntity destination) {
		if (source == null || destination == null)
			return;

		if (source.getComments() != null)
			destination.setComments(source.getComments());

		if (source.getFeedbackScore() != null)
			destination.setFeedbackScore(source.getFeedbackScore());

		if (source.getReview() != null)
			destination.setReview(source.getReview());
	}

}
