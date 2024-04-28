package com.lcwr.user.servivce.UserService.services;

import java.util.List;

import com.lcwr.user.servivce.UserService.entity.User;

public interface UserServices {
	
	//user operations
	
	//create 
	User saveUser(User user);
	
	//get all users
	List<User> getAllUser();
	
	
	//get single user with the UserId
	User getUser(String userId);

	User getUserUsingOpenFeign(String userId);
	
	//delete
	//update 

}
