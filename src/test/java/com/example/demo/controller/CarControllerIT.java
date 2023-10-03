package com.example.demo.controller;

import com.example.demo.data.RestResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;

import java.util.LinkedHashMap;
import java.util.Objects;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql({"/createTablesInDb.sql", "/insertCarInDb.sql"})
public class CarControllerIT {
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void should_return_car() {
        ResponseEntity<RestResponse> entity = testRestTemplate.getForEntity("/cars/1", RestResponse.class);
        Assertions.assertEquals(Objects.requireNonNull(entity.getBody()).result().getClass(), LinkedHashMap.class);
    }

    /*@Test
    public void should_return_positive_response_for_creation() {
        testRestTemplate.exchange("/cars/1", HttpMethod.POST, RestResponse.class)
    }*/
}
