package com.onebox.onebox.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import org.json.JSONObject;

public class CustomValidator implements ConstraintValidator<CustomValidation, Object>{
	String regex;
	String type;
	String template;
	String membership;
	int maxLen;
	int minLen;
	
	@Override
    public void initialize(CustomValidation annotation) {
        this.regex=annotation.regex();
        this.type=annotation.type();
        this.type=annotation.template();
        this.maxLen=annotation.maxLen();
        this.minLen=annotation.minLen();
    }
	
	private String getRegexValue(String regexName) {
		return "";
	}
	
	private JSONObject getTemplate(String templateName) {
		return new JSONObject();
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		if(value instanceof String && !regex.isEmpty()) {
			String regexValue=getRegexValue(regex);
			int valueLen=((String)value).length();
			return (patternMatcher((String)value, regexValue) && valueLen<=maxLen && valueLen>=minLen);
		}else if(!template.isEmpty()){
			return templateMatcher(value);
		}else if(!membership.isEmpty()) {
			return membership.contains((String)value);
		}
		return true;
	}
	

	private boolean patternMatcher(String value, String regex) {
		return value.matches(regex);
	}
	
	private boolean templateMatcher(Object value) {
		
		return true;
	}

}
