package com.example.demo.controller;

import com.example.demo.data.RestResponse;
import com.example.demo.data.dto.CarDto;
import com.example.demo.data.dto.ParkingGarageDto;
import com.example.demo.service.ParkingGarageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/garages")
@RequiredArgsConstructor
public class ParkingGarageController {
    private final ParkingGarageService parkingGarageService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RestResponse createParkingGarage(@RequestBody ParkingGarageDto parkingGarageDto) {
        parkingGarageService.create(parkingGarageDto);
        return new RestResponse("Parking garage created");
    }

    @GetMapping("/{id}")
    public ParkingGarageDto getParkingGarageById(@PathVariable Long id) {
        return parkingGarageService.findById(id);
    }

    @GetMapping
    public List<ParkingGarageDto> getAllParkingGarages() {
        return parkingGarageService.findAll();
    }

    @PutMapping("/{id}")
    public RestResponse updateParkingGarageById(@PathVariable Long id, @RequestBody ParkingGarageDto parkingGarageDto) {
        parkingGarageService.updateById(id, parkingGarageDto);
        return new RestResponse("Parking garage with id: " + id + " updated.");
    }

    @DeleteMapping("/{id}")
    public RestResponse deleteParkingGarageById(@PathVariable Long id) {
        parkingGarageService.deleteById(id);
        return new RestResponse("Parking garage with id: " + id + " deleted");
    }
}
