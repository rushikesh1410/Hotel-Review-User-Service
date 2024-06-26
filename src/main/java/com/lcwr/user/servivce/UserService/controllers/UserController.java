package com.lcwr.user.servivce.UserService.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lcwr.user.servivce.UserService.entity.User;
import com.lcwr.user.servivce.UserService.services.UserServices;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserServices userService;

	// create user
	@PostMapping
	public ResponseEntity<User> createUser(@RequestBody User user) {

		User user1 = userService.saveUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(user1);

	}

	// single get
	@GetMapping("/{userId}")
	public ResponseEntity<User> getSingleUser(@PathVariable String userId){
		
		User user = userService.getUserUsingOpenFeign(userId);
		return ResponseEntity.ok(user);
		
	}

	// get all
	@GetMapping
	public ResponseEntity<List<User>> getAllUser(){
		List<User>  allUser = userService.getAllUser();
		
		return ResponseEntity.ok(allUser) ;
		
	}

}