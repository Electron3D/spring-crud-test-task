package com.example.demo.data.dto;

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
    private String name;
    private int capacity;
    private LocalDate constructionYear;
    private Map<Integer, Boolean> parkingSlots;
    private String address;
}
