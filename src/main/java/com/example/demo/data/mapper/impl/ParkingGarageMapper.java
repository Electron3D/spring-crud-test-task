package com.example.demo.data.mapper.impl;

import com.example.demo.data.dto.ParkingGarageDto;
import com.example.demo.data.entity.Address;
import com.example.demo.data.entity.ParkingGarage;
import com.example.demo.data.entity.ParkingSlot;
import com.example.demo.data.mapper.AbstractMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class ParkingGarageMapper extends AbstractMapper<ParkingGarage, ParkingGarageDto> {
    @Override
    @Transactional
    public ParkingGarage dtoToEntity(ParkingGarageDto garageDto, ParkingGarage existingGarage) {
        existingGarage.setName(garageDto.getName());
        int capacity = garageDto.getCapacity();
        existingGarage.setCapacity(capacity);
        existingGarage.setConstructionYear((garageDto.getConstructionYear()));
        existingGarage.setParkingRate(Objects.requireNonNullElse(garageDto.getParkingRate(), 0));
        List<ParkingSlot> parkingSlots = existingGarage.getParkingSlots();
        if (parkingSlots == null) {
            parkingSlots = new ArrayList<>();
            for (int i = 0; i < capacity; i++) {
                ParkingSlot parkingSlot = new ParkingSlot();
                parkingSlot.setSlotNumber(i);
                parkingSlots.add(parkingSlot);
            }
        }
        if (existingGarage.getAddress() == null) {
            Address address = new Address(
                    garageDto.getCountry(),
                    garageDto.getState(),
                    garageDto.getCity(),
                    garageDto.getStreet(),
                    garageDto.getBuilding()
            );
            existingGarage.setAddress(address);
        }
        return existingGarage;
    }

    @Override
    public ParkingGarageDto entityToDto(ParkingGarage garageEntity) {
        ParkingGarageDto garageDto = new ParkingGarageDto();
        garageDto.setName(garageEntity.getName());
        garageDto.setCapacity(garageEntity.getCapacity());
        garageDto.setConstructionYear(garageEntity.getConstructionYear());
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
