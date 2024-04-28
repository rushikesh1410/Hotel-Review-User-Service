package com.lcwr.user.servivce.UserService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.lcwr.user.servivce.UserService.entity.Rating;
import com.lcwr.user.servivce.UserService.services.external.services.RatingService;

@SpringBootTest
class UserServiceApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private RatingService ratingService;
	
	@Test
	Rating getRating() {
		System.out.println("get rating");
		return ratingService.getRating("7d03dd7a-2eab-4c9b-814c-eb80f8939f35");
		
	}
}
