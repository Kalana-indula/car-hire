package com.example.carhire.repository;

import com.example.carhire.entity.Rent;
import com.example.carhire.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentRepository extends JpaRepository<Rent,Long> {

    @Query("SELECT r FROM Rent r WHERE r.vehicle= :vehicle")
    List<Rent> findRentsByVehicle(@Param("vehicle") Vehicle vehicle);
}
