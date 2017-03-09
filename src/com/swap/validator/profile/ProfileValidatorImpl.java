package com.swap.validator.profile;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.swap.common.error.ErrorEnum;
import com.swap.common.exceptions.SwapException;
import com.swap.entity.common.UserEntity;
import com.swap.models.profile.UserProfileRequest;

@Service
public class ProfileValidatorImpl implements ProfileValidator {
	
	private static Pattern pattern ;
	private static Matcher matcher ;
	private static final String EMAIL_PATTERN =
			"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	private static final String USER_NAME_PATTERN = "^[^~!@#$]+$";
	

	@Override
	public void validateInsertRequest(UserProfileRequest userProfileRequest) {
		if(userProfileRequest == null)
			throw new SwapException(ErrorEnum.USER_CANNOT_BE_EMPTY);
	
		
		validateUserName(userProfileRequest.getUser().getUserName());
		validateForFirstName(userProfileRequest.getUser().getFirstName());
		validateForLastName(userProfileRequest.getUser().getLastName());
		validateEmail(userProfileRequest.getUser().getEmail());
		
	}


	private void validateEmail(String email) {

		validateForNull(email);
		pattern = Pattern.compile(EMAIL_PATTERN, Pattern.CASE_INSENSITIVE);
		matcher = pattern.matcher(email);
		boolean isValid = matcher.find();
		if(!isValid) 
		{
			throw new SwapException(ErrorEnum.INVALID_EMAIL_ID);
		}

	}


	private void validateForLastName(String lastName) {
		if(lastName == null || StringUtils.isEmpty(lastName)) {
			throw new SwapException(ErrorEnum.LAST_NAME_CANNOT_BE_EMPTY);
		}		
	}


	private void validateForFirstName(String firstName) {
		if(firstName == null || StringUtils.isEmpty(firstName)) {
			throw new SwapException(ErrorEnum.FIRST_NAME_CANNOT_BE_EMPTY);
		}
	}


	@Override
	public void validateUpdateRequest(UserProfileRequest userProfileRequest) {
		if(userProfileRequest == null) {
			throw new SwapException(ErrorEnum.USER_CANNOT_BE_EMPTY);
		}		
		validateUserName(userProfileRequest.getUser().getUserName());

		
	}

	@Override
	public void validateUserName(String userName) {
		
		validateForNull(userName);
		
		pattern = Pattern.compile(USER_NAME_PATTERN, Pattern.CASE_INSENSITIVE);
		matcher = pattern.matcher(userName);
		boolean isValid = matcher.find();

		
		if(!isValid) 
		{
			throw new SwapException(ErrorEnum.INVALID_USER_NAME);
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
	

}
