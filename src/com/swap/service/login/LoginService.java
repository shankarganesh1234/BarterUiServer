package com.swap.service.login;

import org.springframework.social.connect.UserProfile;

import com.swap.models.login.FbLongLivedTokenResponse;
import com.swap.models.login.LoginRequest;

public interface LoginService {
	UserProfile connectUser(LoginRequest inputRequest, String providerId);
	boolean test(String providerId, String accessToken);
	boolean hasExpired(String providerId, String accessToken);
	boolean refresh(String providerId, String accessToken);
	FbLongLivedTokenResponse getLongLivedToken(String accessToken);
}
