package com.example.demo.service;

import com.example.demo.data.dto.ParkingGarageDto;

public interface ParkingGarageService {
    void create(ParkingGarageDto parkingGarageDto);
    ParkingGarageDto findById(Long id);
    void update(ParkingGarageDto parkingGarageDto);
    void deleteById(Long id);
}
