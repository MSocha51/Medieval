package com.sumarlidi.medieval.application.validators;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.sumarlidi.medieval.application.services.UserService;

public class UserNotExistValidator implements ConstraintValidator<UserNotExist, String> {

	@Autowired
	private UserService service;
	
	
	@Override
	public void initialize(UserNotExist userAnnotation) {
	}

	@Override
	public boolean isValid(String userNick, ConstraintValidatorContext constraintContext) {
		if(service.ifUserNickExist(userNick)){
			return false;
		}
		else{
			return true;
		}
	}

}
