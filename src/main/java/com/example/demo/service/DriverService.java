package com.example.demo.service;

import com.example.demo.data.dto.DriverDto;

import java.util.List;

public interface DriverService {
    void create(DriverDto driverDto);
    DriverDto findById(Long id);
    List<DriverDto> findAll();
    DriverDto updateById(Long id, DriverDto driverDto);
    void deleteById(Long id);
}
