package com.megacity.cab.controller;

import com.megacity.cab.dto.LoginRequest;
import com.megacity.cab.dto.SignUpRequestDTO;
import com.megacity.cab.dto.SignUpResponseDTO;
import com.megacity.cab.service.AuthService;
import com.megacity.cab.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<SignUpResponseDTO> signUp(@RequestBody SignUpRequestDTO request) {
        SignUpResponseDTO response = userService.registerUser(request);
        return ResponseEntity.ok(response);
    }

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        String token = authService.login(loginRequest.getUsername(), loginRequest.getPassword()).toString();
        return ResponseEntity.ok(token);
    }

}
