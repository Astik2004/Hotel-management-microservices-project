package com.astik.hotel_service.controller;

import com.astik.hotel_service.dto.HotelRequest;
import com.astik.hotel_service.dto.HotelResponse;
import com.astik.hotel_service.service.HotelService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/hotels")
@RequiredArgsConstructor
@Slf4j
public class HotelController {

    private final HotelService hotelService;

    // ================= CREATE HOTEL =================
    @PostMapping
    public ResponseEntity<HotelResponse> createHotel(@Valid @RequestBody HotelRequest hotelRequest) {
        log.info("Received request to create hotel");

        HotelResponse response = hotelService.createHotel(hotelRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // ================= GET HOTEL BY ID =================
    @GetMapping("/{id}")
    public ResponseEntity<HotelResponse> getHotelById(@PathVariable String id) {
        log.info("Received request to fetch hotel with id: {}", id);

        HotelResponse response = hotelService.getHotelById(id);
        return ResponseEntity.ok(response);
    }

    // ================= GET ALL HOTELS =================
    @GetMapping
    public ResponseEntity<List<HotelResponse>> getAllHotels() {
        log.info("Received request to fetch all hotels");

        List<HotelResponse> hotels = hotelService.getAllHotels();
        return ResponseEntity.ok(hotels);
    }
}
