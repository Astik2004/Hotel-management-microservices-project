package com.astik.rating_service.controller;

import com.astik.rating_service.dto.RatingRequest;
import com.astik.rating_service.dto.RatingResponse;
import com.astik.rating_service.service.RatingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/ratings")
public class RatingController {
    private final RatingService ratingService;
    @PostMapping
    public ResponseEntity<RatingResponse>createRating(@Valid @RequestBody RatingRequest ratingRequest)
    {
        log.info("REST request to create Rating for user: {}", ratingRequest.getUserId());
        RatingResponse rating=ratingService.createRating(ratingRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(rating);
    }
    @GetMapping
    public ResponseEntity<List<RatingResponse>> getAllRatings() {
        log.info("REST request to get all Ratings");
        List<RatingResponse> ratings = ratingService.getAllRatings();
        return ResponseEntity.ok(ratings);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<List<RatingResponse>> getRatingsByUserId(@PathVariable String userId) {
        log.info("REST request to get Ratings for user ID: {}", userId);
        List<RatingResponse> ratings = ratingService.getRatingByUserId(userId);
        return ResponseEntity.ok(ratings);
    }

    @GetMapping("/hotels/{hotelId}")
    public ResponseEntity<List<RatingResponse>> getRatingsByHotelId(@PathVariable String hotelId) {
        log.info("REST request to get Ratings for hotel ID: {}", hotelId);
        List<RatingResponse> ratings = ratingService.getRatingByHotelId(hotelId);
        return ResponseEntity.ok(ratings);
    }
}
