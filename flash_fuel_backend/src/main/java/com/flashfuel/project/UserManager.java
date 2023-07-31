package com.flashfuel.project;

import com.flashfuel.project.model.ClientInformation;
import com.flashfuel.project.model.UserCredentials;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Component;

@Component
public class UserManager {
    private final UserCredentialsRepository userCredentialsRepository;

    public UserManager(UserCredentialsRepository userCredentialsRepository) {
        this.userCredentialsRepository = userCredentialsRepository;
    }

    public UserCredentials getUserById(Long userId) {
        return userCredentialsRepository.findById(userId).orElse(null);
    }

    public UserCredentials getUserByUsername(String username) {
        return userCredentialsRepository.findByUsername(username);
    }

    public void registerUser(UserCredentials user) {
        userCredentialsRepository.save(user);
    }

    public boolean isUserRegistered(Long userId) {
        return userCredentialsRepository.existsById(userId);
    }

    public boolean isValidCredentials(String username, String password) {
        UserCredentials user = userCredentialsRepository.findByUsername(username);
        if (user == null) {
            return false;
        }
        return user.getPassword().equals(password);
    }
}