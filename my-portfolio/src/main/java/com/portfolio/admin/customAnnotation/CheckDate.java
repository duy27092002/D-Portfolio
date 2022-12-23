package com.portfolio.admin.customAnnotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.portfolio.admin.validator.DateValidator;

@Documented
@Constraint(validatedBy = DateValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckDate {
	String message() default "Invalid date";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
