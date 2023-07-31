package com.flashfuel.project.model;

import java.time.LocalDate;

public class FuelQuoteDTO {

    private Long id;
    private Long userId;
    private Integer gallonsRequested;
    // private String deliveryAddress;
    private LocalDate deliveryDate;
    private Double suggestedPrice;
    private Double totalAmountDue;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getGallonsRequested() {
        return gallonsRequested;
    }

    public void setGallonsRequested(Integer gallonsRequested) {
        this.gallonsRequested = gallonsRequested;
    }

    // public String getDeliveryAddress() {
    //     return deliveryAddress;
    // }

    // public void setDeliveryAddress(String deliveryAddress) {
    //     this.deliveryAddress = deliveryAddress;
    // }   

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