package com.example.demo.data.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Car {
    @Id
    @GeneratedValue
    private Long id;
    private String brand;
    private String model;
    private String vin;
    private String licensePlate;
    private int power;
    private LocalDate productionYear;
    @ManyToOne
    @JoinColumn(name = "parkingGarage_id")
    private ParkingGarage parkingGarage;
    @OneToOne(mappedBy = "car")
    private ParkingLot parkingLot;
    @ManyToMany
    @JoinTable(name = "car_driver",
            joinColumns = @JoinColumn(name = "car_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "driver_id", referencedColumnName = "id"))
    private Set<Driver> drivers;
}
