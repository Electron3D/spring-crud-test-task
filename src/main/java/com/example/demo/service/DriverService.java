package com.example.demo.service;

import com.example.demo.data.entity.Driver;

import java.util.List;

public interface DriverService {
    void create(Driver driver);
    Driver findById(Long id);
    List<Driver> findAll();
    void updateById(Long id, Driver driver);
    void deleteById(Long id);
}
