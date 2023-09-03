package com.example.demo.service;

import com.example.demo.data.dto.CarDto;

public interface CarService {
    void create(CarDto carDto);
    CarDto findById(Long id);
    void update(CarDto carDto);
    void deleteById(Long id);
}
