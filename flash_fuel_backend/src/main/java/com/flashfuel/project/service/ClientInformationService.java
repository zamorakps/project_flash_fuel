package com.flashfuel.project.service;

import com.flashfuel.project.ClientInformationRepository;
import com.flashfuel.project.UserCredentialsRepository;
import com.flashfuel.project.model.ClientInformation;
import com.flashfuel.project.ClientInformationManager;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClientInformationService {

    private final ClientInformationManager clientInformationManager;
    private final ClientInformationRepository clientInformationRepository;
    private final UserCredentialsRepository userCredentialsRepository;

    public ClientInformationService(ClientInformationManager clientInformationManager,
                                    ClientInformationRepository clientInformationRepository,
                                    UserCredentialsRepository userCredentialsRepository) {
        this.clientInformationManager = clientInformationManager;
        this.clientInformationRepository = clientInformationRepository;
        this.userCredentialsRepository = userCredentialsRepository;
    }

    public ClientInformation getClientInformationByUserId(Long userId) {
        return clientInformationManager.getClientInformationByUserId(userId);
    }

    public String updateClientInformation(Long userId, ClientInformation newClientInformation) {
        String errors = getUpdateProfileErrors(newClientInformation);
    
        if (errors != null) {
            return errors;
        }

        var user = userCredentialsRepository.findById(userId).get();
        var info = user.getClientInformation();

        info.setName(newClientInformation.getName());
        info.setAddress(newClientInformation.getAddress());
        info.setAddressLine2(newClientInformation.getAddressLine2());
        info.setCity(newClientInformation.getCity());
        info.setState(newClientInformation.getState());
        info.setZipCode(newClientInformation.getZipCode());

        clientInformationRepository.save(info);

        return null;
    }    

    public String getUpdateProfileErrors(ClientInformation request) {
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