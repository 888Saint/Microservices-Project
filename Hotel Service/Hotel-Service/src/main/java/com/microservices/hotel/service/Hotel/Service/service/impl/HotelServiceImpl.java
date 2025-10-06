package com.microservices.hotel.service.Hotel.Service.service.impl;

import com.microservices.hotel.service.Hotel.Service.entity.Hotel;
import com.microservices.hotel.service.Hotel.Service.exception.ResourceNotFoundException;
import com.microservices.hotel.service.Hotel.Service.repository.HotelRepository;
import com.microservices.hotel.service.Hotel.Service.service.HotelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class HotelServiceImpl implements HotelService {

    @Autowired
    HotelRepository hotelRepository;

    @Override
    public Hotel saveHotel(Hotel hotel) {
        String randomHotelId = UUID.randomUUID().toString();
        hotel.setId(randomHotelId);
        return hotelRepository.save(hotel);
    }

    @Override
    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }

    @Override
    public Hotel getHotel(String hotelId) {
        return hotelRepository.findById(hotelId)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel with id "+hotelId+" is not found on server!"));
    }

    @Override
    public Hotel updateHotel(String hotelId, Hotel newHotel) {
        Hotel hotel = hotelRepository.findById(hotelId)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel with id " + hotelId + " is not found on server!"));

        // Update only non-null fields
        if (newHotel.getName() != null) hotel.setName(newHotel.getName());
        if (newHotel.getLocation() != null) hotel.setLocation(newHotel.getLocation());
        if (newHotel.getAbout() != null) hotel.setAbout(newHotel.getAbout());

        return hotelRepository.save(hotel); // Save updated hotel
    }

    @Override
    public String deleteHotel(String hotelId) {
        hotelRepository.deleteById(hotelId);
        return "Hotel Deleted";
    }
}
