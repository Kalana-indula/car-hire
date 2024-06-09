package com.example.carhire.service;

import com.example.carhire.dto.RentDto;
import com.example.carhire.entity.Rent;
import com.example.carhire.entity.User;
import com.example.carhire.entity.Vehicle;
import com.example.carhire.repository.RentRepository;
import com.example.carhire.repository.UserRepository;
import com.example.carhire.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class RentServiceImpl implements RentService{

//    Calling repositories

    @Autowired
    private RentRepository rentRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private UserRepository userRepository;


//  Calling Methods

    @Override
    public List<Rent> getAllRents() {
        return rentRepository.findAll();
    }

    @Override
    public String createRent(RentDto rentDto){

        Vehicle existingVehicle=vehicleRepository.findById(rentDto.getVehicleId()).orElse(null);
        User existingUser=userRepository.findById(rentDto.getUserId()).orElse(null);

        if((existingVehicle!=null)&&(existingUser!=null)){

            Rent rentByUser=rentRepository.findIncompletedRentByUser(rentDto.getUserId());

            Rent rentByVehicle=rentRepository.findIncompletedRentByVehicle(rentDto.getVehicleId());

            if(rentByUser==null){

                if(rentByVehicle==null){
                    Rent rent=new Rent();

                    LocalDate today=LocalDate.now();
                    long daysBetween= ChronoUnit.DAYS.between(rentDto.getOrderDate(),today);
                    int duration=Math.toIntExact(daysBetween);
                    double totalPayable=0.0;
                    double additionalCharge=0.0;

                    if(duration>30){
                        additionalCharge=(existingVehicle.getRentPerDay()+1000)*(duration-30);
                        totalPayable=existingVehicle.getRentPerDay()*30+additionalCharge;
                    }else{
                        totalPayable=existingVehicle.getRentPerDay()*duration;
                    }

                    rent.setOrderDate(rentDto.getOrderDate());
                    rent.setDuration(duration);
                    rent.setOverDue(additionalCharge);
                    rent.setTotalPayable(totalPayable);
                    rent.setIsCompleted(false);
                    rent.setVehicle(existingVehicle);
                    rent.setUser(existingUser);

                    rentRepository.save(rent);

                    return "Rent Saved Successfully";
                }else{
                    return "Rent is existed to the entered vehicle";
                }

            }else{
                return "A rent is existed to the entered user";
            }

        }else{
            return "User Or Vehicle is not existed";
        }
    }

    @Override
    public Rent getRentById(Long id) {
        return rentRepository.findById(id).orElse(null);
    }

    @Override
    public List<Rent> getRentsByVehicle(Long id) {

        Vehicle vehicle=vehicleRepository.findById(id).orElse(null);

        if(vehicle!=null){
           List<Rent> rentList=rentRepository.findRentsByVehicle(vehicle);

           return rentList;
        }else{
            return null;
        }

    }

    @Override
    public List<Rent> getRentByUser(Long id) {

        User user=userRepository.findById(id).orElse(null);

        if(user!=null){
            List<Rent> rentList=rentRepository.findRentByUser(user);

            return rentList;
        }else{
            return null;
        }

    }

    @Override
    public String updateRent(Long id, RentDto rentDto) {

        Rent existingRent=rentRepository.findById(id).orElse(null);

        if(existingRent!=null){

            User newUser=userRepository.findById(rentDto.getUserId()).orElse(null);
            Vehicle newVehicle=vehicleRepository.findById(rentDto.getVehicleId()).orElse(null);

            if((newVehicle!=null)&&(newUser!=null)){

                existingRent.setUser(newUser);
                existingRent.setVehicle(newVehicle);
                existingRent.setOrderDate(rentDto.getOrderDate());

                rentRepository.save(existingRent);

                return "Rent was updated successfully";

            }else if(newUser==null){
                return "No user found for entered Id";
            }else {
                return "No vehicle found for entered Id";
            }
        }else{
            return "No rent found in the record for the entered Id";
        }

    }

    @Override
    public Boolean deleteRent(Long id) {

        Boolean hasRent=rentRepository.existsById(id);

        if(hasRent){
            rentRepository.deleteById(id);
            return true;
        }else{
            return null;
        }

    }

    @Override
    public Boolean completeRent(Long id) {
        Rent existingRent=rentRepository.findById(id).orElse(null);

        if(existingRent!=null){
            existingRent.setIsCompleted(true);
            rentRepository.save(existingRent);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public String hasRentForUser(Long id) {

        return null;
    }


}
