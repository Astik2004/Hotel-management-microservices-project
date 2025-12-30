package com.astik.user_service.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/test")
public class UserTestController {
    public ResponseEntity<?>testUser()
    {
        return ResponseEntity.ok("User is working fine");
    }
}
