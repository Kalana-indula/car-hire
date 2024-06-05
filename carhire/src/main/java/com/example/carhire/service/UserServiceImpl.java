package com.example.carhire.service;

import com.example.carhire.entity.User;
import com.example.carhire.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public Boolean deleteUser(Long id) {
        Boolean isExist=userRepository.existsById(id);

        if(isExist){
            userRepository.deleteById(id);
            return true;
        }else{
            return false;
        }
    }
}
