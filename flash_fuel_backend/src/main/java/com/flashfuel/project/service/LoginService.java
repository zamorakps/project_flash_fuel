package com.flashfuel.project.service;

import com.flashfuel.project.UserManager;
import com.flashfuel.project.config.TokenProvider;
import com.flashfuel.project.model.UserCredentials;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class LoginService {

    private final UserManager userManager;
    private final TokenProvider tokenProvider;

    public LoginService(UserManager userManager, TokenProvider tokenProvider) {
        this.userManager = userManager;
        this.tokenProvider = tokenProvider;
    }

    public Map<String, Object> loginAndGetUserProfile(String username, String password) {
        if (userManager.isValidCredentials(username, password)) {
            UserCredentials userCredentials = userManager.getUserByUsername(username);
            String token = tokenProvider.generateToken(userCredentials.getUsername(), userCredentials.getId());

            System.out.println(token);
            Map<String, Object> response = new HashMap<>();
            response.put("userProfile", userCredentials);
            response.put("token", token);

            return response;
        } else {
            throw new RuntimeException("Invalid username or password");
        }
    }
}