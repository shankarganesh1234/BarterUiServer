package com.swap.transformer.user;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.swap.entity.user.UserEntity;
import com.swap.models.user.UserResponse;

@Service
public class UserTransformerImpl implements UserTransformer{

	@Override
	public UserResponse txUserEntity(UserEntity userEntity) {
		UserResponse userResponse = new UserResponse();
		BeanUtils.copyProperties(userEntity, userResponse);
		return userResponse;
	}
}
