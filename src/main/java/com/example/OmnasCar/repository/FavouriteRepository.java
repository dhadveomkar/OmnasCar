package com.example.OmnasCar.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.OmnasCar.model.Favourite;

public interface FavouriteRepository extends JpaRepository<Favourite, Long> {
    List<Favourite> findByUserId(Long userId);
    boolean existsByUserIdAndCarId(Long userId, Long carId);
    void deleteByUserIdAndCarId(Long userId, Long carId);
Favourite findByUserIdAndCarId(Long userId, Long carId);
    
}
