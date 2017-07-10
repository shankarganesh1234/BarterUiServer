package com.swap.dao.notification;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import com.swap.common.CommonUtil;
import com.swap.common.enums.NotificationStatusEnum;
import com.swap.entity.notification.NotificationEntity;

@Component
public class NotificationDaoImpl implements NotificationDao {

	@Inject
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public boolean createInterest(NotificationEntity notificationEntity) {

		boolean result = false;
		try {
			notificationEntity.setCreateTimestamp(CommonUtil.getCurrentDate());
			sessionFactory.getCurrentSession().saveOrUpdate(notificationEntity);
			result = true;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	@Override
	public boolean updateStatusToRead(String interestId, String userId, NotificationStatusEnum notificationStatus) {

		boolean result = false;
		try {
			Query query = sessionFactory.getCurrentSession().createQuery("delete NotificationEntity where interestId = :interestId and userId = :userId");
			query.setParameter("interestId", interestId);
			query.setParameter("userId", userId);
			query.executeUpdate();
			result = true;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	@Override
	public List<NotificationEntity> getUnreadNotificationsForUser(String userId) {

		CriteriaBuilder builder = sessionFactory.getCurrentSession().getCriteriaBuilder();
		// Create CriteriaQuery
		CriteriaQuery<NotificationEntity> criteriaQuery = builder.createQuery(NotificationEntity.class);

		// Add conditions
		Root<NotificationEntity> notificationEntityRoot = criteriaQuery.from(NotificationEntity.class);
		criteriaQuery.select(notificationEntityRoot);
		
		criteriaQuery.where(builder.equal(notificationEntityRoot.get("userId"), userId));

		// execute
		List<NotificationEntity> notifications = sessionFactory.getCurrentSession().createQuery(criteriaQuery)
				.getResultList();
		return notifications;
	}
}
