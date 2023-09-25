package com.example.demo.controller;

import com.example.demo.data.RestResponse;
import com.example.demo.data.dto.ParkingGarageDto;
import com.example.demo.data.entity.ParkingGarage;
import com.example.demo.data.mapper.impl.ParkingGarageMapper;
import com.example.demo.service.ParkingGarageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/garages")
@RequiredArgsConstructor
public class ParkingGarageController {
    private final ParkingGarageService parkingGarageService;
    private final ParkingGarageMapper parkingGarageMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RestResponse createParkingGarage(@RequestBody @Valid ParkingGarageDto parkingGarageDto) {
        parkingGarageService.create(parkingGarageMapper.dtoToEntity(parkingGarageDto));
        return new RestResponse("Parking garage created");
    }

    @GetMapping("/{id}")
    public RestResponse getParkingGarageById(@PathVariable Long id) {
        return new RestResponse(parkingGarageMapper.entityToDto(parkingGarageService.findById(id)));
    }

    @GetMapping
    public RestResponse getAllParkingGarages() {
        List<ParkingGarage> allGarages = parkingGarageService.findAll();
        return new RestResponse(allGarages.stream().map(parkingGarageMapper::entityToDto).collect(Collectors.toList()));
    }

    @PutMapping("/{id}")
    public RestResponse updateParkingGarageById(@PathVariable Long id, @RequestBody ParkingGarageDto parkingGarageDto) {
        parkingGarageService.updateById(id, parkingGarageMapper.dtoToEntity(parkingGarageDto));
        return new RestResponse("Parking garage with id: " + id + " updated.");
    }

    @DeleteMapping("/{id}")
    public RestResponse deleteParkingGarageById(@PathVariable Long id) {
        parkingGarageService.deleteById(id);
        return new RestResponse("Parking garage with id: " + id + " deleted");
    }
}
