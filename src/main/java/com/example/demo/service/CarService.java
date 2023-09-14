package com.example.demo.service;

import com.example.demo.data.entity.Car;

import java.util.List;

public interface CarService {
    void create(Car car);
    Car findById(Long id);
    List<Car> findAll();
    void updateById(Long id, Car car);
    void deleteById(Long id);
}
