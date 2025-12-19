package com.astik.user_service.service;

import com.astik.user_service.dto.UserRequest;
import com.astik.user_service.dto.UserResponse;
import com.astik.user_service.entities.User;

import java.util.List;

public interface UserService {
    UserResponse createUser(UserRequest userRequest);
    List<UserResponse>getAllUsers();
    UserResponse getUserById(String id);
}
