package com.flashfuel.project;

import com.flashfuel.project.model.ClientInformation;
import com.flashfuel.project.model.UserCredentials;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Component;

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
                clientInformation.setUserCredentials(user);
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



/*
package com.flashfuel.project;

import com.flashfuel.project.model.UserCredentials;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class UserManager {
    private Map<Long, UserCredentials> users;  // Using Long to reference userId

    public UserManager() {
        users = new HashMap<>();
    }

    public UserCredentials getUserById(Long userId) {  // Changed to userId
        return users.get(userId);
    }

    public void registerUser(UserCredentials user) {
        if (users.containsKey(user.getId())) {
            throw new RuntimeException("User already exists");  // Changed to check by userId
        }
        users.put(user.getId(), user);
    }

    public boolean isUserRegistered(Long userId) {  // Changed to userId
        return users.containsKey(userId);
    }
}
*/


/*
package com.flashfuel.project;

import com.flashfuel.project.model.UserCredentials;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class UserManager {
    private Map<String, UserCredentials> users;

    public UserManager() {
        users = new HashMap<>();
    }

    public UserCredentials getUserByUsername(String username) {
        return users.get(username);
    }

    public void registerUser(String username, String password) {
        UserCredentials user = new UserCredentials();
        user.setUsername(username);
        user.setPassword(password);
        users.put(username, user);
    }

    public boolean registerUser(UserCredentials user) {
        if (users.containsKey(user.getUsername())) {
            return false; // user already exists
        }
        users.put(user.getUsername(), user);
        return true;
    }

    public boolean isUserRegistered(String username) {
        return users.containsKey(username);
    }

    public boolean isValidCredentials(String username, String password) {
        UserCredentials user = users.get(username);
        return user != null && user.getPassword().equals(password);
    }
}
*/

/*
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
        User tempUser = new User();

        tempUser.setUsername("testUser");
        tempUser.setName("Test Name");
        tempUser.setAddress("123 Fake St");
        tempUser.setAddressLine2("Apt 1");
        tempUser.setCity("Houston");
        tempUser.setState("TX");
        tempUser.setZipCode("77003");

        registeredUsers.put("testUser", tempUser);
      
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
*/