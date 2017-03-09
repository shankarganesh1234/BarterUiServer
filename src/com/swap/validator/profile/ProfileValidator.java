package com.swap.validator.profile;

import com.swap.entity.common.UserEntity;
import com.swap.models.profile.UserProfileRequest;

public interface ProfileValidator {
	
	void validateInsertRequest(UserProfileRequest userProfile);
	void validateUpdateRequest(UserProfileRequest userProfile);
	void validateUserName(String userName);
	void validateResponse(UserEntity entity);
	void validateForNull(String input);

}
