package com.flashfuel.project;

import com.flashfuel.project.model.FuelQuote;
import com.flashfuel.project.model.User;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class FuelQuoteManager {

    private final Map<String, List<FuelQuote>> userFuelQuotes = new HashMap<>();

    public List<FuelQuote> getFuelQuotesByUsername(String username) {
        return userFuelQuotes.getOrDefault(username, new ArrayList<>());
    }

    public void addFuelQuote(User user, Integer gallonsRequested, String deliveryAddress, LocalDate deliveryDate, Double suggestedPrice, Double totalAmountDue) {
        FuelQuote fuelQuote = new FuelQuote(user, gallonsRequested, deliveryAddress, deliveryDate, suggestedPrice, totalAmountDue);
        userFuelQuotes.computeIfAbsent(user.getUsername(), k -> new ArrayList<>()).add(fuelQuote);
    }
}
