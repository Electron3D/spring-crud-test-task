package com.example.demo.service.impl;

import com.example.demo.data.dto.ParkingGarageDto;
import com.example.demo.data.entity.Car;
import com.example.demo.data.entity.ParkingGarage;
import com.example.demo.data.entity.ParkingSlot;
import com.example.demo.data.mapper.impl.ParkingGarageMapper;
import com.example.demo.exception.NotFoundException;
import com.example.demo.repository.ParkingGarageRepository;
import com.example.demo.service.ParkingGarageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ParkingGarageServiceImpl implements ParkingGarageService {
    private final ParkingGarageRepository parkingGarageRepository;

    @Autowired
    public ParkingGarageServiceImpl(ParkingGarageRepository parkingGarageRepository, ParkingGarageMapper parkingGarageMapper) {
        this.parkingGarageRepository = parkingGarageRepository;
        this.parkingGarageMapper = parkingGarageMapper;
    }
    @Override
    public void create(ParkingGarageDto parkingGarageDto) {
        parkingGarageRepository.save(parkingGarageMapper.dtoToEntity(parkingGarageDto, new ParkingGarage()));
    }

    @Override
    public ParkingGarageDto findById(Long id) {
        ParkingGarage garage = parkingGarageRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Parking garage with ID \"" + id + "\" not found."));
        return parkingGarageMapper.entityToDto(garage);
    }

    @Override
    public List<ParkingGarageDto> findAll() {
        return parkingGarageRepository
                .findAll()
                .stream()
                .map(parkingGarageMapper::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ParkingGarageDto updateById(Long id, ParkingGarageDto parkingGarageDto) {
        Optional<ParkingGarage> parkingGarageOptional = parkingGarageRepository.findById(id);
        if (parkingGarageOptional.isPresent()) {
            ParkingGarage existedCar = parkingGarageOptional.get();
            ParkingGarage updatedCar = parkingGarageMapper.dtoToEntity(parkingGarageDto, existedCar);
            return parkingGarageMapper.entityToDto(updatedCar);
        } else throw new NotFoundException("Parking garage with ID \"" + id + "\" not found.");
    }

    @Override
    public void deleteById(Long id) {
        Optional<ParkingGarage> parkingGarageOptional = parkingGarageRepository.findById(id);
        if (parkingGarageOptional.isPresent()) {
            parkingGarageRepository.deleteById(id);
        } else {
            throw new NotFoundException("Parking garage with ID \"" + id + "\" not found");
        }
    }
}
