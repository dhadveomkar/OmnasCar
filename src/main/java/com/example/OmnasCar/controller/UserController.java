package com.example.OmnasCar.controller;

import com.example.OmnasCar.model.User;
import com.example.OmnasCar.repository.UserRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("Email already exists");
        }
        userRepository.save(user);
        return ResponseEntity
        .status(HttpStatus.CREATED)
        .body(Collections.singletonMap("message", "Signup successful"));
    }

    @PostMapping("/login")
public ResponseEntity<?> login(@RequestBody Map<String, String> credentials) {
    String email = credentials.get("email");
    String password = credentials.get("password");

    Optional<User> user = userRepository.findByEmail(email);
    
    if (user.isPresent()) {
        System.out.println("DB password: " + user.get().getPassword());
        System.out.println("Entered password: " + password);

        if (user.get().getPassword().equals(password)) {
            return ResponseEntity.ok(Collections.singletonMap("message", "Login successful"));
        }
    }

    return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                         .body(Collections.singletonMap("message", "Invalid credentials"));
}

@GetMapping("/profile")
public ResponseEntity<?> getProfile(@RequestParam String email) {
    Optional<User> user = userRepository.findByEmail(email);
    if (user.isPresent()) {
        return ResponseEntity.ok(user.get());
    } else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap("message", "User not found"));
    }
}

@PutMapping("/update")
public ResponseEntity<?> updateUser(@RequestBody Map<String, String> updateData) {
    String email = updateData.get("email");
    String contact = updateData.get("contact");
    String address = updateData.get("address");

    Optional<User> userOptional = userRepository.findByEmail(email);
    if (userOptional.isPresent()) {
        User user = userOptional.get();
        user.setContact(contact);
        user.setAddress(address);
        userRepository.save(user);

        return ResponseEntity.ok(Collections.singletonMap("message", "Profile updated successfully"));
    } else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                             .body(Collections.singletonMap("message", "User not found"));
    }
}



    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        return userRepository.findById(id)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        return userRepository.findById(id).map(user -> {
            user.setName(updatedUser.getName());
            user.setAddress(updatedUser.getAddress());
            user.setContact(updatedUser.getContact());
            user.setPassword(updatedUser.getPassword());
            return ResponseEntity.ok(userRepository.save(user));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return ResponseEntity.ok("User deleted");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }
}