package com.flashfuel.project.service;

import com.flashfuel.project.model.ClientInformation;
import com.flashfuel.project.model.ClientInformationDTO;
import com.flashfuel.project.ClientInformationManager;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClientInformationService {

    private final ClientInformationManager clientInformationManager;

    private ClientInformationDTO mapToDTO(ClientInformation clientInformation) {
        ClientInformationDTO dto = new ClientInformationDTO();
        dto.setName(clientInformation.getName());
        dto.setAddress(clientInformation.getAddress());
        dto.setAddressLine2(clientInformation.getAddressLine2());
        dto.setCity(clientInformation.getCity());
        dto.setState(clientInformation.getState());
        dto.setZipCode(clientInformation.getZipCode());
    
        return dto;
    }

    public ClientInformationService(ClientInformationManager clientInformationManager) {
        this.clientInformationManager = clientInformationManager;
    }

    public ClientInformation getClientInformationByUserId(Long userId) {
        return clientInformationManager.getClientInformationByUserId(userId);
    }

    /*
    public void updateClientInformation(Long userId, ClientInformation clientInformation) {
        ClientInformationDTO dto = mapToDTO(clientInformation);
        String errors = getUpdateProfileErrors(dto);

        if (errors != null) {
            throw new RuntimeException(errors);
        }
        clientInformationManager.addClientInformation(userId, clientInformation);
    }
    */

    public void updateClientInformation(Long userId, ClientInformation clientInformation) {
        ClientInformationDTO dto = mapToDTO(clientInformation);
        String errors = getUpdateProfileErrors(dto);
    
        if (errors != null) {
            throw new RuntimeException(errors);
        }
        clientInformationManager.updateClientInformation(userId, clientInformation);
    }    

    private String getUpdateProfileErrors(ClientInformationDTO request) {
        List<String> errorList = new ArrayList<>();

        if (request.getName() == null || request.getName().isBlank())
            errorList.add("Name is required.");

        if (request.getAddress() == null || request.getAddress().isBlank())
            errorList.add("Address is required.");

        if (request.getCity() == null || request.getCity().isBlank())
            errorList.add("City is required.");

        if (request.getState() == null || request.getState().isBlank())
            errorList.add("State is required.");

        if (request.getZipCode() == null || request.getZipCode().isBlank())
            errorList.add("Zip Code is required.");
        else {
            String zipCodePattern = "^\\d{5}(?:[-\\s]\\d{4})?$";
            if (!request.getZipCode().matches(zipCodePattern)) {
                errorList.add("Invalid Zip Code format.");
            }
        }

        if (errorList.size() == 0)
            return null;

        var errorMessage = "<ul>";
        for (String msg : errorList)
            errorMessage += String.format("<li>%s</li>", msg);
        errorMessage += "</ul>";

        return errorMessage;
    }
}

/*
package com.flashfuel.project.service;

import com.flashfuel.project.model.ClientInformation;
import com.flashfuel.project.model.UserCredentials;
import com.flashfuel.project.model.ClientInformationDTO;
import com.flashfuel.project.ClientInformationManager;
import com.flashfuel.project.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClientInformationService {
    @Autowired
    private UserManager userManager;
    @Autowired
    private ClientInformationManager clientInfoManager;

    public ClientInformation getClientInformation(String username) {
        UserCredentials user = userManager.getUserByUsername(username);
        return clientInfoManager.getClientInformationByUserId(user.getId());
    }

    public void updateClientInformation(ClientInformationDTO request) {
        String errors = getUpdateProfileErrors(request);
        if (errors != null) {
            throw new RuntimeException(errors);
        }

        UserCredentials user = userManager.getUserByUsername(request.getName());
        ClientInformation clientInfo = user.getClientInformation();

        clientInfo.setName(request.getName());
        clientInfo.setAddress(request.getAddress());
        clientInfo.setAddressLine2(request.getAddressLine2());
        clientInfo.setCity(request.getCity());
        clientInfo.setState(request.getState());
        clientInfo.setZipCode(request.getZipCode());

        clientInfoManager.addClientInformation(user.getId(), clientInfo);
    }

    private String getUpdateProfileErrors(ClientInformationDTO request) {
        List<String> errorList = new ArrayList<>();

        // check if user exists
        if (userManager.getUserByUsername(request.getName()) == null) {
            errorList.add("User does not exist.");
        }

        if (request.getName() == null || request.getName().isBlank())
            errorList.add("Name is required.");

        if (request.getAddress() == null || request.getAddress().isBlank())
            errorList.add("Address is required.");

        if (request.getCity() == null || request.getCity().isBlank())
            errorList.add("City is required.");

        if (request.getState() == null || request.getState().isBlank())
            errorList.add("State is required.");

        if (request.getZipCode() == null || request.getZipCode().isBlank())
            errorList.add("Zip Code is required.");
        else {
            String zipCodePattern = "^\\d{5}(?:[-\\s]\\d{4})?$";
            if (!request.getZipCode().matches(zipCodePattern)) {
                errorList.add("Invalid Zip Code format.");
            }
        }

        if (errorList.size() == 0)
            return null;

        var errorMessage = "<ul>";
        for (String msg : errorList)
            errorMessage += String.format("<li>%s</li>", msg);
        errorMessage += "</ul>";

        return errorMessage;
    }
}
*/



/*
package com.flashfuel.project.service;

import com.flashfuel.project.model.ClientInformation;
import com.flashfuel.project.model.UserCredentials;
import com.flashfuel.project.ClientInformationManager;
import com.flashfuel.project.UserManager;
import com.flashfuel.project.dto.UserProfileRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClientInformationService {
    @Autowired
    private UserManager userManager;
    @Autowired
    private ClientInformationManager clientInfoManager;

    public ClientInformation getClientInformation(String username) {
        UserCredentials user = userManager.getUserByUsername(username);
        return clientInfoManager.getClientInformationByUserId(user.getId());
    }

    public void updateClientInformation(UserProfileRequest request) {
        String errors = getUpdateProfileErrors(request);
        if (errors != null) {
            throw new RuntimeException(errors);
        }

        UserCredentials user = userManager.getUserByUsername(request.getUsername());
        ClientInformation clientInfo = user.getClientInformation();

        clientInfo.setName(request.getName());
        clientInfo.setAddress(request.getAddress());
        clientInfo.setCity(request.getCity());
        clientInfo.setState(request.getState());
        clientInfo.setZipCode(request.getZipCode());

        clientInfoManager.addClientInformation(user.getId(), clientInfo);
    }

    private String getUpdateProfileErrors(UserProfileRequest request) {
        List<String> errorList = new ArrayList<>();

        // check if user exists
        if (userManager.getUserByUsername(request.getUsername()) == null) {
            errorList.add("User does not exist.");
        }

        if (request.getName() == null || request.getName().isBlank())
            errorList.add("Name is required.");

        if (request.getAddress() == null || request.getAddress().isBlank())
            errorList.add("Address is required.");

        if (request.getCity() == null || request.getCity().isBlank())
            errorList.add("City is required.");

        if (request.getState() == null || request.getState().isBlank())
            errorList.add("State is required.");

        if (request.getZipCode() == null || request.getZipCode().isBlank())
            errorList.add("Zip Code is required.");
        else {
            String zipCodePattern = "^\\d{5}(?:[-\\s]\\d{4})?$";
            if (!request.getZipCode().matches(zipCodePattern)) {
                errorList.add("Invalid Zip Code format.");
            }
        }

        if (errorList.size() == 0)
            return null;

        var errorMessage = "<ul>";
        for (String msg : errorList)
            errorMessage += String.format("<li>%s</li>", msg);
        errorMessage += "</ul>";

        return errorMessage;
    }
}
*/
