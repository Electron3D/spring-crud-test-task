package com.example.demo.service.impl;

import com.example.demo.data.dto.ParkingGarageDto;
import com.example.demo.repository.ParkingGarageRepository;
import com.example.demo.service.ParkingGarageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParkingGarageServiceImpl implements ParkingGarageService {
    private final ParkingGarageRepository parkingGarageRepository;

    @Autowired
    public ParkingGarageServiceImpl(ParkingGarageRepository parkingGarageRepository) {
        this.parkingGarageRepository = parkingGarageRepository;
    }
    @Override
    public void create(ParkingGarageDto parkingGarageDto) {

    }

    @Override
    public ParkingGarageDto findById(Long id) {
        return null;
    }

    @Override
    public List<ParkingGarageDto> findAll() {
        return null;
    }

    @Override
    public ParkingGarageDto updateById(Long id, ParkingGarageDto parkingGarageDto) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }
}
