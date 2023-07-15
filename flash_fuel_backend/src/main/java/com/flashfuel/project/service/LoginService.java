package com.flashfuel.project.service;

import com.flashfuel.project.UserManager;
import com.flashfuel.project.model.User;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private final UserManager userManager;

    public LoginService(UserManager userManager) {
        this.userManager = userManager;
    }

    public User getProfile(String username, String password) {
        if (userManager.isUserRegistered(username) && userManager.isValidCredentials(username, password)) {
            return userManager.getUserByUsername(username);
        }
        return null;
    }

    public String generateAuthToken() {
        // Possible logic for advanced token generation
        return "RandomToken";
    }
}
