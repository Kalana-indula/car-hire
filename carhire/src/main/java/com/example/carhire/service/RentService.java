package com.example.carhire.service;

import com.example.carhire.dto.RentDto;
import com.example.carhire.entity.Rent;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RentService {
    List<Rent> getAllRents();

    String createRent(RentDto rentDto);

    Rent getRentById(Long id);

    List<Rent> getRentsByVehicle(Long id);

    List<Rent> getRentByUser(Long id);

    String updateRent(Long id,RentDto rentDto);

    Boolean deleteRent(Long id);

    Boolean completeRent(Long id);

    String hasRentForUser(Long id);
}
