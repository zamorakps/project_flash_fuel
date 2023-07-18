package com.flashfuel.project.model;

public class UserCredentialsDTO {

    private Long id;
    private String username;
    private ClientInformationDTO clientInformation;

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

    public ClientInformationDTO getClientInformation() {
        return clientInformation;
    }

    public void setClientInformation(ClientInformationDTO clientInformation) {
        this.clientInformation = clientInformation;
    }
}
