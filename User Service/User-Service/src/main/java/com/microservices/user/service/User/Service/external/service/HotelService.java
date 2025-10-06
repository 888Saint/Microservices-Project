package com.microservices.user.service.User.Service.external.service;

import com.microservices.user.service.User.Service.entity.Hotel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "HOTEL-SERVICE")
public interface HotelService {

    @GetMapping("/hotel/{hotelId}")
    Hotel getHotel(@PathVariable("hotelId") String hotelId);
}
