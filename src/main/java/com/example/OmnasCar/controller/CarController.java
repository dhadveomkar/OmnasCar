package com.example.OmnasCar.controller;

import com.example.OmnasCar.model.Car;
import com.example.OmnasCar.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/cars")
public class CarController {

    @Autowired
    private CarRepository carRepository;

    @PostMapping("/add")
    public Car addCar(@RequestBody Car car) {
        return carRepository.save(car);
    }

    @GetMapping("/all")
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCarById(@PathVariable Long id) {
        return carRepository.findById(id)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body("Car not found"));
    }

    @GetMapping("/brand")
    public List<Car> getByBrand(@RequestParam String brand) {
        return carRepository.findByBrand(brand);
    }

    @GetMapping("/price")
    public List<Car> getByPriceRange(@RequestParam double min, @RequestParam double max) {
        return carRepository.findByPriceBetween(min, max);
    }

    @GetMapping("/latest")
    public List<Car> getLatestCars() {
        return carRepository.findByLatest(true);
    }

    @GetMapping("/upcoming")
    public List<Car> getUpcomingCars() {
        return carRepository.findByUpcoming(true);
    }

    @GetMapping("/electric")
    public List<Car> getElectricCars() {
        return carRepository.findByElectric(true);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCar(@PathVariable Long id, @RequestBody Car updatedCar) {
        return carRepository.findById(id).map(car -> {
            car.setName(updatedCar.getName());
            car.setBrand(updatedCar.getBrand());
            car.setType(updatedCar.getType());
            car.setPrice(updatedCar.getPrice());
            car.setLatest(updatedCar.isLatest());
            car.setUpcoming(updatedCar.isUpcoming());
            car.setElectric(updatedCar.isElectric());
            return ResponseEntity.ok(carRepository.save(car));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
public ResponseEntity<?> deleteCar(@PathVariable Long id) {
    if (carRepository.existsById(id)) {
        carRepository.deleteById(id);
        // ✅ Return a structured JSON response
        return ResponseEntity.ok(Map.of("message", "Car deleted successfully"));
    } else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("error", "Car not found"));
    }
}

}
