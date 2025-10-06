package com.microservices.user.service.User.Service.external.service;

import com.microservices.user.service.User.Service.entity.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@Service
@FeignClient("RATING-SERVICE")
public interface RatingService {

    @GetMapping("/rating/{ratingID}")
    ResponseEntity<Rating> getRating(@PathVariable("ratingID") Long ratingId);

    @PostMapping("/rating/add")
    ResponseEntity<Rating> saveRating(Rating values);

    @PutMapping("/rating/update/{ratingId}")
    ResponseEntity<Rating> updateRating(@PathVariable("ratingId") Long ratingId, Rating values);

    @DeleteMapping("/rating/delete/{ratingId}")
    ResponseEntity<String> deleteRating(@PathVariable("ratingId") Long ratingId);
}
