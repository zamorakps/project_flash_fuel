package com.flashfuel.project.controller;

import com.flashfuel.project.model.FuelQuoteRequest;
import com.flashfuel.project.model.FuelQuoteResponse;
import com.flashfuel.project.service.FuelQuoteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class FuelQuoteController {

    private FuelQuoteService fuelQuoteService;

    public FuelQuoteController(FuelQuoteService fuelQuoteService) {
        this.fuelQuoteService = fuelQuoteService;
    }

    @PostMapping("/fuelquote/new")
    public ResponseEntity<String> addFuelQuote(@ModelAttribute FuelQuoteRequest fuelQuoteRequest, @RequestParam Double suggestedPrice, @RequestParam Double totalAmountDue) {
        String result = fuelQuoteService.addFuelQuote(fuelQuoteRequest, suggestedPrice, totalAmountDue);
        if(result == null)
            return ResponseEntity.ok("Fuel quote added successfully.");
        else
            return ResponseEntity.badRequest().body(result);
    }

    @GetMapping("/fuelquote/history")
    public ResponseEntity<List<FuelQuoteResponse>> getFuelQuoteHistory(@RequestParam String username) {
        return ResponseEntity.ok(fuelQuoteService.getFuelQuoteHistory(username));
    }
}
