package com.example.demo.data.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ParkingGarageDto {
    @NotNull
    @NotBlank
    private String name;
    @NotNull
    @Min(1)
    private Integer capacity;
    private LocalDate constructionDate;
    @Min(0)
    private Integer parkingRate;
    private Map<Integer, Boolean> parkingSlots;
    @NotBlank
    private String country;
    @NotBlank
    private String state;
    @NotBlank
    private String city;
    @NotBlank
    private String street;
    @NotBlank
    private String building;
}
