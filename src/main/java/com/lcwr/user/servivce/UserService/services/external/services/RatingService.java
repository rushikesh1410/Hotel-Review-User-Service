package com.lcwr.user.servivce.UserService.services.external.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.lcwr.user.servivce.UserService.entity.Rating;

@FeignClient(name = "RATING-SERVICE")
public interface RatingService {
	
	
	//get rating
	@GetMapping(path = "/ratings/{ratingId}")
	public Rating getRating(@PathVariable String hotelId ); 
	
	
	//create rating
	@PostMapping(path = "/ratings")
	public Rating createRating(Rating rating);
	
	
	//update rating
	@PutMapping(path = "ratings/{ratingId}")
	public Rating  updateRating(@PathVariable String ratingId , Rating rating );
	
	
	//delete rating
	@DeleteMapping(path = "ratings/{ratingId}")
	public void  deleterating(@PathVariable String ratingId);

}
