package com.flashfuel.project.service;

import com.flashfuel.project.UserManager;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

    private final UserManager userManager;

    public RegistrationService(UserManager userManager) {
        this.userManager = userManager;
    }

    private boolean isUsernameTaken(String username) {
        return userManager.isUserRegistered(username);
    }

    public boolean registerUser(String username, String password) {
        if (!isUsernameTaken(username) && isUsernameAndPasswordValid(username, password)) {
            userManager.registerUser(username, password);
            return true;
        }
        return false;
    }

    private boolean isUsernameAndPasswordValid(String username, String password) {
        return !username.isEmpty() && password.length() >= 6;
    }
}
