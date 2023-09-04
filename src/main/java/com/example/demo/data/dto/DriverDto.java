package com.example.demo.data.dto;

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
    private String firstName;
    private String lastName;
    private LocalDate birthday;
    private Double parkingDebt;
    private String phoneNumber;
    private String driverLicense;
    private Set<String> cars;
}
