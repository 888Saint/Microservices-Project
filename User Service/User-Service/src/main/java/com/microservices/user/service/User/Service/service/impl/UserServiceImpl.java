package com.microservices.user.service.User.Service.service.impl;

import com.microservices.user.service.User.Service.entity.Hotel;
import com.microservices.user.service.User.Service.entity.Rating;
import com.microservices.user.service.User.Service.entity.User;
import com.microservices.user.service.User.Service.exception.ResourceNotFoundException;
import com.microservices.user.service.User.Service.external.service.HotelService;
import com.microservices.user.service.User.Service.repository.UserRepository;
import com.microservices.user.service.User.Service.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    HotelService hotelService;

    @Override
    public User saveUser(User user) {
        String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = userRepository.findAll();

        for (User u : users)
        {
            Rating[] ratings = restTemplate.getForObject("http://RATING-SERVICE/rating/user/"+u.getUserId(), Rating[].class);
            log.info("{}", ratings);
            List<Rating> ratingsOfUser = Arrays.stream(ratings).toList();
            List<Rating> ratingList = ratingsOfUser.stream().map(rating -> {
                ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTEL-SERVICE/hotel/"+rating.getHotelId(), Hotel.class);
                Hotel hotel = forEntity.getBody();
                log.info("response status: {}", forEntity.getStatusCode());
                rating.setHotel(hotel);
                return rating;
            }).toList();
            u.setRatings(ratingsOfUser);
        }

        return users;
    }

//    @Override
//    public User getUser(String userId) {
//        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with id "+userId+" is not found on server!"));
//        Rating[] ratings = restTemplate.getForObject("http://RATING-SERVICE/rating/user/"+user.getUserId(), Rating[].class);
//        log.info("{}", ratings);
//        List<Rating> ratingsOfUser = Arrays.stream(ratings).toList();
//        List<Rating> ratingList = ratingsOfUser.stream().map(rating -> {
//            ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTEL-SERVICE/hotel/"+rating.getHotelId(), Hotel.class);
//            Hotel hotel = forEntity.getBody();
//            log.info("response status: {}", forEntity.getStatusCode());
//            rating.setHotel(hotel);
//            return rating;
//        }).collect(Collectors.toList());
//        user.setRatings(ratingList);
//        return user;
//    }

    @Override
    public User getUser(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with id "+userId+" is not found on server!"));
        Rating[] ratings = restTemplate.getForObject("http://RATING-SERVICE/rating/user/"+user.getUserId(), Rating[].class);
        log.info("{}", ratings);
        List<Rating> ratingsOfUser = Arrays.stream(ratings).toList();
        List<Rating> ratingList = ratingsOfUser.stream().map(rating -> {
            Hotel hotel = hotelService.getHotel(rating.getHotelId());
            rating.setHotel(hotel);
            return rating;
        }).collect(Collectors.toList());
        user.setRatings(ratingList);
        return user;
    }

    @Override
    public User updateUser(String userId, User newUser) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User with id " + userId + " is not found on server!"));

        // Update only non-null fields
        if (newUser.getName() != null) user.setName(newUser.getName());
        if (newUser.getEmail() != null) user.setEmail(newUser.getEmail());
        if (newUser.getAbout() != null) user.setAbout(newUser.getAbout());

        return userRepository.save(user); // Save updated user
    }


    @Override
    public String deleteUser(String userId) {
        userRepository.deleteById(userId);
        return "User Deleted";
    }
}
