package com.swap.transformer.login;

import org.springframework.social.connect.UserProfile;

import com.swap.models.login.LoginRequest;

public interface LoginTransformer {
	
	UserProfile connectToProvider(LoginRequest inputRequest, String providerId);

}
