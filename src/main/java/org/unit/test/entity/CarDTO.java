package org.unit.test.entity;

import java.time.LocalDate;

public class CarDTO {
    private String brand;
    private LocalDate releaseDate;
    private String model;
    private String color;
    private Double kilometers;
    private Double price;


    public CarDTO() {
    }

    public CarDTO(String brand, LocalDate releaseDate, String model, String color, Double kilometers, Double price) {
        this.brand = brand;
        this.releaseDate = releaseDate;
        this.model = model;
        this.color = color;
        this.kilometers = kilometers;
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Double getKilometers() {
        return kilometers;
    }

    public void setKilometers(Double kilometers) {
        this.kilometers = kilometers;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
