package com.example.demo.service;

import com.example.demo.data.entity.Car;
import com.example.demo.service.impl.CarServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@DataJpaTest
@Sql(scripts = {"classpath:database/create_tables.sql",
        "classpath:database/populate_tables.sql"},
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class CarServiceImplIT {
    @Autowired
    private CarServiceImpl carService;

    @Test
    @Transactional
    public void should_return_car() {
        Car car = carService.findById(1L);
    }
}
