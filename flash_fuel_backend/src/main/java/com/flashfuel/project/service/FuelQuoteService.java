// package com.flashfuel.project.service;

// import com.flashfuel.project.FuelQuoteManager;
// import com.flashfuel.project.UserManager;
// import com.flashfuel.project.model.FuelQuote;
// import com.flashfuel.project.model.FuelQuoteRequest;
// import com.flashfuel.project.model.FuelQuoteResponse;
// import com.flashfuel.project.model.User;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Component;

// import java.time.LocalDate;
// import java.util.ArrayList;
// import java.util.List;
// import java.util.stream.Collectors;

// @Component
// public class FuelQuoteService {

//     private final FuelQuoteManager fuelQuoteManager;
//     private final UserManager userManager;

//     @Autowired
//     public FuelQuoteService(FuelQuoteManager fuelQuoteManager, UserManager userManager) {
//         this.fuelQuoteManager = fuelQuoteManager;
//         this.userManager = userManager;
//         this.createDummyFuelQuotes();
//     }

//     public List<FuelQuoteResponse> getFuelQuoteHistory(String userName) {
//         List<FuelQuote> fuelQuotes = fuelQuoteManager.getFuelQuotesByUsername(userName);

//         // Transform the FuelQuote model objects into FuelQuoteResponse objects and return them
//         return fuelQuotes.stream()
//                 .map(this::transformFuelQuoteToResponse)
//                 .collect(Collectors.toList());
//     }

//     public String addFuelQuote(FuelQuoteRequest request, Double suggestedPrice, Double totalAmountDue) {
//         var errorMsg = getAddFuelQuoteErrors(request);

//         if(errorMsg != null && !errorMsg.isBlank())
//             return errorMsg;

//         // Fetch the User object using the username in the request
//         User user = userManager.getUserByUsername(request.getUsername());
//         if (user == null) {
//             return "User not found.";
//         }

//         // Add the fuel quote to the user's history
//         fuelQuoteManager.addFuelQuote(user, request.getGallonsRequested(), request.getDeliveryAddress(), request.getDeliveryDate(), suggestedPrice, totalAmountDue);

//         return null;
//     }

//     private String getAddFuelQuoteErrors(FuelQuoteRequest request) {
//         List<String> errorList = new ArrayList<>();

//         // Perform validation checks here
//         // ...

//         if(errorList.isEmpty())
//             return null;

//         var errorMessage = "<ul>";

//         for(String msg : errorList)
//             errorMessage += String.format("<li>%s</li>", msg);

//         errorMessage += "</ul>";

//         return errorMessage;
//     }

//     private FuelQuoteResponse transformFuelQuoteToResponse(FuelQuote fuelQuote) {
//         FuelQuoteResponse response = new FuelQuoteResponse();

//         response.setUsername(fuelQuote.getUser().getUsername());  // Get the username from the User object in FuelQuote
//         response.setGallonsRequested(fuelQuote.getGallonsRequested());
//         response.setDeliveryAddress(fuelQuote.getDeliveryAddress());
//         response.setDeliveryDate(fuelQuote.getDeliveryDate());
//         response.setSuggestedPrice(fuelQuote.getSuggestedPrice());
//         response.setTotalAmountDue(fuelQuote.getTotalAmountDue());

//         return response;
//     }

//     private void createDummyFuelQuotes() {
//         User dummyUser = new User();

//         dummyUser.setUsername("testUser");
//         dummyUser.setPassword("password");
//         dummyUser.setName("Test Name");
//         dummyUser.setAddress("123 Fake St");
//         dummyUser.setAddressLine2("Apt 1");
//         dummyUser.setCity("Houston");
//         dummyUser.setState("TX");
//         dummyUser.setZipCode("77003");

//         List<FuelQuote> dummyFuelQuotes = new ArrayList<>();
//         for(int i = 0; i < 5; i++) {
//             FuelQuote dummyFuelQuote = new FuelQuote(dummyUser, 100 * (i+1), dummyUser.getAddress(), LocalDate.now().plusDays(i), 2.0 * (i+1), 200.0 * (i+1));
//             dummyFuelQuotes.add(dummyFuelQuote);
//         }

//         for(FuelQuote dummyFuelQuote : dummyFuelQuotes) {
//             fuelQuoteManager.addFuelQuote(dummyFuelQuote.getUser(), dummyFuelQuote.getGallonsRequested(), dummyFuelQuote.getDeliveryAddress(), dummyFuelQuote.getDeliveryDate(), dummyFuelQuote.getSuggestedPrice(), dummyFuelQuote.getTotalAmountDue());
//         }
//     }
// }

package com.flashfuel.project.service;

import com.flashfuel.project.FuelQuoteManager;
import com.flashfuel.project.UserManager;
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
        this.createDummyFuelQuotes();
    }

    public List<FuelQuoteResponse> getFuelQuoteHistory(String userName) {
        List<FuelQuote> fuelQuotes = fuelQuoteManager.getFuelQuotesByUsername(userName);

        return fuelQuotes.stream()
                .map(this::transformFuelQuoteToResponse)
                .collect(Collectors.toList());
    }

    public String addFuelQuote(FuelQuoteRequest request, Double suggestedPrice, Double totalAmountDue) {
        var errorMsg = getAddFuelQuoteErrors(request);

        if(errorMsg != null && !errorMsg.isBlank())
            return errorMsg;

        User user = userManager.getUserByUsername(request.getUsername());
        if (user == null) {
            return "User not found.";
        }

        fuelQuoteManager.addFuelQuote(user, request.getGallonsRequested(), request.getDeliveryAddress(), request.getDeliveryDate(), suggestedPrice, totalAmountDue);

        return null;
    }

    private String getAddFuelQuoteErrors(FuelQuoteRequest request) {
        List<String> errorList = new ArrayList<>();

        // Validation checks here
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

        response.setUsername(fuelQuote.getUser().getUsername());
        response.setGallonsRequested(fuelQuote.getGallonsRequested());
        response.setDeliveryAddress(fuelQuote.getDeliveryAddress());
        response.setDeliveryDate(fuelQuote.getDeliveryDate());
        response.setSuggestedPrice(fuelQuote.getSuggestedPrice());
        response.setTotalAmountDue(fuelQuote.getTotalAmountDue());

        return response;
    }

    private void createDummyFuelQuotes() {
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
}




