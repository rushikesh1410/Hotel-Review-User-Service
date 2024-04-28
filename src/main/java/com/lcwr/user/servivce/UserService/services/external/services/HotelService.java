package com.lcwr.user.servivce.UserService.services.external.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.lcwr.user.servivce.UserService.entity.Hotel;

@FeignClient(name = "HOTEL-SERVICE")
public interface HotelService {
	
	@GetMapping(path = "/hotels/{hotelId}")
	 Hotel getHotel(@PathVariable String hotelId);
	
	

}
