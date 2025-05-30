package com.example.OmnasCar.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.OmnasCar.model.Review;


    public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByCarId(Long carId);
    List<Review> findByUserId(Long userId);
    }


