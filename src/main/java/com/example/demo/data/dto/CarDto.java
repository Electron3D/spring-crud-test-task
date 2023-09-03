package com.example.demo.data.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CarDto {
    private String brand;
    private String model;
    private String vin;
    private String licensePlate;
    private int power;
    private LocalDate productionYear;
    private String parkingGarage;
    private Integer parkingLot;
    private Set<String> drivers;

    /*public String getCarName() {
        return brand + " " + model + ", LP: " + licensePlate;
    }*/
}
