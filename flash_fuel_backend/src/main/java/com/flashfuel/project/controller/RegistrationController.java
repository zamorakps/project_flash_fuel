package com.flashfuel.project.controller;

import com.flashfuel.project.service.RegistrationService;
import com.flashfuel.project.model.UserCredentialsDTO;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class RegistrationController {

    private final RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserCredentialsDTO userCredentialsDTO) {
        try {
            registrationService.registerUser(
                userCredentialsDTO.getUsername(), 
                userCredentialsDTO.getPassword(), 
                userCredentialsDTO.getClientInformation()
            );
            return ResponseEntity.ok("User registration successful.");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}