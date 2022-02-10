package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/*
@ResponseStatus(value = HttpStatus.NOT_FOUND)
*/
//will handle not found with ApiExceptionHandler class

public class NotFoundException extends RuntimeException{
	
	public NotFoundException(String message) {
		super(message);
	}
}
