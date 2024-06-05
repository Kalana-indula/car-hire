package com.example.carhire.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class VehicleDto {
    private String number;
    private String brand;
    private String model;
    private String year;
    private Double rentPerDay;
    private Long categoryId;
}
