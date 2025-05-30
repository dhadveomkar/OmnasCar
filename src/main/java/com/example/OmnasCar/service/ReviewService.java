package com.example.OmnasCar.service;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.OmnasCar.model.Car;
import com.example.OmnasCar.model.Review;
import com.example.OmnasCar.model.ReviewDTO;
import com.example.OmnasCar.model.User;
import com.example.OmnasCar.repository.CarRepository;
import com.example.OmnasCar.repository.ReviewRepository;
import com.example.OmnasCar.repository.UserRepository;


@Service
public class ReviewService {
  @Autowired
    private ReviewRepository reviewRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private CarRepository carRepo;

    public Review addReview(Review review) {
        User user = userRepo.findById(review.getUser().getId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Car car = carRepo.findById(review.getCar().getId())
                .orElseThrow(() -> new RuntimeException("Car not found"));

        review.setUser(user);
        review.setCar(car);
        review.setCreatedAt(LocalDateTime.now());

        return reviewRepo.save(review);
    }

    public List<Review> getReviewsByCar(Long carId) {
        return reviewRepo.findByCarId(carId);
    }

    
    public List<ReviewDTO> getReviewsDTOByCar(Long carId) {
    List<Review> reviews = reviewRepo.findByCarId(carId);
    List<ReviewDTO> reviewDTOs = new ArrayList<>();

    for (Review review : reviews) {
        ReviewDTO dto = new ReviewDTO();
        dto.setId(review.getId());
        dto.setRating(review.getRating());
        dto.setComment(review.getComment());
        dto.setCreatedAt(review.getCreatedAt());
        dto.setUserName(review.getUser().getName());
        dto.setCarName(review.getCar().getName());

        reviewDTOs.add(dto);
    }

    return reviewDTOs;
}

public List<ReviewDTO> getAllReviews() {
    List<Review> reviews = reviewRepo.findAll();
    List<ReviewDTO> reviewDTOs = new ArrayList<>();

    for (Review review : reviews) {
        ReviewDTO dto = new ReviewDTO();
        dto.setId(review.getId());
        dto.setRating(review.getRating());
        dto.setComment(review.getComment());
        dto.setCreatedAt(review.getCreatedAt());
        dto.setUserName(review.getUser().getName());
        dto.setCarName(review.getCar().getName());
        reviewDTOs.add(dto);
    }

    return reviewDTOs;
}

}