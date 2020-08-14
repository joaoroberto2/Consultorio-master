package com.consultorio.validation;

import com.consultorio.application.ValidationException;

public interface Validation<T> {
	
	public void validate(T entity) throws ValidationException;
	
}
