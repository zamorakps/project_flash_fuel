package com.flashfuel.project.service;
import com.flashfuel.project.model.User;
import com.flashfuel.project.model.UserProfileRequest;
import com.flashfuel.project.model.UserProfileResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProfileManagementService {

    public UserProfileResponse getProfile(String userName) {
        //get user from database
        var response = new UserProfileResponse();

        response.setUsername("Test Username");
        response.setName("Test Name");
        response.setAddress("123 Fake St");
        response.setAddressLine2("Apt 1");
        response.setCity("Houston");
        response.setState("TX");
        response.setZipCode("77003");

        return response;
    }

    public String updateProfile(UserProfileRequest request) {
        var errorMsg = getUpdateProfileErrors(request);

        if(errorMsg == null ||
            !errorMsg.isBlank())
            return errorMsg;
        // if(errorMsg != null && !errorMsg.isBlank())
        //     return errorMsg;

        //get user from database
        var user = new User();

        user.setName(request.getName());
        user.setAddress(request.getAddress());
        user.setAddressLine2(request.getAddressLine2());
        user.setCity(request.getCity());
        user.setZipCode(request.getZipCode());

        //save user here

        return null;
    }

    private String getUpdateProfileErrors(UserProfileRequest request) {
        List<String> errorList = new ArrayList<>();

        //check if user exists

        if(request.getName().isBlank())
            errorList.add("Name is required.");

        if(request.getAddress().isBlank())
            errorList.add("Address is required.");

        if(request.getCity().isBlank())
            errorList.add("City is required.");

        if(request.getState().isBlank())
            errorList.add("State is required.");

        if(request.getZipCode().isBlank())
            errorList.add("Zip Code is required.");
        else {
            String zipCodePattern = "^\\d{5}(?:[-\\s]\\d{4})?$";
            if (!request.getZipCode().matches(zipCodePattern)) {
                errorList.add("Invalid Zip Code format.");
            }
        }

        if(errorList.size() == 0)
            return null;

        var errorMessage = "<ul>";

        for(String msg : errorList)
            errorMessage += String.format("<li>%s</li>", msg);

        errorMessage += "</ul>";

        return errorMessage;
    }
}
