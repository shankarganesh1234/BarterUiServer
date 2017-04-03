package com.swap.dao.user;

import com.swap.entity.user.UserEntity;

public interface UserDao {

	UserEntity getUserById(String userId);
	Boolean deleteUserByUserId(String userId);
}
