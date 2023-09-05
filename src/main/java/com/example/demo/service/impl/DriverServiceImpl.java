package com.example.demo.service.impl;

import com.example.demo.data.dto.DriverDto;
import com.example.demo.repository.DriverRepository;
import com.example.demo.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriverServiceImpl implements DriverService {
    private final DriverRepository driverRepository;

    @Autowired
    public DriverServiceImpl(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }
    @Override
    public void create(DriverDto driverDto) {

    }

    @Override
    public DriverDto findById(Long id) {
        return null;
    }

    @Override
    public List<DriverDto> findAll() {
        return null;
    }

    @Override
    public DriverDto updateById(Long id, DriverDto driverDto) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }
}
