package com.example.demo.service.impl;

import com.example.demo.data.dto.CarDto;
import com.example.demo.data.entity.Car;
import com.example.demo.data.mapper.impl.CarMapper;
import com.example.demo.exception.NotFoundException;
import com.example.demo.repository.CarRepository;
import com.example.demo.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
        return carMapper.entityToDto(carRepository.findById(id)
                        .orElseThrow(() -> new NotFoundException("Car with id " + id + " not found.")));
    }

    @Override
    public void update(CarDto carDto) {
        Car car = carMapper.dtoToEntity(carDto);
        /*carRepository
                .findByLicensePlate(car.getLicensePlate())
                .ifPresentOrElse(() -> carRepository.save(car), new NotFoundException(""));*/
    }

    @Override
    public void deleteById(Long id) {

    }
}
