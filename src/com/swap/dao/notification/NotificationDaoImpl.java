package com.swap.dao.notification;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

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
			sessionFactory.getCurrentSession().save(notificationEntity);
			result = true;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	@Override
	public boolean updateStatusToRead(String id, NotificationStatusEnum notificationStatus) {

		boolean result = false;
		try {
			NotificationEntity entity = sessionFactory.getCurrentSession().get(NotificationEntity.class, id);
			entity.setStatus(notificationStatus.name());
			sessionFactory.getCurrentSession().save(entity);
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
		NotificationEntity notification = new NotificationEntity();
		notification.setUserId(userId);
		criteriaQuery.where(builder.equal(notificationEntityRoot.get("userId"), notification));

		// execute
		List<NotificationEntity> notifications = sessionFactory.getCurrentSession().createQuery(criteriaQuery)
				.getResultList();
		return notifications;
	}
}
