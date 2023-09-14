package com.example.demo.service.impl;

import com.example.demo.data.dto.DriverDto;
import com.example.demo.data.entity.Car;
import com.example.demo.data.entity.Driver;
import com.example.demo.data.mapper.impl.DriverMapper;
import com.example.demo.exception.NotFoundException;
import com.example.demo.repository.CarRepository;
import com.example.demo.repository.DriverRepository;
import com.example.demo.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DriverServiceImpl implements DriverService {
    private final DriverRepository driverRepository;
    private final CarRepository carRepository;

    @Autowired
    public DriverServiceImpl(DriverRepository driverRepository, CarRepository carRepository) {
        this.driverRepository = driverRepository;
        this.carRepository = carRepository;
    }
    @Override
    @Transactional
    public void create(Driver driver) {
        driverRepository.save(driver);
    }

    @Override
    @Transactional(readOnly = true)
    public Driver findById(Long id) {
        return driverRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Driver with ID \"" + id + "\" not found."));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Driver> findAll() {
        return new ArrayList<>(driverRepository.findAll());
    }

    //redo
    @Override
    @Transactional
    public void updateById(Long id, Driver driver) {
        Optional<Driver> driverOptional = driverRepository.findById(id);
        if (driverOptional.isPresent()) {
            Driver existedDriver = driverOptional.get();
        } else throw new NotFoundException("Driver with ID \"" + id + "\" not found.");
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Optional<Driver> optionalDriver = driverRepository.findById(id);
        if (optionalDriver.isPresent()) {
            Set<Car> cars = optionalDriver.get().getCars();
            for (Car car : cars) {
                if (car.getDrivers().size() == 1) {
                    carRepository.deleteById(car.getId());
                }
            }
            driverRepository.deleteById(id);
        } else {
            throw new NotFoundException("Driver with ID \"" + id + "\" not found");
        }
    }
}
