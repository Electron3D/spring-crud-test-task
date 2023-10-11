package com.example.demo.repository;

import com.example.demo.data.entity.ParkingGarage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ParkingGarageRepository extends JpaRepository<ParkingGarage, Long> {
    Optional<ParkingGarage> findByName(String name);
}
