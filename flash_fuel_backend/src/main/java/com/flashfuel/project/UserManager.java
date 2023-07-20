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

/*
@Component
public class UserManager {

    private AtomicLong userIdGenerator = new AtomicLong(0);
    private Map<Long, UserCredentials> users;  // Using Long to reference userId
    private Map<String, UserCredentials> usersByUsername;

    public long generateNewUserId() {
        return userIdGenerator.incrementAndGet();
    }

    public UserManager() {
        users = new ConcurrentHashMap<>();
        usersByUsername = new ConcurrentHashMap<>();
    }

    public UserCredentials getUserById(Long userId) {
        return users.get(userId);
    }

    public UserCredentials getUserByUsername(String username) {
        return usersByUsername.get(username);
    }

    public void registerUser(UserCredentials user) {
        synchronized (this) {
            if (users.containsKey(user.getId()) || usersByUsername.containsKey(user.getUsername())) {
                throw new RuntimeException("User already exists");
            }
            
            // Ensure ClientInformation is set for UserCredentials and vice versa
            ClientInformation clientInformation = user.getClientInformation();
            if (clientInformation != null) {
                clientInformation.setUserCredentials(user, true);
                user.setClientInformation(clientInformation);
            }

            users.put(user.getId(), user);
            usersByUsername.put(user.getUsername(), user);
        }
    }

    public boolean isUserRegistered(Long userId) {
        return users.containsKey(userId);
    }

    public boolean isValidCredentials(String username, String password) {
        UserCredentials user = usersByUsername.get(username);
        if (user == null) {
            return false;
        }
        return user.getPassword().equals(password);
    }
}
*/