package com.tourguidelocationservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserServiceException extends Exception {

	private static final long serialVersionUID = 1L;

	public UserServiceException(String message) {
		super(message);
	}

	public UserServiceException(String message, Throwable cause) {
		super(message, cause);
	}
	
}
