package com.example.demo.data.mapper.impl;

import com.example.demo.data.dto.CarDto;
import com.example.demo.data.entity.Car;
import com.example.demo.data.entity.Driver;
import com.example.demo.data.entity.ParkingGarage;
import com.example.demo.data.entity.ParkingSlot;
import com.example.demo.data.mapper.AbstractMapper;
import com.example.demo.exception.NotFoundException;
import com.example.demo.exception.SlotOccupiedException;
import com.example.demo.repository.ParkingGarageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class CarMapper implements AbstractMapper<Car, CarDto> {
    private final ParkingGarageRepository parkingGarageRepository;
    @Autowired
    public CarMapper(ParkingGarageRepository parkingGarageRepository) {
        this.parkingGarageRepository = parkingGarageRepository;
    }

    @Override
    public Car dtoToEntity(CarDto carDto) {
        Car car = new Car();
        car.setBrand(carDto.getBrand());
        car.setModel(carDto.getModel());
        car.setLicensePlate(carDto.getLicensePlate());
        car.setParkingStarted(carDto.getParkingStarted());

        Integer parkingSlotNumber = carDto.getParkingSlot();
        String parkingGarageName = carDto.getParkingName();
        if (parkingGarageName != null) {
            Optional<ParkingGarage> parkingGarage = parkingGarageRepository.findByName(parkingGarageName);
            if (parkingGarage.isPresent()) {
                ParkingGarage garage = parkingGarage.get();
                List<ParkingSlot> parkingSlots = garage.getParkingSlots();
                if (parkingSlots.size() > parkingSlotNumber) {
                    ParkingSlot parkingSlot = parkingSlots.get(parkingSlotNumber);
                    if (parkingSlot.isOccupied()) {
                        throw new SlotOccupiedException("Parking slot " + parkingSlotNumber + " is occupied.");
                    }
                    car.setParkingSlot(parkingSlot);
                } else throw new NotFoundException(
                        "Parking slot \"" + parkingSlotNumber + " in garage \"" + parkingGarageName + " not found.");
            } else throw new NotFoundException("Parking garage \"" + parkingGarageName + "\" not found.");
        }

        return car;
    }

    @Override
    public CarDto entityToDto(Car car) {
        CarDto carDto = new CarDto();
        carDto.setBrand(car.getBrand());
        carDto.setModel(car.getModel());
        carDto.setLicensePlate(car.getLicensePlate());

        Set<Driver> drivers = car.getDrivers();
        if (drivers != null) {
            carDto.setDrivers(drivers
                    .stream()
                    .map(this::getDriverFullName)
                    .collect(Collectors.toSet()));
        }
        return carDto;
    }

    public String getDriverFullName(Driver driver) {
        return driver.getFirstName() + " "
                + driver.getLastName() + ", DL: "
                + driver.getDriverLicense();
    }
}
