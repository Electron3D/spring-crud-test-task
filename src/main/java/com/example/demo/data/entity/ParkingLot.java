package com.example.demo.data.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ParkingLot {
    @Id
    @GeneratedValue
    private Long id;
    private int number;
    private boolean occupied;
    @ManyToOne
    @JoinColumn(name = "parkingGarage_id")
    private ParkingGarage parkingGarage;
    @OneToOne
    @JoinColumn(name = "car_id")
    private Car car;
}
