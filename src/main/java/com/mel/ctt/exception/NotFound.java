package com.mel.ctt.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Not Found")
public class NotFound extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
