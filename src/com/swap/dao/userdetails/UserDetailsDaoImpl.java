package com.swap.dao.userdetails;

import javax.inject.Inject;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import com.swap.entity.user.UserDetailsEntity;
import com.swap.entity.user.UserEntity;

@Service
public class UserDetailsDaoImpl implements UserDetailsDao {

	@Inject
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public UserEntity createUserDetails(UserDetailsEntity userDetails) {
		sessionFactory.getCurrentSession().save(userDetails);
		String userId = userDetails.getUserId();
		return sessionFactory.getCurrentSession().get(UserEntity.class, userId);
	}

	@Override
	public UserEntity updateUserDetails(UserDetailsEntity userDetails) {
		sessionFactory.getCurrentSession().update(userDetails);
		String userId = userDetails.getUserId();
		return sessionFactory.getCurrentSession().get(UserEntity.class, userId);
	}

}
