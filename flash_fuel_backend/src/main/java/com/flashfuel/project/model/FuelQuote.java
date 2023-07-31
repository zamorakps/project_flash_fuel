package com.flashfuel.project.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "fuel_quotes")
public class FuelQuote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private UserCredentials user;

    @Column(name = "gallons_requested", nullable = false)
    private Integer gallonsRequested;

    // Removed the deliveryAddress field

    @Column(name = "delivery_date", nullable = false)
    private LocalDate deliveryDate;

    @Column(name = "suggested_price", nullable = false)
    private Double suggestedPrice;

    @Column(name = "total_amount_due", nullable = false)
    private Double totalAmountDue;

    // Constructors
    public FuelQuote() {
    }

    public FuelQuote(UserCredentials user, Integer gallonsRequested, LocalDate deliveryDate, Double suggestedPrice, Double totalAmountDue) {
        this.user = user;
        this.gallonsRequested = gallonsRequested;
        // Removed the code that sets the deliveryAddress field
        this.deliveryDate = deliveryDate;
        this.suggestedPrice = suggestedPrice;
        this.totalAmountDue = totalAmountDue;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserCredentials getUser() {
        return user;
    }

    public void setUser(UserCredentials user) {
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