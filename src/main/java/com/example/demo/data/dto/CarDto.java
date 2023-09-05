package com.example.demo.data.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CarDto {
    private String brand;
    private String model;
    @NotBlank
    private String licensePlate;
    private LocalDate parkingStarted;
    @NotBlank
    private String parkingName;
    @NotBlank
    private Integer parkingSlot;
    @NotBlank
    private Set<String> drivers;
}