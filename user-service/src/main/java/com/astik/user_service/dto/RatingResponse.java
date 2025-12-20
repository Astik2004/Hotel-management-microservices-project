package com.astik.user_service.dto;

import com.astik.user_service.entities.Hotel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RatingResponse {
    private String ratingId;
    private String userId;
    private String hotelId;
    private int rating;
    private String feedback;
}
