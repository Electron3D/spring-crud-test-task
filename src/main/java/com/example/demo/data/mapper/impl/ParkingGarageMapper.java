package com.example.demo.data.mapper.impl;

import com.example.demo.data.dto.ParkingGarageDto;
import com.example.demo.data.entity.Address;
import com.example.demo.data.entity.ParkingGarage;
import com.example.demo.data.entity.ParkingSlot;
import com.example.demo.data.mapper.AbstractMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class ParkingGarageMapper extends AbstractMapper<ParkingGarage, ParkingGarageDto> {
    @Override
    @Transactional
    public ParkingGarage dtoToEntity(ParkingGarageDto garageDto) {
        ParkingGarage parkingGarage = new ParkingGarage();
        parkingGarage.setName(garageDto.getName());
        parkingGarage.setCapacity(garageDto.getCapacity());
        parkingGarage.setConstructionDate(garageDto.getConstructionDate());
        parkingGarage.setParkingRate(garageDto.getParkingRate());
        Address garageAddress = new Address(garageDto.getCountry(), garageDto.getState(),
                garageDto.getCity(), garageDto.getStreet(), garageDto.getBuilding());
        parkingGarage.setAddress(garageAddress);
        return parkingGarage;
    }

    @Override
    @Transactional
    public ParkingGarageDto entityToDto(ParkingGarage garageEntity) {
        ParkingGarageDto garageDto = new ParkingGarageDto();
        garageDto.setName(garageEntity.getName());
        garageDto.setCapacity(garageEntity.getCapacity());
        garageDto.setConstructionDate(garageEntity.getConstructionDate());
        garageDto.setParkingRate(garageEntity.getParkingRate());
        List<ParkingSlot> parkingSlots = garageEntity.getParkingSlots();
        Map<Integer, Boolean> parkingSlotsMap = parkingSlots
                .stream()
                .collect(Collectors.toMap(ParkingSlot::getSlotNumber, ParkingSlot::isOccupied));
        garageDto.setParkingSlots(parkingSlotsMap);
        Address address = garageEntity.getAddress();
        garageDto.setCountry(address.getCountry());
        garageDto.setState(address.getState());
        garageDto.setCity(address.getCity());
        garageDto.setStreet(address.getStreet());
        garageDto.setBuilding(address.getBuilding());
        return garageDto;
    }
}
