package com.example.demo.entity;

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
public class Parking {
    @Id
    @GeneratedValue
    private Long id;
    private int capacity;
    private LocalDate constructionYear;
    @OneToMany(mappedBy = "parking")
    private List<Car> cars;
    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;
}
