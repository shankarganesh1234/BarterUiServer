package com.swap.validator.userdetails;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.swap.common.error.ErrorEnum;
import com.swap.common.exceptions.SwapException;
import com.swap.entity.user.UserEntity;
import com.swap.models.userdetails.UserDetailsRequest;

@Service
public class UserDetailsValidatorImpl implements UserDetailsValidator {
	
	@Override
	public void validateUpdateRequest(UserDetailsRequest userDetailsRequest) {
		if(userDetailsRequest == null) {
			throw new SwapException(ErrorEnum.USER_CANNOT_BE_EMPTY);
		}
	}

	@Override
	public void validateForNull(String input) {
		if(input == null || StringUtils.isEmpty(input)) {
			throw new SwapException(ErrorEnum.USER_NAME_CANNOT_BE_EMPTY);
		}
	}

	@Override
	public void validateResponse(UserEntity entity) {
		if(entity == null) {
			throw new SwapException(ErrorEnum.USER_NOT_FOUND);
		}		
	}

	@Override
	public void validateInsertRequest(UserDetailsRequest userDetails) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void validateUserName(String userName) {
		// TODO Auto-generated method stub
		
	}
}
