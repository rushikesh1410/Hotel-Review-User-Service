package com.lcwr.user.servivce.UserService.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.lcwr.user.servivce.UserService.payload.ApiResponce;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponce> handlerResourceNotFoundExcption(ResourceNotFoundException ex) {

		String message = ex.getMessage();
		ApiResponce responce = ApiResponce.builder().message(message).success(true).status(HttpStatus.NOT_FOUND)
				.build();

		return new ResponseEntity<ApiResponce>(responce, HttpStatus.NOT_FOUND);

	}

}
