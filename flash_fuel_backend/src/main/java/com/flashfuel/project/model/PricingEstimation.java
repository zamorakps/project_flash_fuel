package com.flashfuel.project.model;

public class PricingEstimation {
    private double suggestedPricePerGallon;
    private double totalPrice;

    public double getSuggestedPricePerGallon() {
        return suggestedPricePerGallon;
    }

    public void setSuggestedPricePerGallon(double suggestedPricePerGallon) {
        this.suggestedPricePerGallon = suggestedPricePerGallon;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
