package com.example.demo.service.impl;

import com.example.demo.data.dto.CarDto;
import com.example.demo.data.entity.Car;
import com.example.demo.data.mapper.impl.CarMapper;
import com.example.demo.exception.NotFoundException;
import com.example.demo.repository.CarRepository;
import com.example.demo.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    @Transactional
    public void create(CarDto carDto) {
        carDto.setParkingStarted(LocalDateTime.now());
        carRepository.save(carMapper.dtoToEntity(carDto, new Car()));
    }

    @Override
    public CarDto findById(Long id) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Car with ID \"" + id + "\" not found."));
        return carMapper.entityToDto(car);
    }

    @Override
    public List<CarDto> findAll() {
        return carRepository
                .findAll()
                .stream()
                .map(carMapper::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public CarDto updateById(Long id, CarDto carDto) {
        Optional<Car> carOptional = carRepository.findById(id);
        if (carOptional.isPresent()) {
            Car existedCar = carOptional.get();

            Car updatedCar = carMapper.dtoToEntity(carDto, existedCar);
            return carMapper.entityToDto(updatedCar);
        } else throw new NotFoundException("Car with ID \"" + id + "\" not found.");
    }

    @Override
    public void deleteById(Long id) {
        if (carRepository.existsById(id)) {
            carRepository.deleteById(id);
        } else {
            throw new NotFoundException("Car with ID \"" + id + "\" not found");
        }
    }
}
