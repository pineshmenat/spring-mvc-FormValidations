package com.spring.mvc.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CourseCodeConstraintValidator implements ConstraintValidator<CourseCode, String>{

	private String[] coursePrefix;
	
	@Override
	public void initialize(CourseCode constraintAnnotation) {
		coursePrefix = constraintAnnotation.value();
	}

	@Override
	public boolean isValid(String theCode, ConstraintValidatorContext arg1) {
		
		boolean result = false;
		if(theCode != null) {
			for (String temp : coursePrefix) {
				result = theCode.startsWith(temp);
				if(result) {
					break;
				}
			}
			return result;
			
		} else {
			return true;
		}
	}
	
}
