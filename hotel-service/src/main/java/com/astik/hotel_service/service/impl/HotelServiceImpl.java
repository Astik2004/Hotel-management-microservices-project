package com.astik.hotel_service.service.impl;
import com.astik.hotel_service.dto.HotelRequest;
import com.astik.hotel_service.dto.HotelResponse;
import com.astik.hotel_service.entities.Hotel;
import com.astik.hotel_service.exception.ResourceNotFoundException;
import com.astik.hotel_service.repositories.HotelRepository;
import com.astik.hotel_service.service.HotelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class HotelServiceImpl implements HotelService {

    private final HotelRepository hotelRepository;

    @Override
    public HotelResponse createHotel(HotelRequest hotelRequest) {
        log.info("Creating hotel with name: {}", hotelRequest.getName());

        Hotel hotel = mapToEntity(hotelRequest);
        Hotel savedHotel = hotelRepository.save(hotel);

        log.info("Hotel created successfully with id: {}", savedHotel.getId());
        return mapToResponse(savedHotel);
    }

    @Override
    @Transactional(readOnly = true)
    public List<HotelResponse> getAllHotels() {
        log.info("Fetching all hotels");

        return hotelRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public HotelResponse getHotelById(String id) {
        log.info("Fetching hotel with id: {}", id);

        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Hotel not found with id: " + id
                ));

        return mapToResponse(hotel);
    }

    // ================== Mapping Methods ==================

    private Hotel mapToEntity(HotelRequest request) {
        return Hotel.builder()
                .name(request.getName())
                .location(request.getLocation())
                .about(request.getAbout())
                .build();
    }

    private HotelResponse mapToResponse(Hotel hotel) {
        return HotelResponse.builder()
                .id(hotel.getId())
                .name(hotel.getName())
                .location(hotel.getLocation())
                .about(hotel.getAbout())
                .build();
    }
}
