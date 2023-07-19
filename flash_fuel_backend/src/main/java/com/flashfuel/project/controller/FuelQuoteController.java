
package com.flashfuel.project.controller;

import com.flashfuel.project.model.ClientInformation;
import com.flashfuel.project.model.FuelQuote;
import com.flashfuel.project.model.FuelQuoteDTO;
import com.flashfuel.project.service.ClientInformationService;
import com.flashfuel.project.service.FuelQuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class FuelQuoteController {

    private final FuelQuoteService fuelQuoteService;
    private final ClientInformationService clientInformationService;

    @Autowired
    public FuelQuoteController(FuelQuoteService fuelQuoteService, ClientInformationService clientInformationService) {
        this.fuelQuoteService = fuelQuoteService;
        this.clientInformationService = clientInformationService;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/fuelquote/calculate")
    public ResponseEntity<FuelQuoteDTO> calculatePrices(@RequestBody Map<String, String> requestBody) {
        FuelQuoteDTO response = fuelQuoteService.calculatePrices(requestBody.get("gallonsRequested"));
        return ResponseEntity.ok(response);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/fuelquote/new")
    public ResponseEntity<String> addFuelQuote(@RequestBody FuelQuoteDTO fuelQuote) {
        try {
            fuelQuoteService.addFuelQuote(fuelQuote.getUserId(), fuelQuote);
            return ResponseEntity.ok("Fuel quote added successfully.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Fuel quote not added: " + e.getMessage());
        }
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/fuelquote/history")
    public ResponseEntity<List<FuelQuote>> getFuelQuoteHistory(@RequestParam Long userId) {
        return ResponseEntity.ok(fuelQuoteService.getFuelQuotesByUserId(userId));
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/user/profile")
    public ResponseEntity<ClientInformation> getUserProfile(@RequestParam Long userId) {
        return ResponseEntity.ok(clientInformationService.getClientInformationByUserId(userId));
    }
}



/*
package com.flashfuel.project.controller;

import com.flashfuel.project.model.ClientInformation;
import com.flashfuel.project.model.FuelQuote;
import com.flashfuel.project.model.FuelQuoteDTO;
import com.flashfuel.project.model.FuelQuoteRequest;
import com.flashfuel.project.model.FuelQuoteResponse;
import com.flashfuel.project.model.UserCredentials;
import com.flashfuel.project.model.UserProfileResponse;
import com.flashfuel.project.service.ClientInformationService;
import com.flashfuel.project.service.FuelQuoteService;
import com.flashfuel.project.service.ProfileManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class FuelQuoteController {

    private final FuelQuoteService fuelQuoteService;
    private final ClientInformationService clientInformationService;

    @Autowired
    public FuelQuoteController(FuelQuoteService fuelQuoteService, ClientInformationService clientInformationService) {
        this.fuelQuoteService = fuelQuoteService;
        this.clientInformationService = clientInformationService;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/fuelquote/calculate")
    public ResponseEntity<Map<String, Object>> calculatePrices(@RequestBody Map<String, String> requestBody) {
        String gallonsRequestedStr = requestBody.get("gallonsRequested");
        return fuelQuoteService.calculatePrices(gallonsRequestedStr);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/fuelquote/new")
    public ResponseEntity<?> addFuelQuote(@RequestBody FuelQuoteDTO fuelQuoteDTO) {
        UserCredentials user = fuelQuoteDTO.getUser().toEntity();
        FuelQuote fuelQuote = new FuelQuote(user, 
                                            fuelQuoteDTO.getGallonsRequested(), 
                                            fuelQuoteDTO.getDeliveryDate(), 
                                            fuelQuoteDTO.getSuggestedPrice(), 
                                            fuelQuoteDTO.getTotalAmountDue());

        fuelQuoteService.addFuelQuote(user, fuelQuote);
        return ResponseEntity.ok().build();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/fuelquote/history")
    public ResponseEntity<List<FuelQuote>> getFuelQuoteHistory(@RequestParam String userIdStr) {
        Long userId = Long.parseLong(userIdStr);
        List<FuelQuote> fuelQuotes = fuelQuoteService.getFuelQuotesByUserId(userId);
        return ResponseEntity.ok(fuelQuotes);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/user/profile")
    public ResponseEntity<ClientInformation> getUserProfile(@RequestParam String userIdStr) {
        Long userId = Long.parseLong(userIdStr);
        ClientInformation clientInformation = clientInformationService.getClientInformationByUserId(userId);
        return ResponseEntity.ok(clientInformation);
    }
}
*/



/*
package com.flashfuel.project.controller;

import com.flashfuel.project.model.AddFuelQuoteResponse;
import com.flashfuel.project.model.FuelQuoteRequest;
import com.flashfuel.project.model.FuelQuoteResponse;
import com.flashfuel.project.model.UserProfileResponse;
import com.flashfuel.project.service.FuelQuoteService;
import com.flashfuel.project.service.ProfileManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class FuelQuoteController {

    private final FuelQuoteService fuelQuoteService;
    private final ProfileManagementService profileManagementService;

    @Autowired
    public FuelQuoteController(FuelQuoteService fuelQuoteService, ProfileManagementService profileManagementService) {
        this.fuelQuoteService = fuelQuoteService;
        this.profileManagementService = profileManagementService;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/fuelquote/calculate")
    public ResponseEntity<Map<String, Object>> calculatePrices(@RequestBody Map<String, String> requestBody) {
        Integer gallonsRequested = Integer.parseInt(requestBody.get("gallonsRequested"));
        Double suggestedPrice = 2.0;  // Replace with your actual calculation
        Double totalAmountDue = suggestedPrice * gallonsRequested;

        Map<String, Object> response = new HashMap<>();
        response.put("gallonsRequested", gallonsRequested);
        response.put("suggestedPrice", suggestedPrice);
        response.put("totalAmountDue", totalAmountDue);

        return ResponseEntity.ok(response);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/fuelquote/new")
    public ResponseEntity<?> addFuelQuote(@RequestBody FuelQuoteRequest fuelQuoteRequest) {
        AddFuelQuoteResponse result = fuelQuoteService.addFuelQuote(fuelQuoteRequest);

        // If the result is null, that means the quote was not added.
        if(result == null) {
            AddFuelQuoteResponse response = new AddFuelQuoteResponse();
            response.setMessage("Fuel quote not added.");
            return ResponseEntity.badRequest().body(response);
        }
        else {
            // If the result is not null, that means the quote was added successfully.
            // Now we should return the success message and the data from the result.
            result.setMessage("Fuel quote added successfully.");
            return ResponseEntity.ok(result);
        }
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/fuelquote/history")
    public ResponseEntity<List<FuelQuoteResponse>> getFuelQuoteHistory(@RequestParam String username) {
        return ResponseEntity.ok(fuelQuoteService.getFuelQuoteHistory(username));
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("user/profile")
    public ResponseEntity<UserProfileResponse> getUserProfile(@RequestParam String username) {
        return ResponseEntity.ok(profileManagementService.getProfile(username));
    }
}
*/