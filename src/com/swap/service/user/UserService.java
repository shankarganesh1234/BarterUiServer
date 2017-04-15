package com.swap.service.user;

import com.swap.models.user.UserResponse;

public interface UserService {

	UserResponse getUserById(String userId);
	Boolean deleteUserByUserId(String userId);
}
