package com.microservices.rating.service.Rating.Service.repository;

import com.microservices.rating.service.Rating.Service.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RatingRepository extends JpaRepository<Rating, Long> {

    List<Rating> findByUserId(String userId);

    List<Rating> findByHotelId(String hotelId);
}
