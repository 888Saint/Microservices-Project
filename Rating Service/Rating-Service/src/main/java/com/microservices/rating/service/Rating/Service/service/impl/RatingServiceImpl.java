package com.microservices.rating.service.Rating.Service.service.impl;

import com.microservices.rating.service.Rating.Service.entity.Rating;
import com.microservices.rating.service.Rating.Service.exception.ResourceNotFoundException;
import com.microservices.rating.service.Rating.Service.repository.RatingRepository;
import com.microservices.rating.service.Rating.Service.service.RatingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class RatingServiceImpl implements RatingService {

    @Autowired
    RatingRepository ratingRepository;

    @Override
    public Rating saveRating(Rating rating) {
        return ratingRepository.save(rating); // Save the rating to the repository
    }

    @Override
    public List<Rating> getAllRatings() {
        return ratingRepository.findAll(); // Retrieve all ratings from the repository
    }

    @Override
    public Rating getRating(Long ratingId) {
        return ratingRepository.findById(ratingId)
                .orElseThrow(() -> new ResourceNotFoundException("Rating with id " + ratingId + " is not found on server!"));
        // Fetch the rating by id, throw exception if not found
    }

    @Override
    public Rating updateRating(Long ratingId, Rating newRating) {
        Rating rating = ratingRepository.findById(ratingId)
                .orElseThrow(() -> new ResourceNotFoundException("Rating with id " + ratingId + " is not found on server!"));

        // Update only non-null fields from newRating
        if (newRating.getRating() != 0) rating.setRating(newRating.getRating());
        if (newRating.getFeedback() != null) rating.setFeedback(newRating.getFeedback());

        return ratingRepository.save(rating); // Save the updated rating to the repository
    }

    @Override
    public String deleteRating(Long ratingId) {
        ratingRepository.deleteById(ratingId); // Delete the rating by id
        return "Rating Deleted"; // Return success message
    }

    @Override
    public List<Rating> getRatingsByUserId(String userId) {
        return ratingRepository.findByUserId(userId);
    }

    @Override
    public List<Rating> getRatingsByHotelId(String hotelId) {
        return ratingRepository.findByHotelId(hotelId);
    }
}
