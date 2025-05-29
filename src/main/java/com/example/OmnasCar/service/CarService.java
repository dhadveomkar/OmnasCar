package com.example.OmnasCar.service;

import com.example.OmnasCar.model.Car;
import com.example.OmnasCar.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    public Car addCar(Car car) {
        return carRepository.save(car);
    }

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public Optional<Car> getCarById(Long id) {
        return carRepository.findById(id);
    }

    public List<Car> getCarsByBrand(String brand) {
        return carRepository.findByBrand(brand);
    }

    public List<Car> getCarsByPriceRange(double min, double max) {
        return carRepository.findByPriceBetween(min, max);
    }

    public List<Car> getLatestCars() {
        return carRepository.findByLatest(true);
    }

    public List<Car> getUpcomingCars() {
        return carRepository.findByUpcoming(true);
    }

    public List<Car> getElectricCars() {
        return carRepository.findByElectric(true);
    }

    public Car updateCar(Long id, Car carDetails) {
        return carRepository.findById(id).map(car -> {
            car.setName(carDetails.getName());
            car.setBrand(carDetails.getBrand());
            car.setType(carDetails.getType());
            car.setPrice(carDetails.getPrice());
            car.setLatest(carDetails.isLatest());
            car.setUpcoming(carDetails.isUpcoming());
            car.setElectric(carDetails.isElectric());
            return carRepository.save(car);
        }).orElse(null);
    }

    public boolean deleteCar(Long id) {
        if (carRepository.existsById(id)) {
            carRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
