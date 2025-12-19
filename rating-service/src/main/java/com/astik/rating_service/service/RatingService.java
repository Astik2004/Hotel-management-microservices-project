package com.astik.rating_service.service;

import com.astik.rating_service.dto.RatingRequest;
import com.astik.rating_service.dto.RatingResponse;

import java.util.List;

public interface RatingService {
    RatingResponse createRating(RatingRequest ratingRequest);
    List<RatingResponse>getAllRatings();
    List<RatingResponse>getRatingByUserId(String userId);
    List<RatingResponse>getRatingByHotelId(String hotelId);

}
