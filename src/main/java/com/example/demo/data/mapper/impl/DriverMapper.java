package com.example.demo.data.mapper.impl;

import com.example.demo.data.dto.DriverDto;
import com.example.demo.data.entity.Car;
import com.example.demo.data.entity.Driver;
import com.example.demo.data.entity.ParkingGarage;
import com.example.demo.data.mapper.AbstractMapper;
import com.example.demo.exception.NotFoundException;
import com.example.demo.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class DriverMapper extends AbstractMapper<Driver, DriverDto> {
    private final CarRepository carRepository;
    @Autowired
    public DriverMapper(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    @Transactional
    public Driver dtoToEntity(DriverDto dto) {
        Driver driver = new Driver();
        driver.setFirstName(dto.getFirstName());
        driver.setLastName(dto.getLastName());
        driver.setDriverLicense(dto.getDriverLicense());
        driver.setBirthday(dto.getBirthday());
        driver.setPhoneNumber(dto.getPhoneNumber());
        Set<String> carsLicensePlates = dto.getCars();
        if (carsLicensePlates != null) {
            Set<Car> cars = carsLicensePlates.stream()
                    .map(licensePlate -> carRepository
                            .findByLicensePlate(licensePlate)
                            .orElseThrow(() -> new NotFoundException(
                                    "Car with license plate " + licensePlate + " not found.")))
                    .collect(Collectors.toSet());
            driver.setCars(cars);
        }
        return driver;
    }

    @Override
    public DriverDto entityToDto(Driver driver) {
        DriverDto driverDto = new DriverDto();
        driverDto.setFirstName(driver.getFirstName());
        driverDto.setLastName(driver.getLastName());
        driverDto.setDriverLicense(driver.getDriverLicense());
        driverDto.setBirthday(driver.getBirthday());
        driverDto.setPhoneNumber(driver.getPhoneNumber());

        Set<Car> cars = driver.getCars();
        if (cars != null) {
            driverDto.setParkingDebt(calculateDebt(cars));
            driverDto.setCars(cars
                    .stream()
                    .map(Car::getLicensePlate)
                    .collect(Collectors.toSet()));
        }
        return driverDto;
    }

    public Double calculateDebt(Set<Car> cars) {
        double debt = 0.00;
        for (Car car : cars) {
            ParkingGarage parkingGarage = car.getParkingSlot().getParkingGarage();
            Duration duration = Duration.between(LocalDateTime.now(), car.getParkingStarted());
            long hoursDifference = duration.toHours();
            if (hoursDifference == 0) {
                hoursDifference = 1;
            }
            int parkingRate = parkingGarage.getParkingRate();
            debt = debt + hoursDifference * parkingRate;
        }
        return debt;
    }
}
