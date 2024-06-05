package com.example.carhire.service;

import com.example.carhire.dto.VehicleDto;
import com.example.carhire.entity.Vehicle;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface VehicleService {

    List<Vehicle> getAlLVehicles();
    Vehicle createVehicle(VehicleDto vehicleDto);
    Vehicle getVehicleById(Long id);
    Vehicle updateVehicle(Long id,VehicleDto vehicleDto);
    Boolean deleteVehicle(Long id);
    List<Vehicle> findVehicleByCategory(Long id);
}
