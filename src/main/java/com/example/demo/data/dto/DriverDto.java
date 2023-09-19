package com.example.demo.data.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class DriverDto {
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotNull
    @NotBlank
    private String driverLicense;
    private LocalDate birthday;
    private Double parkingDebt;
    @NotNull
    @NotBlank
    private String phoneNumber;
    private Set<String> carsLicensePlates;
}
