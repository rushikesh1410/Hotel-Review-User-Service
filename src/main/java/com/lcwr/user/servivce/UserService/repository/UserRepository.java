package com.lcwr.user.servivce.UserService.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lcwr.user.servivce.UserService.entity.User;

public interface UserRepository extends JpaRepository<User, String> {
	
	

}
