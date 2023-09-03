package com.example.demo.data.mapper.impl;

import com.example.demo.data.dto.CarDto;
import com.example.demo.data.entity.Car;
import com.example.demo.data.entity.Driver;
import com.example.demo.data.entity.ParkingGarage;
import com.example.demo.data.mapper.AbstractMapper;
import com.example.demo.exception.NotFoundException;
import com.example.demo.repository.ParkingGarageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
        car.setVin(carDto.getVin());
        car.setLicensePlate(carDto.getLicensePlate());
        car.setPower(carDto.getPower());
        car.setProductionYear(carDto.getProductionYear());
        String parkingGarageName = carDto.getParkingGarage();
        if (parkingGarageName != null) {
            Optional<ParkingGarage> optionalParkingGarage = parkingGarageRepository.findByName(parkingGarageName);
            optionalParkingGarage.ifPresentOrElse(car::setParkingGarage, () -> {
                throw new NotFoundException("Parking garage \"" + parkingGarageName + "\" not found.");
            });

            Integer parkingLot = carDto.getParkingLot();
            if (parkingLot != null) {
                //check parkingLot in parkingGarage
                if (optionalParkingGarage.isPresent()) {
                    //check number in garage
                    //check availability
                    //setParkingLot
                }
            }
        }
        return car;
    }

    @Override
    public CarDto entityToDto(Car car) {
        CarDto carDto = new CarDto();
        carDto.setBrand(car.getBrand());
        carDto.setModel(car.getModel());
        carDto.setVin(car.getVin());
        carDto.setLicensePlate(car.getLicensePlate());
        carDto.setPower(car.getPower());
        carDto.setProductionYear(car.getProductionYear());
        carDto.setParkingLot(car.getParkingLot().getNumber());
        carDto.setParkingGarage(car.getParkingGarage().getName());

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
