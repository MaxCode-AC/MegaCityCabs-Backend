package com.megacity.cab.service;

import com.megacity.cab.JwtUtil;
import com.megacity.cab.model.User;
import com.megacity.cab.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public Map<String, String> login(String username, String password) {
        Optional<User> userOpt = userRepository.findByUsername(username);

        if (userOpt.isEmpty() || !passwordEncoder.matches(password, userOpt.get().getPassword())) {
            throw new RuntimeException("Invalid username or password");
        }

        User user = userOpt.get();
        String token = jwtUtil.generateToken(user.getUsername());

        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        response.put("role", user.getRole().name()); // Send user role

        return response;
    }
}
