package com.swap.validator.login;

import com.swap.models.login.LoginRequest;

public interface LoginValidator {
	
	void validateProviderId(String userName);
	
	void validateInputRequest(LoginRequest userName);



}
