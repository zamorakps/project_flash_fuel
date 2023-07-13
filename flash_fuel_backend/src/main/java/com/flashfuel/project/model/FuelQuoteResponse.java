package com.flashfuel.project.model;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

@Component
public class FuelQuoteResponse {
    private String username;
    private Integer gallonsRequested;
    private String deliveryAddress;
    private LocalDate deliveryDate;
    private Double suggestedPrice;
    private Double totalAmountDue;

    // Getters and setters for the fields
    
        public String getUsername() {
            return username;
        }
    
        public void setUsername(String username) {
            this.username = username;
        }
    
        public Integer getGallonsRequested() {
            return gallonsRequested;
        }
    
        public void setGallonsRequested(Integer gallonsRequested) {
            this.gallonsRequested = gallonsRequested;
        }
    
        public String getDeliveryAddress() {
            return deliveryAddress;
        }
    
        public void setDeliveryAddress(String deliveryAddress) {
            this.deliveryAddress = deliveryAddress;
        }
    
        public LocalDate getDeliveryDate() {
            return deliveryDate;
        }
    
        public void setDeliveryDate(LocalDate deliveryDate) {
            this.deliveryDate = deliveryDate;
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
