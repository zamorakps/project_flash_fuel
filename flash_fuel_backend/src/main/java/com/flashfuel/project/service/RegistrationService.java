package com.flashfuel.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flashfuel.project.UserManager;
import com.flashfuel.project.model.ClientInformation;
import com.flashfuel.project.model.ClientInformationDTO;
import com.flashfuel.project.model.UserCredentials;

@Service
public class RegistrationService {

    private final UserManager userManager;

    public RegistrationService(UserManager userManager) {
        this.userManager = userManager;
    }

    /*added mapping below */
    public ClientInformation toClientInformation(ClientInformationDTO dto) {
        ClientInformation clientInformation = new ClientInformation();
        clientInformation.setId(dto.getId());
        clientInformation.setName(dto.getName());
        clientInformation.setAddress(dto.getAddress());
        clientInformation.setAddressLine2(dto.getAddressLine2());
        clientInformation.setCity(dto.getCity());
        clientInformation.setState(dto.getState());
        clientInformation.setZipCode(dto.getZipCode());
        return clientInformation;
    }

    public boolean isUsernameTaken(String username) {
        return userManager.getUserByUsername(username) != null;
    }

    public void registerUser(String username, String password, ClientInformationDTO clientInformationDTO) {
        if (!isUsernameAndPasswordValid(username, password)) {
            throw new RuntimeException("Invalid username or password");
        }
        if (isUsernameTaken(username)) {
            throw new RuntimeException("Username already taken");
        }
        ClientInformation clientInformation = toClientInformation(clientInformationDTO);
        UserCredentials newUser = new UserCredentials(username, password, clientInformation);
        // long newUserId = userManager.generateNewUserId();
        long newUserId = 1;
        newUser.setId(newUserId);
        userManager.registerUser(newUser);
    }
    
    public boolean isUsernameAndPasswordValid(String username, String password) {
        return !username.isEmpty() && password.length() >= 6;
    }
}