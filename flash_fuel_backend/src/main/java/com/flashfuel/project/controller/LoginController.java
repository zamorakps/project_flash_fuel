package com.flashfuel.project.controller;

import com.flashfuel.project.model.UserCredentialsDTO;
import com.flashfuel.project.service.LoginService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserCredentialsDTO userCredentials) {
        try {
            return ResponseEntity.ok(
                    loginService.loginAndGetUserProfile(userCredentials.getUsername(), userCredentials.getPassword())
            );
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
