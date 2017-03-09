package com.swap.dao.user;

import java.util.List;

import com.swap.entity.common.UserEntity;

public interface UserDao {

	UserEntity getUserById(UserEntity userEntity);
	UserEntity getUserByUserName(UserEntity userEntity);
	UserEntity createUser(UserEntity userEntity);
	UserEntity updateUser(UserEntity userEntity);
	void deleteUserByUserId(UserEntity userEntity);
	void deleteUserByUserName(UserEntity userEntity);

	List<UserEntity> getAllUsers();
}
