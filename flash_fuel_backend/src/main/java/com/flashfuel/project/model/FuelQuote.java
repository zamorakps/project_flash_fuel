package com.flashfuel.project.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "fuel_quotes")
public class FuelQuote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "gallons_requested", nullable = false)
    private Integer gallonsRequested;

    @Column(name = "delivery_address", nullable = false)
    private String deliveryAddress;

    @Column(name = "delivery_date", nullable = false)
    private LocalDate deliveryDate;

    @Column(name = "suggested_price", nullable = false)
    private Double suggestedPrice;

    @Column(name = "total_amount_due", nullable = false)
    private Double totalAmountDue;

    // Constructors
    public FuelQuote() {
    }

    public FuelQuote(User user, Integer gallonsRequested, String deliveryAddress, LocalDate deliveryDate, Double suggestedPrice, Double totalAmountDue) {
        this.user = user;
        this.gallonsRequested = gallonsRequested;
        this.deliveryAddress = deliveryAddress;
        this.deliveryDate = deliveryDate;
        this.suggestedPrice = suggestedPrice;
        this.totalAmountDue = totalAmountDue;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
