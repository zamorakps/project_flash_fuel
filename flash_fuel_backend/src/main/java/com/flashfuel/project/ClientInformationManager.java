package com.flashfuel.project;

import com.flashfuel.project.model.ClientInformation;
import com.flashfuel.project.model.UserCredentials;

import org.springframework.stereotype.Component;

@Component
public class ClientInformationManager {
    private final ClientInformationRepository clientInformationRepository;
    private final UserCredentialsRepository userCredentialsRepository;

    public ClientInformationManager(ClientInformationRepository clientInformationRepository,
                                    UserCredentialsRepository userCredentialsRepository) {
        this.clientInformationRepository = clientInformationRepository;
        this.userCredentialsRepository = userCredentialsRepository;
    }

    public ClientInformation getClientInformationByUserId(Long userId) {
        var user = userCredentialsRepository.findById(userId);

        return user.map(UserCredentials::getClientInformation).orElse(null);

    }

    public void updateClientInformation(Long userId, ClientInformation clientInformation) {
        clientInformationRepository.save(clientInformation);
    }
}