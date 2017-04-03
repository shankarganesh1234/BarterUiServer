package com.swap.dao.user;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.TypedQuery;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;

import com.swap.entity.user.UserEntity;

@Service
public class UserDaoImpl implements UserDao {

	@Inject
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * Get an existing User
	 */

	@Override
	public UserEntity getUserById(String userId) {
		@SuppressWarnings("unchecked")
		TypedQuery<UserEntity> query = sessionFactory.getCurrentSession()
				.createQuery("FROM UserEntity where userId = :userId");
		query.setParameter("userId", userId);
		List<UserEntity> result = query.getResultList();
		return result.get(0);
	}

	/**
	 * Delete a user
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Boolean deleteUserByUserId(String userId) {
		try {
			Query<UserEntity> q = sessionFactory.getCurrentSession().createQuery("delete UserEntity where user_id = " + userId);
			q.executeUpdate();
			return Boolean.TRUE;
		} catch (Exception ex) {
			return Boolean.FALSE;
		}
	}

}
