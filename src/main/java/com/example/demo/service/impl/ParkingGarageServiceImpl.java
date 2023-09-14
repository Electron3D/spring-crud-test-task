package com.example.demo.service.impl;

import com.example.demo.data.entity.ParkingGarage;
import com.example.demo.data.entity.ParkingSlot;
import com.example.demo.exception.NotFoundException;
import com.example.demo.exception.WrongInputException;
import com.example.demo.repository.ParkingGarageRepository;
import com.example.demo.service.ParkingGarageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ParkingGarageServiceImpl implements ParkingGarageService {
    private final ParkingGarageRepository parkingGarageRepository;

    @Autowired
    public ParkingGarageServiceImpl(ParkingGarageRepository parkingGarageRepository) {
        this.parkingGarageRepository = parkingGarageRepository;
    }

    @Override
    @Transactional
    public void create(ParkingGarage parkingGarage) {
        String name = parkingGarage.getName();
        parkingGarageRepository.findByName(name)
                .orElseThrow(() -> new WrongInputException(
                        "Car with license plate \""
                                + name + "\" already exist."));
        Integer parkingRate = parkingGarage.getParkingRate();
        if (parkingRate == null || parkingRate < 0) {
            parkingGarage.setParkingRate(0);
        }
        List<ParkingSlot> parkingSlots = new ArrayList<>();
        for (int i = 1; i <= parkingGarage.getCapacity(); i++) {
            ParkingSlot parkingSlot = new ParkingSlot();
            parkingSlot.setSlotNumber(i);
            parkingSlots.add(parkingSlot);
        }
        parkingGarage.setParkingSlots(parkingSlots);
        parkingGarageRepository.save(parkingGarage);
    }

    @Override
    @Transactional(readOnly = true)
    public ParkingGarage findById(Long id) {
        return parkingGarageRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Parking garage with ID \"" + id + "\" not found."));
    }

    @Override
    @Transactional(readOnly = true)
    public List<ParkingGarage> findAll() {
        return new ArrayList<>(parkingGarageRepository.findAll());
    }

    @Override
    @Transactional
    public void updateById(Long id, ParkingGarage parkingGarage) {
        ParkingGarage existingParkingGarage = parkingGarageRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Parking garage with ID \"" + id + "\" not found."));
        existingParkingGarage.setName(parkingGarage.getName());
        existingParkingGarage.setParkingRate(parkingGarage.getParkingRate());
        Integer newCapacity = parkingGarage.getCapacity();
        existingParkingGarage.setParkingSlots(getNewParkingSlots(existingParkingGarage, newCapacity));
        existingParkingGarage.setCapacity(newCapacity);
        parkingGarageRepository.save(existingParkingGarage);
    }

    @Transactional
    public List<ParkingSlot> getNewParkingSlots(ParkingGarage existingParkingGarage, Integer newCapacity) {
        List<ParkingSlot> existingParkingSlots = existingParkingGarage.getParkingSlots();
        if (existingParkingGarage.getCapacity() > newCapacity) {
            throw new WrongInputException("Provided capacity can't be less than current capacity");
        }
        List<ParkingSlot> newParkingSlots = new ArrayList<>(existingParkingSlots);
        int counter = existingParkingSlots.size();
        while (counter < newCapacity) {
            ParkingSlot parkingSlot = new ParkingSlot();
            parkingSlot.setOccupied(false);
            parkingSlot.setSlotNumber(++counter);
            newParkingSlots.add(parkingSlot);
        }
        return newParkingSlots;
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Optional<ParkingGarage> parkingGarageOptional = parkingGarageRepository.findById(id);
        if (parkingGarageOptional.isPresent()) {
            ParkingGarage parkingGarage = parkingGarageOptional.get();
            List<ParkingSlot> parkingSlots = parkingGarage.getParkingSlots();
            for (ParkingSlot slot : parkingSlots) {
                if (slot.isOccupied()) {
                    throw new WrongInputException("You can't delete garage with cars inside.");
                }
            }
            parkingGarageRepository.deleteById(id);
        } else {
            throw new NotFoundException("Parking garage with ID \"" + id + "\" not found");
        }
    }
}
