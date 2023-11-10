package com.flashmart.order.service;

import com.flashmart.order.model.User;
import com.flashmart.order.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Userservice {
    private final UserRepository userRepository;

    @Autowired
    public Userservice(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User saveUser(User user) {
        // Implement user registration logic here
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    // Other service methods

    // Implement your business logic using userRepository
}
