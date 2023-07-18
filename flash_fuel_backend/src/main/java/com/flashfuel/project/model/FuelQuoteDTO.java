package com.flashfuel.project.model;

import java.time.LocalDate;

public class FuelQuoteDTO {

    private Long id;
    private UserCredentialsDTO user;
    private Integer gallonsRequested;
    private LocalDate deliveryDate;
    private Double suggestedPrice;
    private Double totalAmountDue;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserCredentialsDTO getUser() {
        return user;
    }

    public void setUser(UserCredentialsDTO user) {
        this.user = user;
    }

    public Integer getGallonsRequested() {
        return gallonsRequested;
    }

    public void setGallonsRequested(Integer gallonsRequested) {
        this.gallonsRequested = gallonsRequested;
    }

    public String getDeliveryAddress() {
        if (user != null && user.getClientInformation() != null) {
            return user.getClientInformation().getAddress() 
                 + ", " + user.getClientInformation().getAddressLine2() 
                 + ", " + user.getClientInformation().getCity() 
                 + ", " + user.getClientInformation().getState() 
                 + ", " + user.getClientInformation().getZipCode();
        } else {
            return null;  // or return some default value
        }
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
