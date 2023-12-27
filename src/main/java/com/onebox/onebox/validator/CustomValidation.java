package com.onebox.onebox.validator;

import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.ElementType;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = (CustomValidator.class))
public @interface CustomValidation {
	String regex() default "";
	String type() default "";
	String template() default "";
	String[] membership() default {};
	int maxLen() default 500;
	int minLen() default 0;
	
	String message() default "Invalid parameter value.";
	public Class<?>[] groups() default {};
	public Class<? extends Payload>[] payload() default {};
}
