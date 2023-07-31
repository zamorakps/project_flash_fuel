
package com.flashfuel.project.controller;

import com.flashfuel.project.config.TokenProvider;
import com.flashfuel.project.model.ClientInformation;
import com.flashfuel.project.model.FuelQuote;
import com.flashfuel.project.model.FuelQuoteDTO;
import com.flashfuel.project.model.PricingEstimation;
import com.flashfuel.project.service.ClientInformationService;
import com.flashfuel.project.service.FuelQuoteService;
import com.flashfuel.project.service.PricingEstimationService;

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
    private final PricingEstimationService pricingEstimationService;
    private final TokenProvider tokenProvider;

    public FuelQuoteController(FuelQuoteService fuelQuoteService, ClientInformationService clientInformationService, PricingEstimationService pricingEstimationService, TokenProvider tokenProvider) {
        this.fuelQuoteService = fuelQuoteService;
        this.clientInformationService = clientInformationService;
        this.pricingEstimationService = pricingEstimationService;
        this.tokenProvider = tokenProvider;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/fuelquote/calculate")
    public ResponseEntity<PricingEstimation> calculatePrices(@RequestHeader("Authorization") String authorizationHeader, @RequestBody Map<String, String> requestBody) {
        //TokenProvider tokenProvider = new TokenProvider();
        String jwtToken = authorizationHeader.substring(7);
        PricingEstimation response = pricingEstimationService.calculatePrices(requestBody.get("gallonsRequested"),requestBody.get("clientState"),tokenProvider.getIdFromToken(jwtToken));
        return ResponseEntity.ok(response);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/fuelquote/new")
    public ResponseEntity<?> addFuelQuote(@RequestHeader("Authorization") String authorizationHeader, @RequestBody FuelQuoteDTO fuelQuote) {
        try {
            //TokenProvider tokenProvider = new TokenProvider();
            String jwtToken = authorizationHeader.substring(7);
            fuelQuoteService.addFuelQuote(tokenProvider.getIdFromToken(jwtToken), fuelQuote);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Fuel quote added successfully.");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Fuel quote not added: " + e.getMessage());
        }
    }    

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/fuelquote/history")
    public ResponseEntity<List<FuelQuote>> getFuelQuoteHistory(@RequestHeader("Authorization") String authorizationHeader) {
        TokenProvider tokenProvider = new TokenProvider();
        String jwtToken = authorizationHeader.substring(7);
        return ResponseEntity.ok(fuelQuoteService.getFuelQuotesByUserId(tokenProvider.getIdFromToken(jwtToken)));
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/user/profile")
    public ResponseEntity<ClientInformation> getUserProfile(@RequestHeader("Authorization") String authorizationHeader) {
        TokenProvider tokenProvider = new TokenProvider();
        String jwtToken = authorizationHeader.substring(7);
        return ResponseEntity.ok(clientInformationService.getClientInformationByUserId(tokenProvider.getIdFromToken(jwtToken)));
    }
}