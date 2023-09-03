package com.example.demo.service.impl;

import com.example.demo.data.dto.CarDto;
import com.example.demo.data.mapper.impl.CarMapper;
import com.example.demo.repository.CarRepository;
import com.example.demo.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    private final CarMapper carMapper;

    @Autowired
    public CarServiceImpl(CarRepository carRepository, CarMapper carMapper) {
        this.carRepository = carRepository;
        this.carMapper = carMapper;
    }

    @Override
    public void create(CarDto carDto) {
        carRepository.save(carMapper.dtoToEntity(carDto));
    }

    @Override
    public CarDto findById(Long id) {
        return null;
    }

    @Override
    public void update(CarDto carDto) {

    }

    @Override
    public void deleteById(Long id) {

    }
}
