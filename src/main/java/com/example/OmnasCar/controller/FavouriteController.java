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
    Favourite saved = favService.addFavourite(fav);
    FavouriteDTO dto = new FavouriteDTO();
    dto.setId(saved.getId());
    dto.setUserName(saved.getUser().getName());
    dto.setCarName(saved.getCar().getName());
    dto.setFavouritedAt(saved.getFavouritedAt());

    return ResponseEntity.ok(dto);
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

    @GetMapping("/exists")
    public ResponseEntity<Boolean> isFavourite(@RequestParam Long userId, @RequestParam Long carId) {
        return ResponseEntity.ok(favService.isFavourite(userId, carId));
    }

}


