package com.example.demo.data.dto;

import jakarta.validation.constraints.NotBlank;
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
    @NotBlank
    private String name;
    @NotBlank
    private Integer capacity;
    private LocalDate constructionYear;
    private Integer parkingRate;
    private Map<Integer, Boolean> parkingSlots;
    @NotBlank
    private String address;
}
