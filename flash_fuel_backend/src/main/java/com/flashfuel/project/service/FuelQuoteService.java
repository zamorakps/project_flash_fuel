package com.flashfuel.project.service;

import com.flashfuel.project.FuelQuoteManager;
import com.flashfuel.project.UserManager;
import com.flashfuel.project.model.FuelQuote;
import com.flashfuel.project.model.FuelQuoteRequest;
import com.flashfuel.project.model.FuelQuoteResponse;
import com.flashfuel.project.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class FuelQuoteService {

    private final FuelQuoteManager fuelQuoteManager;
    private final UserManager userManager;

    @Autowired
    public FuelQuoteService(FuelQuoteManager fuelQuoteManager, UserManager userManager) {
        this.fuelQuoteManager = fuelQuoteManager;
        this.userManager = userManager;
    }

    public List<FuelQuoteResponse> getFuelQuoteHistory(String userName) {
        List<FuelQuote> fuelQuotes = fuelQuoteManager.getFuelQuotesByUsername(userName);

        // Transform the FuelQuote model objects into FuelQuoteResponse objects and return them
        return fuelQuotes.stream()
                .map(this::transformFuelQuoteToResponse)
                .collect(Collectors.toList());
    }

    public String addFuelQuote(FuelQuoteRequest request, Double suggestedPrice, Double totalAmountDue) {
        var errorMsg = getAddFuelQuoteErrors(request);

        if(errorMsg != null && !errorMsg.isBlank())
            return errorMsg;

        // Fetch the User object using the username in the request
        User user = userManager.getUserByUsername(request.getUsername());
        if (user == null) {
            return "User not found.";
        }

        // Add the fuel quote to the user's history
        fuelQuoteManager.addFuelQuote(user, request.getGallonsRequested(), request.getDeliveryAddress(), request.getDeliveryDate(), suggestedPrice, totalAmountDue);

        return null;
    }

    private String getAddFuelQuoteErrors(FuelQuoteRequest request) {
        List<String> errorList = new ArrayList<>();

        // Perform validation checks here
        // ...

        if(errorList.isEmpty())
            return null;

        var errorMessage = "<ul>";

        for(String msg : errorList)
            errorMessage += String.format("<li>%s</li>", msg);

        errorMessage += "</ul>";

        return errorMessage;
    }

    private FuelQuoteResponse transformFuelQuoteToResponse(FuelQuote fuelQuote) {
        FuelQuoteResponse response = new FuelQuoteResponse();

        response.setUsername(fuelQuote.getUser().getUsername());  // Get the username from the User object in FuelQuote
        response.setGallonsRequested(fuelQuote.getGallonsRequested());
        response.setDeliveryAddress(fuelQuote.getDeliveryAddress());
        response.setDeliveryDate(fuelQuote.getDeliveryDate());
        response.setSuggestedPrice(fuelQuote.getSuggestedPrice());
        response.setTotalAmountDue(fuelQuote.getTotalAmountDue());

        return response;
    }
}
