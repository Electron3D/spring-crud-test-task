package com.example.demo.controller;

import com.example.demo.data.RestResponse;
import com.example.demo.data.dto.DriverDto;
import com.example.demo.service.DriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/drivers")
@RequiredArgsConstructor
public class DriverController {
    private final DriverService driverService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RestResponse createDriver(@RequestBody DriverDto driverDto) {
        driverService.create(driverDto);
        return new RestResponse("Driver created");
    }

    @GetMapping("/{id}")
    public DriverDto getDriverById(@PathVariable Long id) {
        return driverService.findById(id);
    }

    @GetMapping
    public List<DriverDto> getAllDrivers() {
        return driverService.findAll();
    }

    @PutMapping("/{id}")
    public RestResponse updateDriverById(@PathVariable Long id, @RequestBody DriverDto driverDto) {
        driverService.updateById(id, driverDto);
        return new RestResponse("Driver with id: " + id + " updated.");
    }

    @DeleteMapping("/{id}")
    public RestResponse deleteDriverById(@PathVariable Long id) {
        driverService.deleteById(id);
        return new RestResponse("Driver with id: " + id + " deleted");
    }
}
