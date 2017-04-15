package com.swap.service.user;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.swap.dao.user.UserDao;
import com.swap.entity.user.UserEntity;
import com.swap.models.user.UserResponse;
import com.swap.transformer.user.UserTransformer;

@Service
public class UserServiceImpl implements UserService {

	@Inject
	private UserDao userDao;
	
	@Inject
	private UserTransformer userTransformer;
	
	@Transactional
	@Override
	public UserResponse getUserById(String userId) {
		UserEntity userEntity = userDao.getUserById(userId);
		return userTransformer.txUserEntity(userEntity);
	}

	@Transactional
	@Override
	public Boolean deleteUserByUserId(String userId) {
		return userDao.deleteUserByUserId(userId);
	}
}
