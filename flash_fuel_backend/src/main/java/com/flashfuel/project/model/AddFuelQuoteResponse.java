package com.flashfuel.project.model;

public class AddFuelQuoteResponse {
    public String message;

    public Double suggestedPrice;

    public Double totalAmountDue;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Double getSuggestedPrice() {
        return suggestedPrice;
    }

    public void setSuggestedPrice(Double suggestedPrice) {
        this.suggestedPrice = suggestedPrice;
    }

    public Double getTotalAmountDue() {
        return totalAmountDue;
    }

    public void setTotalAmountDue(Double totalAmountDue) {
        this.totalAmountDue = totalAmountDue;
    }
}
