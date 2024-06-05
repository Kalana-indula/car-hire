package com.example.carhire.controller;

import com.example.carhire.dto.VehicleDto;
import com.example.carhire.entity.Vehicle;
import com.example.carhire.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @GetMapping("/vehicles")
    public ResponseEntity<?> findAllVehicles(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(vehicleService.getAlLVehicles());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @PostMapping("/vehicles")
    public ResponseEntity<?> createVehicle(@RequestBody VehicleDto vehicleDto){

        Vehicle vehicle= vehicleService.createVehicle(vehicleDto);

        if(vehicle!=null){
            try{
                return ResponseEntity.status(HttpStatus.CREATED).body(vehicle);
            }catch (Exception e){
                return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
            }
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category id "+vehicleDto.getCategoryId()+"is not found ");
        }
    }

    @GetMapping("/vehicles/{id}")
    public ResponseEntity<?> findVehicleById(@PathVariable Long id){

        Vehicle existingVehicle=vehicleService.getVehicleById(id);

        if(existingVehicle!=null){
            try{
                return ResponseEntity.status(HttpStatus.FOUND).body(existingVehicle);
            }catch (Exception e){
                return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
            }
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Vehicle Found For Entered Id");
        }
    }

    @PutMapping("/vehicles/{id}")
    public ResponseEntity<?> updateVehicle(@PathVariable Long id,@RequestBody VehicleDto vehicleDto){

        Vehicle updatedVehicle= vehicleService.updateVehicle(id,vehicleDto);

        if(updatedVehicle!=null){
            try{
                return ResponseEntity.status(HttpStatus.OK).body(updatedVehicle);
            }catch (Exception e){
                return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
            }
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Vehicle Found For Entered Id");
        }
    }

    @DeleteMapping("/vehicles/{id}")
    public ResponseEntity<?> deleteVehicle(@PathVariable Long id){
        Boolean hasDeleted=vehicleService.deleteVehicle(id);

        if(hasDeleted){
            try{
                return ResponseEntity.status(HttpStatus.OK).body("Successfully Deleted");
            }catch (Exception e){
                return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
            }
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Vehicle Found For Entered Id");
        }
    }

    @GetMapping("/categories/{id}/vehicles")
    public ResponseEntity<?> findVehicleByCategory(@PathVariable Long id){
        List<Vehicle> vehicle=vehicleService.findVehicleByCategory(id);

        try{
            return ResponseEntity.status(HttpStatus.FOUND).body(vehicle);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

}
