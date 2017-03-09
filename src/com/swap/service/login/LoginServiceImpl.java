package com.swap.service.login;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.UserProfile;
import org.springframework.stereotype.Service;

import com.swap.models.login.LoginRequest;
import com.swap.transformer.login.LoginTransformer;
import com.swap.validator.login.LoginValidator;

@Service
public class LoginServiceImpl implements LoginService {
	

	@Inject
	private LoginTransformer loginTransformer;

	@Inject
	private LoginValidator loginValidator;

	@Autowired
	protected static LoginService loginService;


	@Override
	public UserProfile connectUser(LoginRequest inputRequest, String providerId) {
		loginValidator.validateProviderId(providerId);
		loginValidator.validateInputRequest(inputRequest);
		
		

		return loginTransformer.connectToProvider(inputRequest, providerId);
	}

}
