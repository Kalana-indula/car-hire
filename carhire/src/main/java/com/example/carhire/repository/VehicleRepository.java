package com.example.carhire.repository;

import com.example.carhire.entity.Category;
import com.example.carhire.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle,Long> {
    @Query("SELECT v FROM Vehicle v WHERE v.category= :category")
    List<Vehicle> findVehicleByCategory(@Param("category")Category category);
}
