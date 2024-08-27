package com.micro.accounts.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class CustomerAlreadyExistsException extends RuntimeException {
	
	public CustomerAlreadyExistsException(String message) {
		// TODO Auto-generated constructor stub
		super(message);
	}
	

}
