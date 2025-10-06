package com.microservices.rating.service.Rating.Service.controller;

import com.microservices.rating.service.Rating.Service.entity.Rating;
import com.microservices.rating.service.Rating.Service.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rating")
public class RatingController {

    @Autowired
    RatingService ratingService;

    @PostMapping("/add")
    public ResponseEntity<Rating> saveRating(@RequestBody Rating rating) {
        Rating savedRating = ratingService.saveRating(rating);
        return new ResponseEntity<>(savedRating, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Rating>> getAllRatings() {
        List<Rating> ratings = ratingService.getAllRatings();
        return new ResponseEntity<>(ratings, HttpStatus.OK);
    }

    @GetMapping("/{ratingId}")
    public ResponseEntity<Rating> getRating(@PathVariable("ratingId") Long ratingId) {
        Rating rating = ratingService.getRating(ratingId);
        return new ResponseEntity<>(rating, HttpStatus.OK);
    }

    @PutMapping("/update/{ratingId}")
    public ResponseEntity<Rating> updateRating(@PathVariable("ratingId") Long ratingId, @RequestBody Rating newRating) {
        Rating updatedRating = ratingService.updateRating(ratingId, newRating);
        return ResponseEntity.ok(updatedRating);
    }

    @DeleteMapping("/delete/{ratingId}")
    public ResponseEntity<String> deleteRating(@PathVariable("ratingId") Long ratingId) {
        String response = ratingService.deleteRating(ratingId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Rating>> getRatingsByUserId(@PathVariable("userId") String userId) {
        List<Rating> ratings = ratingService.getRatingsByUserId(userId);
        return new ResponseEntity<>(ratings, HttpStatus.OK);
    }

    @GetMapping("/hotel/{hotelId}")
    public ResponseEntity<List<Rating>> getRatingsByHotelId(@PathVariable("hotelId") String hotelId) {
        List<Rating> ratings = ratingService.getRatingsByHotelId(hotelId);
        return new ResponseEntity<>(ratings, HttpStatus.OK);
    }
}
