package com.example.OmnasCar.model;

import jakarta.persistence.*;

@Entity
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String brand;
    private String type;

    private double price;

    @Column(name = "is_latest", nullable = false)
    private boolean latest;

    @Column(name = "is_upcoming", nullable = false)
    private boolean upcoming;

    @Column(name = "is_electric", nullable = false)
    private boolean electric;

    // ✅ Default constructor (required by JPA)
    public Car() {}

    // ✅ Parameterized constructor (optional)
    public Car(String name, String brand, String type, double price, boolean latest, boolean upcoming, boolean electric) {
        this.name = name;
        this.brand = brand;
        this.type = type;
        this.price = price;
        this.latest = latest;
        this.upcoming = upcoming;
        this.electric = electric;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBrand() {
        return brand;
    }

    public String getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }

    public boolean isLatest() {
        return latest;
    }

    public boolean isUpcoming() {
        return upcoming;
    }

    public boolean isElectric() {
        return electric;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setLatest(boolean latest) {
        this.latest = latest;
    }

    public void setUpcoming(boolean upcoming) {
        this.upcoming = upcoming;
    }

    public void setElectric(boolean electric) {
        this.electric = electric;
    }
}
