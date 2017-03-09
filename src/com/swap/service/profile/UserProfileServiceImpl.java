package com.swap.service.profile;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.swap.dao.user.UserDao;
import com.swap.entity.common.UserEntity;
import com.swap.models.profile.UserProfileRequest;
import com.swap.models.profile.UserProfileResponse;
import com.swap.transformer.profile.ProfileTransformer;
import com.swap.validator.profile.ProfileValidator;

@Service
public class UserProfileServiceImpl implements UserProfileService {

	@Inject
	private UserDao userDao;

	@Inject
	private ProfileTransformer profileTransformer;

	@Inject
	private ProfileValidator profileValidator;

	/**
	 * Get a category based on the category id Every request goes through the
	 * following steps 1. Request validation 2. Calling DAO and getting response
	 * 3. Response validation
	 */
	@Transactional
	@Override
	public UserProfileResponse getUserProfile(String userName) {

		// Validate request
		profileValidator.validateForNull(userName);

		// tx request
		UserEntity entity = profileTransformer.createUserProfileEntityFromUserName(userName);

		// call DAO
		entity = userDao.getUserByUserName(entity);

		// validate response
		profileValidator.validateResponse(entity);

		return profileTransformer.createUserProfileResponse(entity);

	}
	@Transactional
	@Override
	public UserProfileResponse createUserProfile(UserProfileRequest userProfile) {
		// Validate request
				profileValidator.validateInsertRequest(userProfile);

				// tx request
				UserEntity entity = profileTransformer.createUserProfileEntity(userProfile);

				// call DAO
				entity = userDao.createUser(entity);

				// validate response
				profileValidator.validateResponse(entity);

				return profileTransformer.createUserProfileResponse(entity);
	}
	
	@Transactional
	@Override
	public UserProfileResponse updateUserProfile(UserProfileRequest user) {
		UserProfileResponse userResponse = null;
		
		// validate request
        profileValidator.validateUpdateRequest(user);
		
		// tx request
		UserEntity entity = profileTransformer.updateUserEntity(user);
		
		// invoke dao
		entity = userDao.updateUser(entity);
		
		// validate response
		profileValidator.validateResponse(entity);
		
		// tx response
		userResponse = profileTransformer.createUserProfileResponse(entity);
		
		return userResponse;
	}
	
	@Transactional
	@Override
	public void deleteUserProfile(String userName) {
		// validate request
		profileValidator.validateUserName(userName);
		
		// tx request
		UserEntity entity = profileTransformer.createUserProfileEntityFromUserName(userName);
		
		// invoke DAO
		userDao.deleteUserByUserName(entity);
		
	}

}
