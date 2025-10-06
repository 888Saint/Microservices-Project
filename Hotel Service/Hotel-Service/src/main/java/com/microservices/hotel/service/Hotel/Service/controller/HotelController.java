package com.microservices.hotel.service.Hotel.Service.controller;

import com.microservices.hotel.service.Hotel.Service.entity.Hotel;
import com.microservices.hotel.service.Hotel.Service.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotel")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @PostMapping("/add")
    public ResponseEntity<Hotel> saveHotel(@RequestBody Hotel hotel) {
        Hotel savedHotel = hotelService.saveHotel(hotel);
        return new ResponseEntity<>(savedHotel, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Hotel>> getAllHotels() {
        List<Hotel> hotels = hotelService.getAllHotels();
        return new ResponseEntity<>(hotels, HttpStatus.OK);
    }

    @GetMapping("/{hotelId}")
    public ResponseEntity<Hotel> getHotel(@PathVariable("hotelId") String hotelId) {
        Hotel hotel = hotelService.getHotel(hotelId);
        return new ResponseEntity<>(hotel, HttpStatus.OK);
    }

    @PutMapping("/update/{hotelId}")
    public ResponseEntity<Hotel> updateHotel(@PathVariable("hotelId") String hotelId, @RequestBody Hotel newHotel) {
        Hotel updatedHotel = hotelService.updateHotel(hotelId, newHotel);
        return ResponseEntity.ok(updatedHotel);
    }

    @DeleteMapping("/delete/{hotelId}")
    public ResponseEntity<String> deleteHotel(@PathVariable("hotelId") String hotelId) {
        String response = hotelService.deleteHotel(hotelId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
