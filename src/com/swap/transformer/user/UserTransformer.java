package com.swap.transformer.user;

import com.swap.entity.user.UserEntity;
import com.swap.models.user.UserResponse;

public interface UserTransformer {

	UserResponse txUserEntity(UserEntity userEntity);
}
