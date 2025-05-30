package com.example.OmnasCar.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Favourite {
   @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Car car;

    private LocalDateTime favouritedAt;

    public Long getId() {
    return id;
}

public void setId(Long id) {
    this.id = id;
}

public User getUser() {
    return user;
}

public void setUser(User user) {
    this.user = user;
}

public Car getCar() {
    return car;
}

public void setCar(Car car) {
    this.car = car;
}

public LocalDateTime getFavouritedAt() {
    return favouritedAt;
}

public void setFavouritedAt(LocalDateTime favouritedAt) {
    this.favouritedAt = favouritedAt;
}
 
}
