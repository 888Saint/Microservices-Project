package com.microservices.hotel.service.Hotel.Service.repository;

import com.microservices.hotel.service.Hotel.Service.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<Hotel, String> {
}
