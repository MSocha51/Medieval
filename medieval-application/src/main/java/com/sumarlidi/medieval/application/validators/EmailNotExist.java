package com.sumarlidi.medieval.application.validators;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target(FIELD)
@Retention(RUNTIME)
@Constraint(validatedBy = EmailNotExistValidator.class)
@Documented
public @interface EmailNotExist {
	String message() default "Email allredy exist";
	
	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };

	@Target({ FIELD})
	@Retention(RUNTIME)
	@Documented
	@interface List {
		EmailNotExist[] value();
	}
}
