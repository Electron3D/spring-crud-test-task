package com.example.demo.service.impl;

import com.example.demo.data.entity.Car;
import com.example.demo.data.entity.ParkingSlot;
import com.example.demo.exception.NotFoundException;
import com.example.demo.exception.WrongInputException;
import com.example.demo.repository.CarRepository;
import com.example.demo.repository.ParkingSlotRepository;
import com.example.demo.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    private final ParkingSlotRepository parkingSlotRepository;

    @Autowired
    public CarServiceImpl(CarRepository carRepository, ParkingSlotRepository parkingGarageRepository) {
        this.carRepository = carRepository;
        this.parkingSlotRepository = parkingGarageRepository;
    }

    @Override
    @Transactional
    public void create(Car car) {
        String licensePlate = car.getLicensePlate();
        carRepository.findByLicensePlate(licensePlate).ifPresent((existedCar) -> {
            throw new WrongInputException("Car with license plate \"" + licensePlate + "\" already exist.");
        });
        car.setParkingStarted(LocalDateTime.now());
        carRepository.save(car);
    }

    @Override
    @Transactional(readOnly = true)
    public Car findById(Long id) {
        return carRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Car with ID \"" + id + "\" not found."));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Car> findAll() {
        return new ArrayList<>(carRepository.findAll());
    }

    @Override
    @Transactional
    public void updateById(Long id, Car car) {
        Car existingCar = carRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Car with ID \"" + id + "\" not found."));
        existingCar.setBrand(car.getBrand());
        existingCar.setModel(car.getModel());
        carRepository.save(existingCar);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Optional<Car> optionalCar = carRepository.findById(id);
        if (optionalCar.isPresent()) {
            ParkingSlot parkingSlot = optionalCar.get().getParkingSlot();
            parkingSlot.setCar(null);
            parkingSlot.setOccupied(false);
            parkingSlotRepository.save(parkingSlot);
            carRepository.deleteById(id);
        } else {
            throw new NotFoundException("Car with ID \"" + id + "\" not found.");
        }
    }
}
