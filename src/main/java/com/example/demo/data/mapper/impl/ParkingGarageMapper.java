package com.example.demo.data.mapper.impl;

import com.example.demo.data.dto.ParkingGarageDto;
import com.example.demo.data.entity.ParkingGarage;
import com.example.demo.data.mapper.AbstractMapper;
import org.springframework.stereotype.Component;

@Component
public class ParkingGarageMapper implements AbstractMapper<ParkingGarage, ParkingGarageDto> {
    @Override
    public ParkingGarage dtoToEntity(ParkingGarageDto dto) {
        return null;
    }

    @Override
    public ParkingGarageDto entityToDto(ParkingGarage entity) {
        return null;
    }
}