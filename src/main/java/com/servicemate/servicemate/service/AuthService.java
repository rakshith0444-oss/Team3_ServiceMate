package com.servicemate.servicemate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.servicemate.servicemate.model.User;
import com.servicemate.servicemate.repository.UserRepository;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    // Signup Logic
    public User registerUser(User user) {
        return userRepository.save(user);
    }

    // Login Logic
    public User loginUser(String email, String password) {

        User user = userRepository.findByEmail(email);

        if(user != null && user.getPassword().equals(password)) {
            return user;
        }

        return null;
    }
}