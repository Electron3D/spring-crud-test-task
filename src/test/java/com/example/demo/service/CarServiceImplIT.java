package com.example.demo.service;

import com.example.demo.data.entity.Car;
import com.example.demo.exception.NotFoundException;
import com.example.demo.exception.WrongInputException;
import com.example.demo.service.impl.CarServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@SpringBootTest
public class CarServiceImplIT {
    @Autowired
    private CarServiceImpl carService;

    @Test
    @Transactional
    public void should_return_car() {
        Car expectedCar = new Car();
        expectedCar.setModel("abc");
        expectedCar.setBrand("123");
        expectedCar.setParkingStarted(LocalDateTime.now());
        expectedCar.setLicensePlate("abc123");
        carService.create(expectedCar);

        Car actualCar = carService.findById(1L);

        Assertions.assertEquals(expectedCar, actualCar);
    }
    @Test
    @Transactional
    public void should_return_wrong_input_exception() {
        Car expectedCar = new Car();
        expectedCar.setModel("abc");
        expectedCar.setBrand("123");
        expectedCar.setParkingStarted(LocalDateTime.now());
        expectedCar.setLicensePlate("abc123");
        carService.create(expectedCar);

        WrongInputException wrongInputException = Assertions.assertThrows(WrongInputException.class, () -> carService.create(expectedCar));
        String expectedMessage = "Car with license plate \"abc123\" already exist.";
        String actualMessage = wrongInputException.getMessage();
        Assertions.assertEquals(expectedMessage, actualMessage);
    }
    @Test
    @Transactional
    public void should_return_not_found_exception() {
        NotFoundException notFoundException = Assertions.assertThrows(NotFoundException.class, () -> carService.findById(1L));
        String expectedMessage = "Car with ID \"1\" not found.";
        String actualMessage = notFoundException.getMessage();
        Assertions.assertEquals(expectedMessage, actualMessage);
    }
}
