package com.example.OmnasCar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.OmnasCar.model.Review;
import com.example.OmnasCar.model.ReviewDTO;
import com.example.OmnasCar.service.ReviewService;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {
 @Autowired private ReviewService reviewService;

    @PostMapping
    public ResponseEntity<Review> createReview(@RequestBody Review review) {
        return ResponseEntity.ok(reviewService.addReview(review));
    }

    @GetMapping
public ResponseEntity<List<ReviewDTO>> getAllReviews() {
    return ResponseEntity.ok(reviewService.getAllReviews());
}

    @GetMapping("/car/{carId}")
public ResponseEntity<List<ReviewDTO>> getCarReviews(@PathVariable Long carId) {
    return ResponseEntity.ok(reviewService.getReviewsDTOByCar(carId));
}  
}
