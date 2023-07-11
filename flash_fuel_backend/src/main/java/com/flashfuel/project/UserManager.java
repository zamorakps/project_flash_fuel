package com.flashfuel.project;

import com.flashfuel.project.model.User;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class UserManager {

    // Sample data of registered users
    private final Map<String, User> registeredUsers = new HashMap<>();

    public User getUserByUsername(String username) {
        return registeredUsers.get(username);
    }

    public void registerUser(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        registeredUsers.put(username, user);
    }

    public void updateUserCredentials(String username, String name, String address, String addressLine2, String city, String state, String zipCode) {
        User user = registeredUsers.get(username);
        if (user != null) {
            user.setName(name);
            user.setAddress(address);
            user.setAddressLine2(addressLine2);
            user.setCity(city);
            user.setState(state);
            user.setZipCode(zipCode);
        }
    }

    public boolean isUserRegistered(String username) {
        return registeredUsers.containsKey(username);
    }

    public boolean isValidCredentials(String username, String password) {
        User user = registeredUsers.get(username);
        return user != null && user.getPassword().equals(password);
    }
}
