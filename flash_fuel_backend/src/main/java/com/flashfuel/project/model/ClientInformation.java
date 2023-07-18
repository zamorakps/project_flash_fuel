package com.flashfuel.project.model;

import jakarta.persistence.*;

@Entity
@Table(name = "client_information")
public class ClientInformation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true)
    private String name;

    @Column(nullable = true)
    private String address;

    @Column(name = "address_line2", nullable = true)
    private String addressLine2;

    @Column(nullable = true)
    private String city;

    @Column(nullable = true)
    private String state;

    @Column(name = "zip_code", nullable = true)
    private String zipCode;

    @OneToOne(mappedBy = "clientInformation")
    private UserCredentials userCredentials;

    public ClientInformation() {}

    public ClientInformation(String name, String address, String addressLine2, String city, String state, String zipCode) {
        this.name = name;
        this.address = address;
        this.addressLine2 = addressLine2;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public UserCredentials getUserCredentials() {
        return userCredentials;
    }

    /*
    public void setUserCredentials(UserCredentials userCredentials) {
        if (userCredentials == null) {
            if (this.userCredentials != null) {
                this.userCredentials.setClientInformation(null);
            }
        } else {
            userCredentials.setClientInformation(this);
        }
        this.userCredentials = userCredentials;
    }
    */
    public void setUserCredentials(UserCredentials userCredentials, boolean update) {
        if (userCredentials == null) {
            if (this.userCredentials != null) {
                if(this.userCredentials.getClientInformation() == this) {
                    this.userCredentials.setClientInformation(null);
                }
            }
        } else {
            if (userCredentials.getClientInformation() != this && update) {
                userCredentials.setClientInformation(this);
            }
        }
        this.userCredentials = userCredentials;
    }
    
}
