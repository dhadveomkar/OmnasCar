package com.example.OmnasCar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.OmnasCar.model.FavouriteDTO;
import com.example.OmnasCar.model.Favourite;
import com.example.OmnasCar.service.FavouriteService;

@RestController
@RequestMapping("/api/favourites")
public class FavouriteController {

    @Autowired
    private FavouriteService favService;

    @PostMapping
    public ResponseEntity<FavouriteDTO> addFavourite(@RequestBody Favourite fav) {
        return ResponseEntity.ok(favService.addFavourite(fav));
    }

    @DeleteMapping
    public ResponseEntity<Void> removeFavourite(@RequestParam Long userId, @RequestParam Long carId) {
        favService.removeFavourite(userId, carId);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<FavouriteDTO>> getAllFavourites() {
        return ResponseEntity.ok(favService.getAllFavourites());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<FavouriteDTO>> getUserFavourites(@PathVariable Long userId) {
        return ResponseEntity.ok(favService.getFavouriteDTOs(userId));
    }

    

}
