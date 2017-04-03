package com.swap.service.profile;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.swap.dao.userdetails.UserDetailsDao;
import com.swap.entity.user.UserDetailsEntity;
import com.swap.entity.user.UserEntity;
import com.swap.models.user.UserResponse;
import com.swap.models.userdetails.UserDetailsRequest;
import com.swap.transformer.userdetails.UserDetailsTransformer;
import com.swap.validator.userdetails.UserDetailsValidator;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Inject
	private UserDetailsDao userDetailsDao;

	@Inject
	private UserDetailsTransformer userDetailsTransformer;

	@Inject
	private UserDetailsValidator userDetailsValidator;

	@Transactional
	@Override
	public UserResponse createUserDetails(UserDetailsRequest userDetails) {
		// Validate request
		userDetailsValidator.validateInsertRequest(userDetails);

		// tx request
		UserDetailsEntity entity = userDetailsTransformer.txUserDetailsRequest(userDetails);

		// call DAO
		UserEntity userEntity = userDetailsDao.createUserDetails(entity);
		
		return userDetailsTransformer.txUserEntity(userEntity);
	}

	@Transactional
	@Override
	public UserResponse updateUserDetails(UserDetailsRequest userDetails) {
		// Validate request
		userDetailsValidator.validateInsertRequest(userDetails);

		// tx request
		UserDetailsEntity entity = userDetailsTransformer.txUserDetailsRequest(userDetails);

		// call DAO
		UserEntity userEntity = userDetailsDao.updateUserDetails(entity);
		
		return userDetailsTransformer.txUserEntity(userEntity);
	}
}
