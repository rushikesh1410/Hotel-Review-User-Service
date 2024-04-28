package com.lcwr.user.servivce.UserService.services;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.lcwr.user.servivce.UserService.entity.Hotel;
import com.lcwr.user.servivce.UserService.entity.Rating;
import com.lcwr.user.servivce.UserService.entity.User;
import com.lcwr.user.servivce.UserService.exceptions.ResourceNotFoundException;
import com.lcwr.user.servivce.UserService.repository.UserRepository;
import com.lcwr.user.servivce.UserService.services.external.services.HotelService;

@Service
public class UserServiceImpl implements UserServices {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private HotelService hotelService;

//	@Autowired
//	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Override
	public User saveUser(User user) {

		// to generate unique user id
		// UUID --> A class that represents an immutable universally unique identifier
		// (UUID).A UUID represents a 128-bit value.

		String randomUserId = UUID.randomUUID().toString();
		user.setUserId(randomUserId);

		return userRepository.save(user);

	}

	@Override
	public List<User> getAllUser() {

		List<User> user = userRepository.findAll();


		return user;
	}

	@Override
	public User getUser(String userId) {
		// get user from database with help of user repository
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("user not found with id =" + userId));

		// fetch rating of the above user from RATING SERVICE

		// http://localhost:8083/ratings/user/964b66ba-94ee-4703-b8fb-0827ec4d9209
		Rating[] ratingsOfUser = restTemplate.getForObject("http://RATING-SERVICE/ratings/user/" + user.getUserId(),
				Rating[].class);

		List<Rating> Rating = Arrays.stream(ratingsOfUser).toList();

		List<Rating> ratingsList = Rating.stream().map(rating -> {

			// api call to hotel to info about the hotel
			ResponseEntity<Hotel> forEntity = restTemplate
					.getForEntity("http://HOTEL-SERVICE/hotels/" + rating.getHotelId(), Hotel.class);
			Hotel hotel = forEntity.getBody();

			// set the hotel to rating
			rating.setHotel(hotel);

			// return the rating
			return rating;

		}).collect(Collectors.toList());

		// set updated ratings along with ratings and hotel details
		user.setRatings(ratingsList);

		// and return the user
		return user;
	}
	
	
	@Override
	public User getUserUsingOpenFeign(String userId) {
		// get user from database with help of user repository
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("user not found with id =" + userId));

		// fetch rating of the above user from RATING SERVICE

		// http://localhost:8083/ratings/user/964b66ba-94ee-4703-b8fb-0827ec4d9209
		Rating[] ratingsOfUser = restTemplate.getForObject("http://RATING-SERVICE/ratings/user/" + user.getUserId(),
				Rating[].class);

		List<Rating> Rating = Arrays.stream(ratingsOfUser).toList();

		List<Rating> ratingsList = Rating.stream().map(rating -> {

			// api call to hotel to info about the hotel
		//	ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/" + rating.getHotelId(), Hotel.class);
			
			
		//	Hotel hotel = forEntity.getBody();
			
			Hotel hotel = hotelService.getHotel(rating.getHotelId());

			// set the hotel to rating
			rating.setHotel(hotel);

			// return the rating
			return rating;

		}).collect(Collectors.toList());

		// set updated ratings along with ratings and hotel details
		user.setRatings(ratingsList);

		// and return the user
		return user;
	}

}
