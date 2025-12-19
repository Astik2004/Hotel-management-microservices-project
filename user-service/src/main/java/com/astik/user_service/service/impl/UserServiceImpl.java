package com.astik.user_service.service.impl;

import com.astik.user_service.dto.UserRequest;
import com.astik.user_service.dto.UserResponse;
import com.astik.user_service.entities.User;
import com.astik.user_service.exception.ResourceNotFoundException;
import com.astik.user_service.repositories.UserRepository;
import com.astik.user_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserResponse createUser(UserRequest userRequest) {
        User user = toEntity(userRequest);
        User savedUser = userRepository.save(user);
        return toResponse(savedUser);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponse getUserById(String id) {
        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found with id: " + id));

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
                .rating(List.of())
                .build();
    }
}
