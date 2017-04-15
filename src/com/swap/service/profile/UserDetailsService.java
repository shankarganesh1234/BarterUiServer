package com.swap.service.profile;

import com.swap.models.user.UserResponse;
import com.swap.models.userdetails.UserDetailsRequest;

public interface UserDetailsService {
	UserResponse createUserDetails(UserDetailsRequest userDetails);
	UserResponse updateUserDetails(UserDetailsRequest userDetails);
}
