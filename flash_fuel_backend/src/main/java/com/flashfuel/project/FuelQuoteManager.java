package com.flashfuel.project;

import com.flashfuel.project.model.FuelQuote;
import com.flashfuel.project.model.UserCredentials;

import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class FuelQuoteManager {
    private final FuelQuoteRepository fuelQuoteRepository;

    public FuelQuoteManager(FuelQuoteRepository fuelQuoteRepository) {
        this.fuelQuoteRepository = fuelQuoteRepository;
    }

    public List<FuelQuote> getFuelQuotesByUserId(Long userId) {
        UserCredentials user = new UserCredentials();
        user.setId(userId);
        return fuelQuoteRepository.findByUser(user);
    }

    public void addFuelQuote(UserCredentials user, FuelQuote fuelQuote) {
        fuelQuote.setUser(user);
        fuelQuoteRepository.save(fuelQuote);
    }
}