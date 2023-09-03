package com.example.demo.data.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ParkingGarage {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private int capacity;
    private LocalDate constructionYear;
    @OneToMany(mappedBy = "parkingGarage")
    private List<ParkingLot> parkingLots;
    @OneToMany(mappedBy = "parkingGarage")
    private List<Car> cars;
    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;
}
