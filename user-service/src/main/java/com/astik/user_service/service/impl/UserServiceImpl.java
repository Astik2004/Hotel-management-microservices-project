package com.astik.user_service.service.impl;

import com.astik.user_service.client.HotelService;
import com.astik.user_service.client.RatingService;
import com.astik.user_service.dto.RatingResponse;
import com.astik.user_service.entities.Hotel;
import com.astik.user_service.entities.Rating;
import com.astik.user_service.dto.UserRequest;
import com.astik.user_service.dto.UserResponse;
import com.astik.user_service.entities.User;
import com.astik.user_service.exception.ResourceNotFoundException;
import com.astik.user_service.repositories.UserRepository;
import com.astik.user_service.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    //private final RestTemplate restTemplate;
    private final HotelService hotelService;
    private final RatingService ratingService;

    @Override
    public UserResponse createUser(UserRequest userRequest) {
        log.info("Creating User with name {}",userRequest.getName());
        User user = toEntity(userRequest);
        User savedUser = userRepository.save(user);
        log.info("User created successfully with id {}",savedUser.getId());
        return toResponse(savedUser);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserResponse> getAllUsers() {
        log.info("Fetching all User Details");
        return userRepository.findAll()
                .stream()
                .map(user -> {
                    List<Rating> ratings =
                            ratingService.getRatingsByUserId(user.getId())
                                    .stream()
                                    .map(this::mapToEntity)
                                    .map(rating -> {
                                        Hotel hotel = hotelService.getHotel(rating.getHotelId());
                                        rating.setHotel(hotel);
                                        return rating;
                                    })
                                    .toList();
                    user.setRating(ratings);
                    return user;
                })
                .map(this::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponse getUserById(String id) {
        log.info("Fetching user with id : {}",id);
        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found with id: " + id));
        //Rating[] ratingOfUser=restTemplate.getForObject("http://RATING-SERVICE/api/v1/ratings/users/"+id, Rating[].class);
        List<RatingResponse>ratingOfUser=ratingService.getRatingsByUserId(user.getId());
        //List<Rating>ratings= Arrays.stream(ratingOfUser).toList();
        List<Rating>ratings=ratingOfUser.stream().map(this::mapToEntity).map(rating->{
            Hotel hotel=hotelService.getHotel(rating.getHotelId());
            rating.setHotel(hotel);
            return rating;
        }).toList();

//        List<Rating>ratingList =ratings.stream().map(rating->{
//            ResponseEntity<Hotel> forEntity=restTemplate.getForEntity("http://HOTEL-SERVICE/api/v1/hotels/"+rating.getHotelId(), Hotel.class);
//            Hotel hotel=hotelService.getHotel(rating.getHotelId());
//            log.info("Response status code: {}",forEntity.getStatusCode());
//            rating.setHotel(hotel);
//            return rating;
//        }).toList();

        user.setRating(ratings);
        return toResponse(user);
    }

    /* ================= PRIVATE MAPPING METHODS ================= */

    private User toEntity(UserRequest request) {
        return User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .about(request.getAbout())
                .build();
    }

    private UserResponse toResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .about(user.getAbout())
                .rating(user.getRating())
                .build();
    }
    private Rating mapToEntity(RatingResponse response) {
        return Rating.builder()
                .ratingId(response.getRatingId())
                .userId(response.getUserId())
                .hotelId(response.getHotelId())
                .rating(response.getRating())
                .feedback(response.getFeedback())
                .build();
    }

}
