package com.example.demo.controller;

import com.example.demo.data.RestResponse;
import com.example.demo.data.dto.DriverDto;
import com.example.demo.data.entity.Driver;
import com.example.demo.data.mapper.impl.DriverMapper;
import com.example.demo.service.DriverService;
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
    public RestResponse createDriver(@RequestBody DriverDto driverDto) {
        driverService.create(driverMapper.dtoToEntity(driverDto));
        return new RestResponse("Driver created");
    }

    @GetMapping("/{id}")
    public DriverDto getDriverById(@PathVariable Long id) {
        return driverMapper.entityToDto(driverService.findById(id));
    }

    @GetMapping
    public List<DriverDto> getAllDrivers() {
        List<Driver> allDrivers = driverService.findAll();
        return allDrivers.stream().map(driverMapper::entityToDto).collect(Collectors.toList());
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
