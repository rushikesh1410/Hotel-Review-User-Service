package com.lcwr.user.servivce.UserService.exceptions;

public class ResourceNotFoundException extends RuntimeException {

	public ResourceNotFoundException() {
		super("Resource not found on user!!");
	}
	
	public ResourceNotFoundException(String msg) {
		super(msg);
	}
}
