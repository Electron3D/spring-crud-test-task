package com.example.demo.controller;

import com.example.demo.data.RestResponse;
import com.example.demo.data.dto.CarDto;
import com.example.demo.data.entity.Car;
import com.example.demo.data.mapper.impl.CarMapper;
import com.example.demo.service.CarService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cars")
@RequiredArgsConstructor
public class CarController {
    private final CarService carService;
    private final CarMapper carMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RestResponse createCar(@RequestBody @Valid CarDto carDto) {
        carService.create(carMapper.dtoToEntity(carDto));
        return new RestResponse("Car created");
    }

    @GetMapping("/{id}")
    public RestResponse getCarById(@PathVariable Long id) {
        return new RestResponse(carMapper.entityToDto(carService.findById(id)));
    }

    @GetMapping
    public RestResponse getAllCars() {
        List<Car> allCars = carService.findAll();
        return new RestResponse(allCars.stream().map(carMapper::entityToDto).collect(Collectors.toList()));
    }

    @PutMapping("/{id}")
    public RestResponse updateCarById(@PathVariable Long id, @RequestBody CarDto carDto) {
        carService.updateById(id, carMapper.dtoToEntity(carDto));
        return new RestResponse("Car with ID \"" + id + "\" updated.");
    }

    @DeleteMapping("/{id}")
    public RestResponse deleteCarById(@PathVariable Long id) {
        carService.deleteById(id);
        return new RestResponse("Car with ID \"" + id + "\" deleted");
    }
}
