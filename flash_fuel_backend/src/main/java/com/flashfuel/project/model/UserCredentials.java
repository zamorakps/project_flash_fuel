package com.flashfuel.project.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;

@Entity
@Table(name = "user_credentials")
public class UserCredentials {

    /*
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    */

    @Id
    private Long id;

    @Column(unique = true)
    private String username;

    @Column
    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "client_information_id", referencedColumnName = "id")
    @JsonManagedReference
    private ClientInformation clientInformation;

    public UserCredentials() {}

    public UserCredentials(String username, String password, ClientInformation clientInformation) {
        this.username = username;
        this.password = password;
        this.clientInformation = clientInformation;
    }

    @OneToMany(mappedBy = "user")
    private List<FuelQuote> fuelQuotes;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ClientInformation getClientInformation() {
        return clientInformation;
    }

    public void setClientInformation(ClientInformation clientInformation) {
        if (clientInformation == null) {
            if (this.clientInformation != null) {
                if(this.clientInformation.getUserCredentials() == this) {
                    this.clientInformation.setUserCredentials(null, false);
                }
            }
        } else {
            if (clientInformation.getUserCredentials() != this) {
                clientInformation.setUserCredentials(this, false);
            }
        }
        this.clientInformation = clientInformation;
    }
}