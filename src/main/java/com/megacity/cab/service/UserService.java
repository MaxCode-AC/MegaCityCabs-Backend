package com.megacity.cab.service;

import com.megacity.cab.dto.SignUpRequestDTO;
import com.megacity.cab.dto.SignUpResponseDTO;
import com.megacity.cab.model.User;
import com.megacity.cab.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); // Encrypt passwords

    public SignUpResponseDTO registerUser(SignUpRequestDTO request) {
        // Check if the username already exists
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            return new SignUpResponseDTO("Username already taken!");
        }

        // Create new user
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword())); // Encrypt password
        user.setRole(request.getRole());

        userRepository.save(user);
        return new SignUpResponseDTO("User registered successfully!");
    }
}
