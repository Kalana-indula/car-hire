package com.example.carhire.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class RentDto {
    private Long userId;
    private Long vehicleId;
    private LocalDate orderDate;
}
