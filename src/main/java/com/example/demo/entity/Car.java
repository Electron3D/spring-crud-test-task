package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

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
    private String licencePlate;
    private int power;
    private LocalDate productionYear;
    @ManyToOne
    @JoinColumn(name = "parking_id")
    private Parking parking;
}
