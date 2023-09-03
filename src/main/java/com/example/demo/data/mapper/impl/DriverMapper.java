package com.example.demo.data.mapper.impl;

import com.example.demo.data.dto.DriverDto;
import com.example.demo.data.entity.Driver;
import com.example.demo.data.mapper.AbstractMapper;
import org.springframework.stereotype.Component;

@Component
public class DriverMapper implements AbstractMapper<Driver, DriverDto> {
    @Override
    public Driver dtoToEntity(DriverDto dto) {
        return null;
    }

    @Override
    public DriverDto entityToDto(Driver entity) {
        return null;
    }
}
