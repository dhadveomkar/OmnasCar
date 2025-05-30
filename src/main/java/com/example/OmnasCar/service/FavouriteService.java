package com.example.OmnasCar.service;


import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.OmnasCar.model.Favourite;
import com.example.OmnasCar.model.FavouriteDTO;
import com.example.OmnasCar.repository.FavouriteRepository;

@Service
public class FavouriteService {
   @Autowired
    private FavouriteRepository favRepo;

    public Favourite addFavourite(Favourite fav) {
        fav.setFavouritedAt(java.time.LocalDateTime.now());
        return favRepo.save(fav);
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
