package com.example.demo.service;

import com.example.demo.data.entity.Car;
import com.example.demo.data.entity.ParkingGarage;
import com.example.demo.data.entity.ParkingSlot;
import com.example.demo.exception.NotFoundException;
import com.example.demo.exception.WrongInputException;
import com.example.demo.service.impl.CarServiceImpl;
import com.example.demo.service.impl.ParkingGarageServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@SpringBootTest
@Sql({"/createTablesInDb.sql", "/insertGarageInDb.sql"})
public class CarServiceImplIT {
    private final CarServiceImpl carService;
    private final ParkingGarageServiceImpl parkingGarageService;

    @Autowired
    public CarServiceImplIT(CarServiceImpl carService,
                            ParkingGarageServiceImpl parkingGarageService) {
        this.carService = carService;
        this.parkingGarageService = parkingGarageService;
    }

    @Test
    @Transactional
    public void should_return_car_IT() {
        Car expectedCar = new Car();
        expectedCar.setModel("200");
        expectedCar.setBrand("Audi");
        expectedCar.setParkingStarted(LocalDateTime.now());
        expectedCar.setLicensePlate("abc123");
        carService.create(expectedCar);
        Car actualCar = carService.findById(1L);

        Assertions.assertEquals(expectedCar, actualCar);
    }
    @Test
    @Transactional
    public void should_return_updated_car_IT() {
        Car expectedCar = new Car();
        expectedCar.setBrand("Audi");
        expectedCar.setLicensePlate("abc123");
        carService.create(expectedCar);
        expectedCar.setBrand("Toyota");
        carService.updateById(1L, expectedCar);
        Car actualCar = carService.findById(1L);

        Assertions.assertEquals(expectedCar, actualCar);
    }
    @Test
    @Transactional
    public void should_return_wrong_input_exception_IT() {
        Car expectedCar = new Car();
        expectedCar.setLicensePlate("abc123");
        carService.create(expectedCar);
        WrongInputException wrongInputException = Assertions.assertThrows(WrongInputException.class, () -> carService.create(expectedCar));
        String expectedMessage = "Car with license plate \"abc123\" already exist.";
        String actualMessage = wrongInputException.getMessage();
        Assertions.assertEquals(expectedMessage, actualMessage);
    }
    @Test
    @Transactional
    public void should_return_not_found_exception_IT() {
        ParkingGarage actualGarage = parkingGarageService.findById(1L);
        ParkingSlot actualSlot = actualGarage.getParkingSlots().get(0);
        Car expectedCar = new Car();
        expectedCar.setLicensePlate("abc123");
        expectedCar.setParkingSlot(actualSlot);
        carService.create(expectedCar);
        carService.deleteById(1L);

        NotFoundException notFoundException = Assertions.assertThrows(NotFoundException.class, () -> carService.findById(1L));
        String expectedMessage = "Car with ID \"1\" not found.";
        String actualMessage = notFoundException.getMessage();
        Assertions.assertEquals(expectedMessage, actualMessage);
    }
}
