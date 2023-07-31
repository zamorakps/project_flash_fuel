package com.flashfuel.project.service;

import com.flashfuel.project.model.PricingEstimation;

import org.springframework.stereotype.Service;

@Service
public class PricingEstimationService {

    private final FuelQuoteService fuelQuoteService;

    public PricingEstimationService(FuelQuoteService fuelQuoteService) {
        this.fuelQuoteService = fuelQuoteService;
    }

    public PricingEstimation calculatePrices(String gallonsRequestedStr, String state, Long userId) {
        Double gallonsRequested = Double.parseDouble(gallonsRequestedStr);

        Double currentPricePerGallon = 1.50;

        Double locationFactor = 0.0;
        if (state.equals("TX"))
            locationFactor = 0.02;
        else
            locationFactor = 0.04;

        Double rateHistoryFactor = 0.0;
        if (fuelQuoteService.getFuelQuotesByUserId(userId) != null)
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
}
