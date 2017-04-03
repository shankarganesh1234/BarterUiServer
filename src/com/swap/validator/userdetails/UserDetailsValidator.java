package com.swap.validator.userdetails;

import com.swap.entity.user.UserEntity;
import com.swap.models.userdetails.UserDetailsRequest;

public interface UserDetailsValidator {
	
	void validateInsertRequest(UserDetailsRequest userDetails);
	void validateUpdateRequest(UserDetailsRequest userDetails);
	void validateUserName(String userName);
	void validateResponse(UserEntity entity);
	void validateForNull(String input);

}
