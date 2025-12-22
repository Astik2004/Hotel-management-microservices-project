package com.astik.hotel_service.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/staffs")
@Slf4j
public class StaffsController {
    @GetMapping
    public ResponseEntity<List<String>>getStaffs()
    {
        log.info("REST request to fetching hotel staffs");
        List<String>staffs= List.of("Astik","Ankit","Prayag","Ayush","Akarsh");
        return ResponseEntity.ok(staffs);
    }
}
