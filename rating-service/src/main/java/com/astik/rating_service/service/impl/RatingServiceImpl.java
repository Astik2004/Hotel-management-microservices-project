package com.astik.rating_service.service.impl;

import com.astik.rating_service.dto.RatingRequest;
import com.astik.rating_service.dto.RatingResponse;
import com.astik.rating_service.entities.Rating;
import com.astik.rating_service.repositories.RatingRepository;
import com.astik.rating_service.service.RatingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class RatingServiceImpl implements RatingService {
    private final RatingRepository ratingRepository;
    @Override
    public RatingResponse createRating(RatingRequest ratingRequest) {
        log.info("Creating new rating for User: {} and Hotel: {}", ratingRequest.getUserId(), ratingRequest.getHotelId());
        Rating rating = mapToEntity(ratingRequest);
        Rating savedRating = ratingRepository.save(rating);
        log.info("Rating created successfully with ID: {}", savedRating.getRatingId());
        return mapToResponse(savedRating);
    }

    @Override
    public List<RatingResponse> getAllRatings() {
        log.info("Fetching all ratings from database");
        return ratingRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public List<RatingResponse> getRatingByUserId(String userId) {
        log.info("Fetching ratings for User ID: {}", userId);
        List<Rating> ratings = ratingRepository.findByUserId(userId);
        return ratings.stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public List<RatingResponse> getRatingByHotelId(String hotelId) {
        log.info("Fetching ratings for Hotel ID: {}", hotelId);
        return ratingRepository.findByHotelId(hotelId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }
    private Rating mapToEntity(RatingRequest request)
    {
        return Rating.builder()
                .userId(request.getUserId())
                .hotelId(request.getHotelId())
                .rating(request.getRating())
                .feedback(request.getFeedback())
                .build();
    }
    private RatingResponse mapToResponse(Rating rating)
    {
        return RatingResponse.builder()
                .ratingId(rating.getRatingId())
                .userId(rating.getUserId())
                .hotelId(rating.getHotelId())
                .feedback(rating.getFeedback())
                .rating(rating.getRating())
                .build();
    }
}
