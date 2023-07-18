package com.flashfuel.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flashfuel.project.UserManager;
import com.flashfuel.project.model.ClientInformation;
import com.flashfuel.project.model.UserCredentials;

@Service
public class RegistrationService {

    private final UserManager userManager;

    @Autowired
    public RegistrationService(UserManager userManager) {
        this.userManager = userManager;
    }

    public boolean isUsernameTaken(String username) {
        return userManager.getUserByUsername(username) != null;
    }

    public void registerUser(String username, String password, ClientInformation clientInformation) {
        if (!isUsernameAndPasswordValid(username, password)) {
            throw new RuntimeException("Invalid username or password");
        }
        if (isUsernameTaken(username)) {
            throw new RuntimeException("Username already taken");
        }
        UserCredentials newUser = new UserCredentials(username, password, clientInformation);
        long newUserId = userManager.generateNewUserId();
        newUser.setId(newUserId);
        userManager.registerUser(newUser);
    }

    private boolean isUsernameAndPasswordValid(String username, String password) {
        return !username.isEmpty() && password.length() >= 6;
    }
}



/*
package com.flashfuel.project.service;

import com.flashfuel.project.model.ClientInformation;
import com.flashfuel.project.model.UserCredentials;
import com.flashfuel.project.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {
    @Autowired
    private UserManager userManager;

    public boolean isUsernameTaken(String username) {
        return userManager.getUserByUsername(username) != null;
    }

    public void registerUser(String username, String password, ClientInformation clientInformation) {
        if (!isUsernameAndPasswordValid(username, password)) {
            throw new RuntimeException("Invalid username or password");
        }
        if (isUsernameTaken(username)) {
            throw new RuntimeException("Username already taken");
        }
        UserCredentials newUser = new UserCredentials(username, password, clientInformation);
        long newUserId = userManager.generateNewUserId();
        newUser.setId(newUserId);
        userManager.registerUser(newUser);
    }

    private boolean isUsernameAndPasswordValid(String username, String password) {
        return !username.isEmpty() && password.length() >= 6;
    }
}
*/



/*
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
*/