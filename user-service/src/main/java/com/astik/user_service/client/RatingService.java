package com.astik.user_service.client;

import com.astik.user_service.dto.RatingRequest;
import com.astik.user_service.dto.RatingResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name="RATING-SERVICE")
public interface RatingService {

    @PostMapping("/api/v1/ratings")
    RatingResponse createRating(@RequestBody RatingRequest ratingRequest);

    @GetMapping("/api/v1/ratings")
    List<RatingResponse> getAllRatings();

    @GetMapping("/api/v1/ratings/users/{userId}")
    List<RatingResponse> getRatingsByUserId(@PathVariable String userId);

    @GetMapping("/api/v1/ratings/hotels/{hotelId}")
    List<RatingResponse> getRatingsByHotelId(@PathVariable String hotelId);
}
