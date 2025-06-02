package com.example.OmnasCar.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.OmnasCar.model.Car;
import com.example.OmnasCar.model.Favourite;
import com.example.OmnasCar.model.FavouriteDTO;
import com.example.OmnasCar.model.User;
import com.example.OmnasCar.repository.CarRepository;
import com.example.OmnasCar.repository.FavouriteRepository;
import com.example.OmnasCar.repository.UserRepository;

@Service
public class FavouriteService {
    @Autowired
    private FavouriteRepository favRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private CarRepository carRepo;

    public FavouriteDTO addFavourite(Favourite fav) {
        User user = userRepo.findById(fav.getUser().getId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Car car = carRepo.findById(fav.getCar().getId())
                .orElseThrow(() -> new RuntimeException("Car not found"));

        fav.setUser(user);
        fav.setCar(car);
        fav.setFavouritedAt(LocalDateTime.now());

        Favourite saved = favRepo.save(fav);

        FavouriteDTO dto = new FavouriteDTO();
        dto.setId(saved.getId());
        dto.setUserName(user.getName());
        dto.setCarName(car.getName());
        dto.setFavouritedAt(saved.getFavouritedAt());

        return dto;
    }

    public void removeFavourite(Long userId, Long carId) {
        Favourite fav = favRepo.findByUserIdAndCarId(userId, carId);
        if (fav != null) {
            favRepo.delete(fav);
        }
    }

    public boolean isFavourite(Long userId, Long carId) {
        return favRepo.findByUserIdAndCarId(userId, carId) != null;
    }

    public List<FavouriteDTO> getFavouriteDTOs(Long userId) {
        List<Favourite> favourites = favRepo.findByUserId(userId);
        List<FavouriteDTO> dtoList = new ArrayList<>();

        for (Favourite fav : favourites) {
            FavouriteDTO dto = new FavouriteDTO();
            dto.setId(fav.getId());
            dto.setUserName(fav.getUser().getName());
            dto.setCarName(fav.getCar().getName());
            dto.setFavouritedAt(fav.getFavouritedAt());
            dtoList.add(dto);
        }

        return dtoList;
    }

    public List<FavouriteDTO> getAllFavourites() {
        List<Favourite> favourites = favRepo.findAll();
        List<FavouriteDTO> dtos = new ArrayList<>();

        for (Favourite fav : favourites) {
            FavouriteDTO dto = new FavouriteDTO();
            dto.setId(fav.getId());
            dto.setCarName(fav.getCar().getName());
            dto.setUserName(fav.getUser().getName());
            dto.setFavouritedAt(fav.getFavouritedAt());
            dtos.add(dto);
        }

        return dtos;
    }
}
