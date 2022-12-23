package com.portfolio.admin.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.portfolio.admin.customAnnotation.CheckDate;

public class DateValidator implements ConstraintValidator<CheckDate, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return value.trim().length() != 0;
	}

}
