package com.example.carhire.service;

import com.example.carhire.dto.VehicleDto;
import com.example.carhire.entity.Category;
import com.example.carhire.entity.Vehicle;
import com.example.carhire.repository.CategoryRepository;
import com.example.carhire.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleServiceImpl implements VehicleService{

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Vehicle> getAlLVehicles() {
        return vehicleRepository.findAll();
    }

    @Override
    public Vehicle createVehicle(VehicleDto vehicleDto) {
        Category category=categoryRepository.findById(vehicleDto.getCategoryId()).orElse(null);

        if(category!=null){
            Vehicle vehicle=new Vehicle();

            vehicle.setNumber(vehicleDto.getNumber());
            vehicle.setBrand(vehicleDto.getBrand());
            vehicle.setModel(vehicleDto.getModel());
            vehicle.setYear(vehicleDto.getYear());
            vehicle.setRentPerDay(vehicleDto.getRentPerDay());
            vehicle.setCategory(category);

            return vehicleRepository.save(vehicle);
        }else{
            return null;
        }

    }

    @Override
    public Vehicle getVehicleById(Long id) {
        return vehicleRepository.findById(id).orElse(null);
    }

    @Override
    public Vehicle updateVehicle(Long id, VehicleDto vehicleDto) {

        Vehicle existingVehicle=vehicleRepository.findById(id).orElse(null);

        if(existingVehicle!=null){
            Category existingCategory=categoryRepository.findById(vehicleDto.getCategoryId()).orElse(null);

            if(existingCategory!=null){
                existingVehicle.setNumber(vehicleDto.getNumber());
                existingVehicle.setBrand(vehicleDto.getBrand());
                existingVehicle.setModel(vehicleDto.getModel());
                existingVehicle.setYear(vehicleDto.getYear());
                existingVehicle.setRentPerDay(vehicleDto.getRentPerDay());
                existingVehicle.setCategory(existingCategory);

                return vehicleRepository.save(existingVehicle);
            }
        }else{
            return null;
        }
        return null;
    }

    @Override
    public Boolean deleteVehicle(Long id) {
        Boolean isVehicleExist=vehicleRepository.existsById(id);

        if(isVehicleExist){
            vehicleRepository.deleteById(id);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public List<Vehicle> findVehicleByCategory(Long id) {
        Category category=categoryRepository.findById(id).orElse(null);

        if(category!=null){
            return vehicleRepository.findVehicleByCategory(category);
        }else{
            return null;
        }
    }

}
