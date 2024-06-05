package com.example.carhire.controller;

import com.example.carhire.entity.User;
import com.example.carhire.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public ResponseEntity<?> findAllUsers(){

        try{
            return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUsers());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @PostMapping("/users")
    public ResponseEntity<?> createUser(@RequestBody User user){

        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(user));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<?> findUserById(@PathVariable Long id){

        User existingUser=userService.getUserById(id);

        if(existingUser!=null){
            try{
                return ResponseEntity.status(HttpStatus.FOUND).body(existingUser);
            }catch (Exception e){
                return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
            }
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User Not Found");
        }
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id){

        boolean isDeleted=userService.deleteUser(id);

        if(isDeleted){
            return ResponseEntity.status(HttpStatus.OK).body("User Successfully Deleted");
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User Not Found");
        }
    }

}
