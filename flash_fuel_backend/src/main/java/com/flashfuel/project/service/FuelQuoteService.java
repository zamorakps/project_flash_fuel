package com.flashfuel.project.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flashfuel.project.FuelQuoteManager;
import com.flashfuel.project.UserManager;
import com.flashfuel.project.model.ClientInformation;
import com.flashfuel.project.model.ClientInformationDTO;
import com.flashfuel.project.model.FuelQuote;
import com.flashfuel.project.model.FuelQuoteDTO;
import com.flashfuel.project.model.PricingEstimation;
import com.flashfuel.project.model.UserCredentials;
import com.flashfuel.project.model.UserCredentialsDTO;

import org.springframework.http.ResponseEntity;

import java.util.HashMap;

@Service
public class FuelQuoteService {

    private final FuelQuoteManager fuelQuoteManager;
    private final UserManager userManager;
    private final ClientInformationService clientInformationService;

    @Autowired
    public FuelQuoteService(FuelQuoteManager fuelQuoteManager, ClientInformationService clientInformationService, UserManager userManager) {
        this.fuelQuoteManager = fuelQuoteManager;
        this.clientInformationService = clientInformationService;
        this.userManager = userManager;
    }
    
    public List<FuelQuote> getFuelQuotesByUserId(Long userId) {
        return fuelQuoteManager.getFuelQuotesByUserId(userId);
    }

    public PricingEstimation calculatePrices(String gallonsRequestedStr, String state, Long userId) {
    //Integer gallonsRequested = Integer.parseInt(gallonsRequestedStr);
    // Double suggestedPrice = 2.0;
    // Double totalAmountDue = suggestedPrice * gallonsRequested;
    Double gallonsRequested = Double.parseDouble(gallonsRequestedStr);

    Double currentPricePerGallon = 1.50;

    Double locationFactor = 0.0;
    if (state.equals("TX"))
        locationFactor = 0.02;
    else
        locationFactor = 0.04;

    Double rateHistoryFactor = 0.0;
    if (getFuelQuotesByUserId(userId) != null)
        rateHistoryFactor = 0.01;

    Double gallonsRequestedFactor = 0.0;
    if (gallonsRequested < 1000)
        gallonsRequestedFactor = 0.03;
    else
        gallonsRequestedFactor = 0.02;
    
    Double companyProfitMargin = 0.10;

    Double margin = currentPricePerGallon * (locationFactor - rateHistoryFactor + gallonsRequestedFactor + companyProfitMargin);

    Double suggestedPrice = currentPricePerGallon + margin;

    Double totalAmountDue = gallonsRequested * suggestedPrice;

    PricingEstimation response = new PricingEstimation();
    response.setSuggestedPricePerGallon(suggestedPrice);
    response.setTotalPrice(totalAmountDue);

    return response;
}

/*
public FuelQuoteDTO calculatePrices(String gallonsRequestedStr) {
    Integer gallonsRequested = Integer.parseInt(gallonsRequestedStr);
    Double suggestedPrice = 2.0;
    Double totalAmountDue = suggestedPrice * gallonsRequested;

    FuelQuoteDTO response = new FuelQuoteDTO();
    response.setGallonsRequested(gallonsRequested);
    response.setSuggestedPrice(suggestedPrice);
    response.setTotalAmountDue(totalAmountDue);

    return response;
}
*/

public void addFuelQuote(Long userId, FuelQuoteDTO fuelQuoteDto) {
    ClientInformation clientInfo = clientInformationService.getClientInformationByUserId(userId);
    if (clientInfo == null) {
        throw new RuntimeException("Client information not found for user id: " + userId);
    }

    UserCredentials user = userManager.getUserById(userId);
    if (user == null) {
        throw new RuntimeException("User not found with id: " + userId);
    }

    FuelQuote fuelQuote = mapDtoToEntity(fuelQuoteDto, user);

    String errors = validateFuelQuote(fuelQuoteDto);

    if (errors != null) {
        throw new RuntimeException(errors);
    }
    
    fuelQuoteManager.addFuelQuote(user, fuelQuote);
}

private FuelQuote mapDtoToEntity(FuelQuoteDTO fuelQuoteDto, UserCredentials user) {
    FuelQuote fuelQuote = new FuelQuote();
    
    fuelQuote.setId(fuelQuoteDto.getId());
    fuelQuote.setGallonsRequested(fuelQuoteDto.getGallonsRequested());
    fuelQuote.setDeliveryDate(fuelQuoteDto.getDeliveryDate());
    fuelQuote.setSuggestedPrice(fuelQuoteDto.getSuggestedPrice());
    fuelQuote.setTotalAmountDue(fuelQuoteDto.getTotalAmountDue());
    fuelQuote.setUser(user);
    
    return fuelQuote;
}    
    public String validateFuelQuote(FuelQuoteDTO fuelQuoteDTO) {
        List<String> errorList = new ArrayList<>();
        if(fuelQuoteDTO.getGallonsRequested() <= 0)
            errorList.add("Gallons requested must be greater than 0.");

        if(fuelQuoteDTO.getDeliveryDate().isBefore(LocalDate.now()))
            errorList.add("Delivery date must be today's date or in the future.");

        if(fuelQuoteDTO.getSuggestedPrice() == null || fuelQuoteDTO.getSuggestedPrice() < 0)
            errorList.add("Suggested price cannot be null or negative.");

        if(fuelQuoteDTO.getTotalAmountDue() == null || fuelQuoteDTO.getTotalAmountDue() < 0)
            errorList.add("Total amount due cannot be null or negative.");

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

    private UserCredentials mapDtoToEntity(UserCredentialsDTO userDto) {
        UserCredentials user = new UserCredentials();
        
        user.setId(userDto.getId());
        user.setUsername(userDto.getUsername());
        
        ClientInformationDTO clientInfoDTO = userDto.getClientInformation();
        if (clientInfoDTO != null) {
            ClientInformation clientInfo = new ClientInformation();
            clientInfo.setId(clientInfoDTO.getId());
            clientInfo.setAddress(clientInfoDTO.getAddress());
            clientInfo.setAddressLine2(clientInfoDTO.getAddressLine2());
            clientInfo.setCity(clientInfoDTO.getCity());
            clientInfo.setState(clientInfoDTO.getState());
            clientInfo.setZipCode(clientInfoDTO.getZipCode());
            user.setClientInformation(clientInfo);
        }
        
        return user;
    }
    private UserCredentialsDTO mapUserToUserDTO(UserCredentials user) {
        UserCredentialsDTO userDTO = new UserCredentialsDTO();
        
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());

        ClientInformation clientInfo = user.getClientInformation();
        if (clientInfo != null) {
            ClientInformationDTO clientInfoDTO = new ClientInformationDTO();
            clientInfoDTO.setId(clientInfo.getId());
            clientInfoDTO.setAddress(clientInfo.getAddress());
            clientInfoDTO.setAddressLine2(clientInfo.getAddressLine2());
            clientInfoDTO.setCity(clientInfo.getCity());
            clientInfoDTO.setState(clientInfo.getState());
            clientInfoDTO.setZipCode(clientInfo.getZipCode());
            userDTO.setClientInformation(clientInfoDTO);
        }

        return userDTO;
    }



    private FuelQuoteDTO mapToDTO(FuelQuote fuelQuote) {
        FuelQuoteDTO dto = new FuelQuoteDTO();
        dto.setId(fuelQuote.getId());
        dto.setGallonsRequested(fuelQuote.getGallonsRequested());
        dto.setDeliveryDate(fuelQuote.getDeliveryDate());
        dto.setSuggestedPrice(fuelQuote.getSuggestedPrice());
        dto.setTotalAmountDue(fuelQuote.getTotalAmountDue());
    
        UserCredentialsDTO userDTO = mapUserToUserDTO(fuelQuote.getUser());
        dto.setUser(userDTO);
    
        return dto;
    }
*/

/*
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flashfuel.project.FuelQuoteManager;
import com.flashfuel.project.model.FuelQuote;
import com.flashfuel.project.model.FuelQuoteDTO;

import org.springframework.http.ResponseEntity;

import java.util.HashMap;

@Service
public class FuelQuoteService {

    @Autowired
    private FuelQuoteManager fuelQuoteManager;

    public FuelQuoteDTO addFuelQuote(FuelQuoteDTO fuelQuoteDTO) {
        validateFuelQuote(fuelQuoteDTO);
        FuelQuote fuelQuote = new FuelQuote(
                fuelQuoteDTO.getUser(),
                fuelQuoteDTO.getGallonsRequested(),
                fuelQuoteDTO.getDeliveryDate(),
                fuelQuoteDTO.getSuggestedPrice(),
                fuelQuoteDTO.getTotalAmountDue());
        fuelQuoteManager.addFuelQuote(fuelQuote);
        return fuelQuoteDTO;
    }

    public ResponseEntity<Map<String, Object>> calculatePrices(String gallonsRequestedStr) {
        Integer gallonsRequested = Integer.parseInt(gallonsRequestedStr);
        Double suggestedPrice = 2.0;
        Double totalAmountDue = suggestedPrice * gallonsRequested;

        Map<String, Object> response = new HashMap<>();
        response.put("gallonsRequested", gallonsRequested);
        response.put("suggestedPrice", suggestedPrice);
        response.put("totalAmountDue", totalAmountDue);

        return ResponseEntity.ok(response);
    }

    // Implement getFuelQuoteHistory() here

    private void validateFuelQuote(FuelQuoteDTO fuelQuoteDTO) {
        List<String> errorList = new ArrayList<>();
        if(fuelQuoteDTO.getGallonsRequested() <= 0)
            errorList.add("Gallons requested must be greater than 0.");

        if(fuelQuoteDTO.getDeliveryDate().isBefore(LocalDate.now()))
            errorList.add("Delivery date must be today's date or in the future.");

        if(fuelQuoteDTO.getSuggestedPrice() == null || fuelQuoteDTO.getSuggestedPrice() < 0)
            errorList.add("Suggested price cannot be null or negative.");

        if(fuelQuoteDTO.getTotalAmountDue() == null || fuelQuoteDTO.getTotalAmountDue() < 0)
            errorList.add("Total amount due cannot be null or negative.");

        if(!errorList.isEmpty())
            throw new RuntimeException(errorList.toString());
    }
}
*/



/*
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
*/




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
