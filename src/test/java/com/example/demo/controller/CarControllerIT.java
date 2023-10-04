package com.example.demo.controller;

import com.example.demo.data.RestResponse;
import com.example.demo.data.dto.CarDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDateTime;
import java.util.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql({"/createTablesInDb.sql", "/insertCarIntoDb.sql", "/insertDriverIntoDb.sql", "/insertGarageIntoDb.sql"})
public class CarControllerIT {
    private final TestRestTemplate testRestTemplate;

    @Autowired
    public CarControllerIT(TestRestTemplate testRestTemplate) {
        this.testRestTemplate = testRestTemplate;
    }

    @Test
    public void should_return_car() {
        ResponseEntity<RestResponse> entity = testRestTemplate.getForEntity("/cars/1", RestResponse.class);
        Assertions.assertEquals(LinkedHashMap.class, Objects.requireNonNull(entity.getBody()).result().getClass());
    }

    @Test
    public void should_return_positive_response_for_creation() {
        CarDto dto = new CarDto();
        dto.setLicensePlate("agb123");
        dto.setParkingName("Garage A");
        dto.setParkingSlot(2);
        dto.setDrivers(Set.of("AB123456"));
        dto.setBrand("Brand");
        dto.setModel("Model");
        dto.setParkingStarted(LocalDateTime.now());
        ResponseEntity<RestResponse> response = testRestTemplate.exchange(
                "/cars", HttpMethod.POST, new HttpEntity<>(dto), RestResponse.class);
        Object result = Objects.requireNonNull(response.getBody()).result();
        Assertions.assertEquals(String.class, result.getClass());
        Assertions.assertEquals("Car created", result.toString());
    }

    @Test
    public void should_return_updated_car() {
        ResponseEntity<RestResponse> entity = testRestTemplate.getForEntity("/cars/1", RestResponse.class);
        CarDto carDto = new CarDto();
        LinkedHashMap<String, Object> linkedHashMap =
                (LinkedHashMap<String, Object>) Objects.requireNonNull(entity.getBody()).result();
        carDto.setBrand(linkedHashMap.get("brand").toString());
        carDto.setLicensePlate(linkedHashMap.get("licensePlate").toString());
        carDto.setModel("Supra");
        carDto.setParkingName(linkedHashMap.get("parkingName").toString());
        carDto.setParkingSlot((Integer) linkedHashMap.get("parkingSlot"));
        ArrayList<String> drivers = (ArrayList<String>) linkedHashMap.get("drivers");
        carDto.setDrivers(new HashSet<>(drivers));
        ResponseEntity<RestResponse> response = testRestTemplate.exchange(
                "/cars/1", HttpMethod.PUT, new HttpEntity<>(carDto), RestResponse.class);
        Object result = Objects.requireNonNull(response.getBody()).result();
        Assertions.assertEquals(String.class, result.getClass());
        Assertions.assertEquals("Car with ID \"1\" updated.", result.toString());
    }

    @Test
    public void should_return_positive_response_for_deletion() {
        ResponseEntity<RestResponse> response = testRestTemplate.exchange(
                "/cars/1", HttpMethod.DELETE, new HttpEntity<>(new CarDto()), RestResponse.class);
        Object result = Objects.requireNonNull(response.getBody()).result();
        Assertions.assertEquals(String.class, result.getClass());
        Assertions.assertEquals("Car with ID \"1\" deleted.", result.toString());
    }
}
