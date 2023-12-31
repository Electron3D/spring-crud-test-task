package com.example.demo.controller;

import com.example.demo.data.RestResponse;
import com.example.demo.data.dto.DriverDto;
import com.example.demo.data.entity.Driver;
import com.example.demo.data.mapper.impl.DriverMapper;
import com.example.demo.service.DriverService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/drivers")
@RequiredArgsConstructor
public class DriverController {
    private final DriverService driverService;
    private final DriverMapper driverMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RestResponse createDriver(@RequestBody @Valid DriverDto driverDto) {
        driverService.create(driverMapper.dtoToEntity(driverDto));
        return new RestResponse("Driver created");
    }

    @GetMapping("/{id}")
    public RestResponse getDriverById(@PathVariable Long id) {
        return new RestResponse(driverMapper.entityToDto(driverService.findById(id)));
    }

    @GetMapping
    public RestResponse getAllDrivers() {
        List<Driver> allDrivers = driverService.findAll();
        return new RestResponse(allDrivers.stream().map(driverMapper::entityToDto).collect(Collectors.toList()));
    }

    @PutMapping("/{id}")
    public RestResponse updateDriverById(@PathVariable Long id, @RequestBody DriverDto driverDto) {
        driverService.updateById(id, driverMapper.dtoToEntity(driverDto));
        return new RestResponse("Driver with ID \"" + id + "\" updated.");
    }

    @DeleteMapping("/{id}")
    public RestResponse deleteDriverById(@PathVariable Long id) {
        driverService.deleteById(id);
        return new RestResponse("Driver with ID \"" + id + "\" deleted");
    }
}
