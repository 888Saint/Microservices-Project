package com.microservices.hotel.service.Hotel.Service.service;

import com.microservices.hotel.service.Hotel.Service.entity.Hotel;

import java.util.List;

public interface HotelService {

    Hotel saveHotel(Hotel hotel);

    List<Hotel> getAllHotels();

    Hotel getHotel(String id);

    Hotel updateHotel(String id, Hotel newHotel);

    String deleteHotel(String hotelId);
}
