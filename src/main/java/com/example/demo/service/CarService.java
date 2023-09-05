package com.example.demo.service;

import com.example.demo.data.dto.CarDto;

import java.util.List;

public interface CarService {
    void create(CarDto carDto);
    CarDto findById(Long id);
    List<CarDto> findAll();
    CarDto updateById(Long id, CarDto carDto);
    void deleteById(Long id);
}
