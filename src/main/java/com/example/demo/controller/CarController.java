package com.example.demo.controller;

import com.example.demo.data.RestResponse;
import com.example.demo.data.dto.CarDto;
import com.example.demo.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cars")
@RequiredArgsConstructor
public class CarController {
    private final CarService carService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RestResponse createCar(@RequestBody CarDto carDto) {
        carService.create(carDto);
        return new RestResponse("Car created");
    }

    @GetMapping("/{id}")
    public CarDto getCarById(@PathVariable Long id) {
        return carService.findById(id);
    }

    @GetMapping
    public List<CarDto> getAllCars() {
        return carService.findAll();
    }

    @PutMapping("/{id}")
    public RestResponse updateCarById(@PathVariable Long id, @RequestBody CarDto carDto) {
        carService.updateById(id, carDto);
        return new RestResponse("Car with id: " + id + " updated.");
    }

    @DeleteMapping("/{id}")
    public RestResponse deleteCarById(@PathVariable Long id) {
        carService.deleteById(id);
        return new RestResponse("Car with id: " + id + " deleted");
    }
}
