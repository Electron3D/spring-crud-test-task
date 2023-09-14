package com.example.demo.data.mapper.impl;

import com.example.demo.data.dto.CarDto;
import com.example.demo.data.entity.Car;
import com.example.demo.data.entity.Driver;
import com.example.demo.data.entity.ParkingGarage;
import com.example.demo.data.entity.ParkingSlot;
import com.example.demo.data.mapper.AbstractMapper;
import com.example.demo.exception.NotFoundException;
import com.example.demo.exception.NotProvidedException;
import com.example.demo.exception.WrongInputException;
import com.example.demo.repository.DriverRepository;
import com.example.demo.repository.ParkingGarageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class CarMapper extends AbstractMapper<Car, CarDto> {
    private final ParkingGarageRepository parkingGarageRepository;
    private final DriverRepository driverRepository;

    @Autowired
    public CarMapper(ParkingGarageRepository parkingGarageRepository, DriverRepository driverRepository) {
        this.parkingGarageRepository = parkingGarageRepository;
        this.driverRepository = driverRepository;
    }

    @Override
    @Transactional
    public Car dtoToEntity(CarDto carDto) {
        Car car = new Car();
        car.setBrand(carDto.getBrand());
        car.setModel(carDto.getModel());
        car.setLicensePlate(carDto.getLicensePlate());

        int parkingSlotNumber = carDto.getParkingSlot();
        String parkingGarageName = carDto.getParkingName();
        if (parkingGarageName!= null && !parkingGarageName.isBlank()) {
            ParkingGarage garage = parkingGarageRepository.findByName(parkingGarageName)
                    .orElseThrow(() -> new NotFoundException("Parking garage \"" + parkingGarageName + "\" not found."));
            List<ParkingSlot> parkingSlots = garage.getParkingSlots();
            if (parkingSlots.size() >= parkingSlotNumber) {
                ParkingSlot parkingSlot = parkingSlots.get(parkingSlotNumber - 1);
                if (parkingSlot.isOccupied()) {
                    throw new WrongInputException("Parking slot " + parkingSlotNumber + " is already occupied.");
                }
                parkingSlot.setOccupied(true);
                car.setParkingSlot(parkingSlot);
            } else throw new NotFoundException(
                    "Parking slot \"" + parkingSlotNumber + "\" in garage \"" + parkingGarageName + "\" not found.");
        } else throw new NotProvidedException("Please provide parking garage name.");

        Set<String> driversLicenses = carDto.getDrivers();
        if (driversLicenses != null && !driversLicenses.isEmpty()) {
            Set<Driver> drivers = driversLicenses.stream()
                    .map(driverLicense -> driverRepository
                            .findByDriverLicense(driverLicense)
                            .orElseThrow(() -> new NotFoundException(
                                    "Driver with driver license " + driverLicense + " not found.")))
                    .collect(Collectors.toSet());
            car.setDrivers(drivers);
        } else throw new NotProvidedException("Please provide information about drivers.");
        return car;
    }

    @Override
    @Transactional
    public CarDto entityToDto(Car car) {
        CarDto carDto = new CarDto();
        carDto.setBrand(car.getBrand());
        carDto.setModel(car.getModel());
        carDto.setLicensePlate(car.getLicensePlate());
        carDto.setParkingStarted(car.getParkingStarted());

        ParkingSlot parkingSlot = car.getParkingSlot();
        if (parkingSlot != null) {
            carDto.setParkingName(parkingSlot.getParkingGarage().getName());
            carDto.setParkingSlot(parkingSlot.getSlotNumber());
        }

        Set<Driver> drivers = car.getDrivers();
        if (drivers != null) {
            carDto.setDrivers(drivers
                    .stream()
                    .map(Driver::getDriverLicense)
                    .collect(Collectors.toSet()));
        }
        return carDto;
    }
}
