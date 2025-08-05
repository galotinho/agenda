package com.example.agenda.service;

import com.example.agenda.dto.LoginRequest;
import com.example.agenda.dto.SignupRequest;
import com.example.agenda.model.User;
import com.example.agenda.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    public User registerUser(SignupRequest signupRequest) {
        // Check if user already exists
        if (userRepository.existsByEmail(signupRequest.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        // Create new user
        User user = new User();
        user.setName(signupRequest.getNome());
        user.setEmail(signupRequest.getEmail());
        user.setPassword(signupRequest.getSenha()); // In production, hash the password

        return userRepository.save(user);
    }
    
    public boolean authenticateUser(LoginRequest loginRequest) {
        Optional<User> userOptional = userRepository.findByEmail(loginRequest.getEmail());

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            // In production, compare hashed passwords
            return user.getPassword().equals(loginRequest.getSenha());
        }

        return false;
    }
    
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
