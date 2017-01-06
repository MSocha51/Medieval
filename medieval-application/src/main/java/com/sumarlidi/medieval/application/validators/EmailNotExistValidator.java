package com.sumarlidi.medieval.application.validators;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.sumarlidi.medieval.application.services.UserService;

public class EmailNotExistValidator implements ConstraintValidator<EmailNotExist, String> {

	@Autowired
	private UserService service;
	
	
	@Override
	public void initialize(EmailNotExist userAnnotation) {
	}

	@Override
	public boolean isValid(String userEmail, ConstraintValidatorContext constraintContext) {
		if(service.ifUserEmailExist(userEmail)){
			return false;
		}
		else{
			return true;
		}
	}

}
