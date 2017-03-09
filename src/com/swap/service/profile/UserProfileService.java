package com.swap.service.profile;

import com.swap.models.profile.UserProfileRequest;
import com.swap.models.profile.UserProfileResponse;

public interface UserProfileService {
	
	UserProfileResponse getUserProfile(String userName);
	UserProfileResponse createUserProfile(UserProfileRequest user);
	UserProfileResponse updateUserProfile(UserProfileRequest user);
	void deleteUserProfile(String userName);

}
