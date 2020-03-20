package com.filmland.streaming.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class DataNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public DataNotFoundException(String message) {
		super(message);
	}
	
	public DataNotFoundException(String message, Throwable t) {
		super(message, t);
	}
}