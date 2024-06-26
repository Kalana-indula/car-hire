package com.example.carhire.controller;

import com.example.carhire.dto.RentDto;
import com.example.carhire.entity.Rent;
import com.example.carhire.service.RentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class RentController {

    @Autowired
    private RentService rentService;

    @GetMapping("/rents")
    public ResponseEntity<?> findAllRents(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(rentService.getAllRents());
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @PostMapping("/rents")
    public ResponseEntity<?> createRent(@RequestBody RentDto rentDto){

        String newRent=rentService.createRent(rentDto);

        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(newRent);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }

    }

    @GetMapping("/rents/{id}")
    public ResponseEntity<?> findRentById(@PathVariable Long id){
        Rent existingRent=rentService.getRentById(id);

        if(existingRent!=null){
            return ResponseEntity.status(HttpStatus.FOUND).body(existingRent);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No rent found for entered Id");
        }
    }


    @PutMapping("close/rents/{id}")
    public ResponseEntity<?> closeCurrentRent(@PathVariable Long id){

        try{
            Boolean isCompleted=rentService.completeRent(id);

            if(isCompleted){
                return ResponseEntity.status(HttpStatus.OK).body("Rent was completed successfully");
            }else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No rent found for entered Id");
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @PutMapping("update/rents/{id}")
    public ResponseEntity<?> updateRent(@PathVariable Long id,@RequestBody RentDto rentDto){

        try{
            return ResponseEntity.status(HttpStatus.OK).body(rentService.updateRent(id,rentDto));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @GetMapping("/vehicles/{id}/rents")
    public ResponseEntity<?> getRentsByVehicle(@PathVariable Long id){
        try{
            List<Rent> rentList=rentService.getRentsByVehicle(id);

            if((rentList!=null)&&(!rentList.isEmpty())){

                return ResponseEntity.status(HttpStatus.OK).body(rentList);

            }else if (rentList.isEmpty()){

                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No rent record found for the vehicle");

            } else{

                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No vehicle found for entered Id");

            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @GetMapping("/users/{id}/rents")
    public ResponseEntity<?> findRentByUser(@PathVariable Long id){
        try{
            List<Rent> rentList=rentService.getRentByUser(id);

            if((rentList!=null)&&(!rentList.isEmpty())){
                return ResponseEntity.status(HttpStatus.OK).body(rentList);
            }else if(rentList.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No rent record found for the user");
            }else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No user found for the entered id");
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }
}
