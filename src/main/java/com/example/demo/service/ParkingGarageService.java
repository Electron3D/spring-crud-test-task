package com.example.demo.service;

import com.example.demo.data.entity.ParkingGarage;

import java.util.List;

public interface ParkingGarageService {
    void create(ParkingGarage parkingGarage);
    ParkingGarage findById(Long id);
    List<ParkingGarage> findAll();
    void updateById(Long id, ParkingGarage parkingGarage);
    void deleteById(Long id);
}
