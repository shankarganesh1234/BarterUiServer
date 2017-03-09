package com.swap.dao.user;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.TypedQuery;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import com.swap.entity.common.UserEntity;

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
	public UserEntity getUserById(UserEntity inputEntity) {

		return sessionFactory.getCurrentSession().get(UserEntity.class, inputEntity.getUserName());

	}

	/**
	 * Create a new user
	 */
	@Override
	public UserEntity createUser(UserEntity inputEntity) {
		sessionFactory.getCurrentSession().save(inputEntity);
		return inputEntity;
	}

	/**
	 * Update an existing user
	 */

	@Override
	public UserEntity updateUser(UserEntity inputEntity) {
		sessionFactory.getCurrentSession().update(inputEntity);
		return inputEntity;

	}

	/**
	 * Delete a user
	 */
	@Override
	public void deleteUserByUserId(UserEntity inputEntity) {
		sessionFactory.getCurrentSession().delete(inputEntity);
	}

	/**
	 * Get all users
	 */
	@Override
	public List<UserEntity> getAllUsers() {
		@SuppressWarnings("unchecked")
		TypedQuery<UserEntity> query = sessionFactory.getCurrentSession().createQuery("FROM UserEntity"); 
		List<UserEntity> result = query.getResultList();
		return result;
	}

	@Override
	public UserEntity getUserByUserName(UserEntity userEntity) {
		 @SuppressWarnings("unchecked")
		TypedQuery<UserEntity> query = sessionFactory.getCurrentSession().createQuery("FROM UserEntity where user_name = :username");
	        query.setParameter("username", userEntity.getUserName());
	        return query.getSingleResult();
	}

	@Override
	public void deleteUserByUserName(UserEntity userEntity) {
		 @SuppressWarnings("unchecked")
			TypedQuery<UserEntity> query = sessionFactory.getCurrentSession().createQuery("DELETE FROM UserEntity where user_name = :username");
		        query.setParameter("username", userEntity.getUserName());
		        query.executeUpdate();
	}

}
