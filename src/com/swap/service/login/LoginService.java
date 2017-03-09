package com.swap.service.login;

import org.springframework.social.connect.UserProfile;

import com.swap.models.login.LoginRequest;

public interface LoginService {
	UserProfile connectUser(LoginRequest inputRequest, String providerId);

}
