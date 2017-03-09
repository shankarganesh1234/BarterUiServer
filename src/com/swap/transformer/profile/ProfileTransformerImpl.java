package com.swap.transformer.profile;

import java.util.List;

import org.springframework.stereotype.Service;

import com.swap.entity.common.UserEntity;
import com.swap.models.profile.UserProfileRequest;
import com.swap.models.profile.UserProfileResponse;

@Service
public class ProfileTransformerImpl implements ProfileTransformer{

	@Override
	public UserEntity createUserProfileEntity(UserProfileRequest userProfileRequest) {
		UserEntity userEntity = new UserEntity();
		userEntity = userProfileRequest.getUser();	
 		return userEntity;
	}

	@Override
	public UserProfileResponse createUserProfileResponse(UserEntity entity) {
		UserProfileResponse userProfileResponse = new UserProfileResponse();
		userProfileResponse.setUser(entity);
		return userProfileResponse;
	}

	@Override
	public UserEntity updateUserEntity(UserProfileRequest userProfileRequest) {
		UserEntity userEntity = new UserEntity();
		userEntity = userProfileRequest.getUser();	
 		return userEntity;
	}

	@Override
	public List<UserProfileResponse> convertToUserProfile(List<UserEntity> userEntities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserEntity createUserProfileEntityFromUserName(String userName) {
		UserEntity userEntity = new UserEntity();
		userEntity.setUserName(userName);
		return userEntity;
	}

}
