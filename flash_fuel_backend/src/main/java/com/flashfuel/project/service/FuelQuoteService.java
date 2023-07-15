package com.flashfuel.project.service;

import com.flashfuel.project.FuelQuoteManager;
import com.flashfuel.project.UserManager;
import com.flashfuel.project.model.AddFuelQuoteResponse;
import com.flashfuel.project.model.FuelQuote;
import com.flashfuel.project.model.FuelQuoteRequest;
import com.flashfuel.project.model.FuelQuoteResponse;
import com.flashfuel.project.model.User;
import com.flashfuel.project.model.UserProfileResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class FuelQuoteService {

    private final FuelQuoteManager fuelQuoteManager;
    private final UserManager userManager;
    private final ProfileManagementService profileManagementService;

    @Autowired
    public FuelQuoteService(FuelQuoteManager fuelQuoteManager, UserManager userManager, ProfileManagementService profileManagementService) {
        this.fuelQuoteManager = fuelQuoteManager;
        this.userManager = userManager;
        this.profileManagementService = profileManagementService;
        // this.createDummyFuelQuotes();
    }

    public List<FuelQuoteResponse> getFuelQuoteHistory(String userName) {
        List<FuelQuote> fuelQuotes = fuelQuoteManager.getFuelQuotesByUsername(userName);

        return fuelQuotes.stream()
                .map(this::transformFuelQuoteToResponse)
                .collect(Collectors.toList());
    }

    public AddFuelQuoteResponse addFuelQuote(FuelQuoteRequest request) {
    var errorMsg = getAddFuelQuoteErrors(request);

    AddFuelQuoteResponse response = new AddFuelQuoteResponse();

    if(errorMsg != null && !errorMsg.isBlank()) {
        response.setMessage(errorMsg);
        return response;
    }

    User user = userManager.getUserByUsername(request.getUsername());

    if (user == null) {
        response.setMessage("User not found.");
        return response;
    }

    fuelQuoteManager.addFuelQuote(user, request.getGallonsRequested(), request.getDeliveryAddress(), request.getDeliveryDate(), request.getSuggestedPrice(), request.getTotalAmountDue());

    response.setMessage("Fuel quote added successfully.");
    response.setSuggestedPrice(request.getSuggestedPrice());
    response.setTotalAmountDue(request.getTotalAmountDue());

    return response;
}

    private String getAddFuelQuoteErrors(FuelQuoteRequest request) {
        List<String> errorList = new ArrayList<>();

        if(request.getGallonsRequested() <= 0)
            errorList.add("Gallons requested must be greater than 0.");

        if(request.getDeliveryAddress() == null || request.getDeliveryAddress().isBlank())
            errorList.add("Delivery address is required.");

        if(request.getDeliveryDate() == null || request.getDeliveryDate().isBefore(LocalDate.now()))
            errorList.add("Delivery date must be today's date or in the future.");

        if(request.getSuggestedPrice() == null || request.getSuggestedPrice() < 0)
            errorList.add("Suggested price cannot be null or negative.");

        if(request.getTotalAmountDue() == null || request.getTotalAmountDue() < 0)
            errorList.add("Total amount due cannot be null or negative.");

        if(errorList.isEmpty())
            return null;

        var errorMessage = "<ul>";

        for(String msg : errorList)
            errorMessage += String.format("<li>%s</li>", msg);

        errorMessage += "</ul>";

        return errorMessage;
    }

    public FuelQuoteResponse transformFuelQuoteToResponse(FuelQuote fuelQuote) {
        FuelQuoteResponse response = new FuelQuoteResponse();

        response.setUsername(fuelQuote.getUser().getUsername());
        response.setGallonsRequested(fuelQuote.getGallonsRequested());
        response.setDeliveryAddress(fuelQuote.getDeliveryAddress());
        response.setDeliveryDate(fuelQuote.getDeliveryDate());
        response.setSuggestedPrice(fuelQuote.getSuggestedPrice());
        response.setTotalAmountDue(fuelQuote.getTotalAmountDue());

        return response;
    }
}



    /*
    public void createDummyFuelQuotes() {
        UserProfileResponse userProfile = profileManagementService.getProfile("testUser");
        User dummyUser = new User();

        dummyUser.setUsername(userProfile.getUsername());
        dummyUser.setPassword("password");
        dummyUser.setName(userProfile.getName());
        dummyUser.setAddress(userProfile.getAddress());
        dummyUser.setAddressLine2(userProfile.getAddressLine2());
        dummyUser.setCity(userProfile.getCity());
        dummyUser.setState(userProfile.getState());
        dummyUser.setZipCode(userProfile.getZipCode());

        List<FuelQuote> dummyFuelQuotes = new ArrayList<>();
        for(int i = 0; i < 5; i++) {
            FuelQuote dummyFuelQuote = new FuelQuote(dummyUser, 100 * (i+1), dummyUser.getAddress(), LocalDate.now().plusDays(i), 2.0 * (i+1), 200.0 * (i+1));
            dummyFuelQuotes.add(dummyFuelQuote);
        }

        for(FuelQuote dummyFuelQuote : dummyFuelQuotes) {
            fuelQuoteManager.addFuelQuote(dummyFuelQuote.getUser(), dummyFuelQuote.getGallonsRequested(), dummyFuelQuote.getDeliveryAddress(), dummyFuelQuote.getDeliveryDate(), dummyFuelQuote.getSuggestedPrice(), dummyFuelQuote.getTotalAmountDue());
        }
    }

    */
