package com.example.carhire.service;

import com.example.carhire.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    List<User> getAllUsers();
    User createUser(User user);
    User getUserById(Long id);
    Boolean deleteUser(Long id);
}
