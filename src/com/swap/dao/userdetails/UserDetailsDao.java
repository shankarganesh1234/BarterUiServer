package com.swap.dao.userdetails;

import com.swap.entity.user.UserDetailsEntity;
import com.swap.entity.user.UserEntity;

public interface UserDetailsDao {
	UserEntity createUserDetails(UserDetailsEntity userDetails);
	UserEntity updateUserDetails(UserDetailsEntity userDetails);
}
