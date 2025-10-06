package com.microservices.user.service.User.Service.controller;

import com.microservices.user.service.User.Service.entity.User;
import com.microservices.user.service.User.Service.service.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        User savedUser = userService.saveUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    //@CircuitBreaker(name = "ratingHotelBreaker", fallbackMethod = "ratingHotelFallback")
    //@Retry(name = "ratingHotelService", fallbackMethod = "ratingHotelFallback")
    @RateLimiter(name = "userRateLimiter", fallbackMethod = "ratingHotelFallback")
    public ResponseEntity<User> getUser(@PathVariable ("userId") String userId) {
        User user = userService.getUser(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    int retryCount = 1;
    public ResponseEntity<User> ratingHotelFallback(String userId, Exception ex)
    {
        //log.info("Fallback is executed because service is down: {}", ex.getMessage());
        log.info("Retry Count: {}", retryCount);
        retryCount++;
        User user = User.builder()
                .userId("123abc")
                .name("Dummy")
                .email("dummy111@gmail.com")
                .about("This user is created because some services are down")
                .build();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable("userId") String userId, @RequestBody User newUser) {
        User updatedUser = userService.updateUser(userId, newUser);
        return ResponseEntity.ok(updatedUser);
    }


    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable ("userId") String userId)
    {
        String response = userService.deleteUser(userId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
