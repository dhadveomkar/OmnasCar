package com.example.OmnasCar.controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.OmnasCar.model.Admin;
import com.example.OmnasCar.repository.AdminRepository;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "*")
public class AdminController {

    @Autowired
    private AdminRepository adminRepository;

    @PostMapping("/login")
    public ResponseEntity<?> adminLogin(@RequestBody Admin loginData) {
        Optional<Admin> admin = adminRepository.findByEmailAndPassword(loginData.getEmail(), loginData.getPassword());

        if (admin.isPresent()) {
            return ResponseEntity.ok(Map.of("message", "Admin login successful"));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "Invalid credentials"));
        }
    }

    // You can also add: GET /api/admin/profile, etc.
}

