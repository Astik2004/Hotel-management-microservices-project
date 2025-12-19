package com.astik.hotel_service.service;

import com.astik.hotel_service.dto.HotelRequest;
import com.astik.hotel_service.dto.HotelResponse;

import java.util.List;

public interface HotelService {
    HotelResponse createHotel(HotelRequest hotelRequest);
    List<HotelResponse>getAllHotels();
    HotelResponse getHotelById(String id);
}
