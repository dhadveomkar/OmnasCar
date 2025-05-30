package com.example.OmnasCar.model;

import java.time.LocalDateTime;

public class FavouriteDTO {
    private Long id;
    private String userName;
    private String carName;
    private LocalDateTime favouritedAt;

    // Getters and setters

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }

    public String getCarName() { return carName; }
    public void setCarName(String carName) { this.carName = carName; }

    public LocalDateTime getFavouritedAt() { return favouritedAt; }
    public void setFavouritedAt(LocalDateTime favouritedAt) { this.favouritedAt = favouritedAt; }
}