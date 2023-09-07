package com.example.demo.data.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
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
    private LocalDateTime parkingStarted;
    @NotBlank
    private String parkingName;
    @NotNull
    private Integer parkingSlot;
    @NotNull
    private Set<String> drivers;
}