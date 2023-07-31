package com.flashfuel.project.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.flashfuel.project.FuelQuoteManager;
import com.flashfuel.project.UserManager;
import com.flashfuel.project.model.ClientInformation;
import com.flashfuel.project.model.FuelQuote;
import com.flashfuel.project.model.FuelQuoteDTO;
import com.flashfuel.project.model.UserCredentials;

@Service
public class FuelQuoteService {

    private final FuelQuoteManager fuelQuoteManager;
    private final UserManager userManager;
    private final ClientInformationService clientInformationService;

    public FuelQuoteService(FuelQuoteManager fuelQuoteManager, ClientInformationService clientInformationService, UserManager userManager) {
        this.fuelQuoteManager = fuelQuoteManager;
        this.clientInformationService = clientInformationService;
        this.userManager = userManager;
    }
    
    public List<FuelQuote> getFuelQuotesByUserId(Long userId) {
        return fuelQuoteManager.getFuelQuotesByUserId(userId);
    }

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