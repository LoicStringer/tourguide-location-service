package com.tourguidelocationservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class GpsUtilException extends Exception {

	private static final long serialVersionUID = 1L;

	public GpsUtilException(String message, Throwable cause) {
		super(message, cause);
	}

	public GpsUtilException(String message) {
		super(message);
	}

	
}
