package com.swap.transformer.userdetails;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.swap.entity.user.UserDetailsEntity;
import com.swap.entity.user.UserEntity;
import com.swap.models.user.UserResponse;
import com.swap.models.userdetails.UserDetailsRequest;

@Service
public class UserDetailsTransformerImpl implements UserDetailsTransformer{

	@Override
	public UserDetailsEntity txUserDetailsRequest(UserDetailsRequest userDetailsRequest) {
		UserDetailsEntity userDetailsEntity = new UserDetailsEntity();
		userDetailsEntity.setAboutMe(userDetailsRequest.getAboutMe()); 
		userDetailsEntity.setStatus(userDetailsRequest.getStatus());
		userDetailsEntity.setUserId(userDetailsRequest.getUserId());
		return userDetailsEntity;
	}

	@Override
	public UserResponse txUserEntity(UserEntity userEntity) {
		UserResponse userResponse = new UserResponse();
		BeanUtils.copyProperties(userEntity, userResponse);
		return userResponse;
	}
}
