package com.example.demo.service;

import com.example.demo.data.dto.DriverDto;

public interface DriverService {
    void create(DriverDto driverDto);
    DriverDto findById(Long id);
    void update(DriverDto driverDto);
    void deleteById(Long id);
}
