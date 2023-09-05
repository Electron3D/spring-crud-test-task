package com.example.demo.service;

import com.example.demo.data.dto.ParkingGarageDto;

import java.util.List;

public interface ParkingGarageService {
    void create(ParkingGarageDto parkingGarageDto);
    ParkingGarageDto findById(Long id);
    List<ParkingGarageDto> findAll();
    ParkingGarageDto updateById(Long id, ParkingGarageDto parkingGarageDto);
    void deleteById(Long id);
}
