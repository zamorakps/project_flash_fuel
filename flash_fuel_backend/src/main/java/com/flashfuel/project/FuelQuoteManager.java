package com.flashfuel.project;

import com.flashfuel.project.model.FuelQuote;
import com.flashfuel.project.model.FuelQuoteDTO;
import com.flashfuel.project.model.UserCredentials;
import com.flashfuel.project.model.UserCredentialsDTO;

import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

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


/*
@Component
public class FuelQuoteManager {
    private Map<Long, List<FuelQuote>> fuelQuotes;

    public FuelQuoteManager() {
        fuelQuotes = new ConcurrentHashMap<>();
    }

    public List<FuelQuote> getFuelQuotesByUserId(Long userId) {
        return fuelQuotes.getOrDefault(userId, new ArrayList<>());
    }

    public void addFuelQuote(UserCredentials user, FuelQuote fuelQuote) {
        synchronized (this) {
            fuelQuotes.computeIfAbsent(user.getId(), k -> new ArrayList<>()).add(fuelQuote);
        }
    }
}
*/