package com.swap.validator.login;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.swap.common.error.ErrorEnum;
import com.swap.common.exceptions.SwapException;
import com.swap.models.login.LoginRequest;
@Service
public class LoginValidatorImpl implements LoginValidator{

	@Override
	public void validateProviderId(String providerId) {
		if(providerId == null || StringUtils.isEmpty(providerId)) {
			throw new SwapException(ErrorEnum.PROVIDER_ID_EMPTY);
		}
	}

	@Override
	public void validateInputRequest(LoginRequest inputRequest) {
		if(inputRequest == null)
			throw new SwapException(ErrorEnum.ACCESS_TOKEN_EMPTY);
	}

}
