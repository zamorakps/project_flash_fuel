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

/*
package com.flashfuel.project.service;

import com.flashfuel.project.model.ClientInformation;
import com.flashfuel.project.model.ClientInformationDTO;
import com.flashfuel.project.model.UserCredentials;
import com.flashfuel.project.model.UserCredentialsDTO;
import com.flashfuel.project.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    
    private final UserManager userManager;
    
    @Autowired
    public LoginService(UserManager userManager) {
        this.userManager = userManager;
    }
    
    public UserCredentialsDTO getUserProfile(String username) {
        UserCredentials userCredentials = userManager.getUserByUsername(username);
        UserCredentialsDTO userCredentialsDTO = null;
        if(userCredentials != null){
            userCredentialsDTO = new UserCredentialsDTO();
            userCredentialsDTO.setId(userCredentials.getId());
            userCredentialsDTO.setUsername(userCredentials.getUsername());
            // Populate ClientInformationDTO
            ClientInformation clientInformation = userCredentials.getClientInformation();
            if (clientInformation != null) {
                ClientInformationDTO clientInformationDTO = new ClientInformationDTO();
                clientInformationDTO.setId(clientInformation.getId());
                clientInformationDTO.setName(clientInformation.getName());
                clientInformationDTO.setAddress(clientInformation.getAddress());
                clientInformationDTO.setAddressLine2(clientInformation.getAddressLine2());
                clientInformationDTO.setCity(clientInformation.getCity());
                clientInformationDTO.setState(clientInformation.getState());
                clientInformationDTO.setZipCode(clientInformation.getZipCode());
                userCredentialsDTO.setClientInformation(clientInformationDTO);
            }
        }
        return userCredentialsDTO;
    }
}
*/


/*
package com.flashfuel.project.service;

import com.flashfuel.project.model.UserCredentials;
import com.flashfuel.project.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    @Autowired
    private UserManager userManager;

    public UserCredentials getProfile(String username) {
        return userManager.getUserByUsername(username);
    }

    public String generateAuthToken() {
        // Possible logic for advanced token generation
        return "RandomToken";
    }
}
*/



/*
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
*/