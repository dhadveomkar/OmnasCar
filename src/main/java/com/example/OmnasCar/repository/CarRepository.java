package com.example.OmnasCar.repository;

import com.example.OmnasCar.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    List<Car> findByBrand(String brand);

    List<Car> findByPriceBetween(double min, double max);

    List<Car> findByLatest(boolean latest);

    List<Car> findByUpcoming(boolean upcoming);

    List<Car> findByElectric(boolean electric);
}

