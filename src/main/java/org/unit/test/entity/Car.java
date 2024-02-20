package org.unit.test.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
public class Car {

    @Id
    @GeneratedValue
    private Long id;

    private String brand;
    private LocalDate releaseDate;
    private String model;
    private String color;
    private Double kilometers;
    private Double price;

    public Car() {
    }

    public Car(Long id, String brand, LocalDate releaseDate, String model, String color, Double kilometers, Double price) {
        this.id = id;
        this.brand = brand;
        this.releaseDate = releaseDate;
        this.model = model;
        this.color = color;
        this.kilometers = kilometers;
        this.price = price;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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


    public static Car fromDTO(CarDTO carDTO) {
        Car car = new Car();
        car.setBrand(carDTO.getBrand());
        car.setColor(carDTO.getColor());
        car.setKilometers(carDTO.getKilometers());
        car.setModel(carDTO.getModel());
        car.setPrice(carDTO.getPrice());
        car.setReleaseDate(carDTO.getReleaseDate());
        return car;
    }

    public static CarDTO toDTO(Car car) {
        if (car == null) {
            return null;
        }
        CarDTO carDTO = new CarDTO();
        carDTO.setBrand(car.getBrand());
        carDTO.setColor(car.getColor());
        carDTO.setKilometers(car.getKilometers());
        carDTO.setModel(car.getModel());
        carDTO.setPrice(car.getPrice());
        carDTO.setReleaseDate(car.getReleaseDate());
        return carDTO;
    }
}

