package com.microservices.rating.service.Rating.Service.service;

import com.microservices.rating.service.Rating.Service.entity.Rating;

import java.util.List;

public interface RatingService {

    Rating saveRating(Rating rating);

    List<Rating> getAllRatings();

    Rating getRating(Long ratingId);

    Rating updateRating(Long ratingId, Rating newRating);

    String deleteRating(Long ratingId);

    List<Rating> getRatingsByUserId(String userId);

    List<Rating> getRatingsByHotelId(String hotelId);
}
