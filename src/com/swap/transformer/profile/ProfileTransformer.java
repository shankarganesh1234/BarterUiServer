package com.swap.transformer.profile;

import java.util.List;

import com.swap.entity.common.UserEntity;
import com.swap.models.profile.UserProfileRequest;
import com.swap.models.profile.UserProfileResponse;

public interface ProfileTransformer {
	
	UserEntity createUserProfileEntity(UserProfileRequest userProfileRequest);
	UserProfileResponse createUserProfileResponse(UserEntity entity);
	UserEntity updateUserEntity(UserProfileRequest userProfileRequest);
	List<UserProfileResponse> convertToUserProfile(List<UserEntity> userEntities);
	UserEntity createUserProfileEntityFromUserName(String userName);


}
