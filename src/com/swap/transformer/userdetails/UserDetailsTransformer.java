package com.swap.transformer.userdetails;

import com.swap.entity.user.UserDetailsEntity;
import com.swap.entity.user.UserEntity;
import com.swap.models.user.UserResponse;
import com.swap.models.userdetails.UserDetailsRequest;

public interface UserDetailsTransformer {
	
	UserDetailsEntity txUserDetailsRequest(UserDetailsRequest userDetailsRequest);
	UserResponse txUserEntity(UserEntity userEntity);
}
