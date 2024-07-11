package com.demo.Demmo.service;

import com.demo.Demmo.respository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.demo.Demmo.entity.User;

@Service
public class SignupService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public User registerUser(String username, String password) {
        if (userRepository.findByUsername(username) != null) {
            throw new RuntimeException("User already exists");
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));

        return userRepository.save(user);
    }
}
