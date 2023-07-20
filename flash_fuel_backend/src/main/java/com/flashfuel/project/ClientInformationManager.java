package com.flashfuel.project;

import com.flashfuel.project.model.ClientInformation;
import com.flashfuel.project.model.UserCredentials;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class ClientInformationManager {
    private final ClientInformationRepository clientInformationRepository;

    public ClientInformationManager(ClientInformationRepository clientInformationRepository) {
        this.clientInformationRepository = clientInformationRepository;
    }

    public ClientInformation getClientInformationByUserId(Long userId) {
        return clientInformationRepository.findById(userId).orElse(null);
    }

    public void updateClientInformation(Long userId, ClientInformation clientInformation) {
        clientInformationRepository.save(clientInformation);
    }
}

/*
@Component
public class ClientInformationManager {
    private Map<Long, ClientInformation> clientInfo;
    private final UserManager userManager; 
    private AtomicLong clientIdGenerator = new AtomicLong(0);

    public ClientInformationManager(UserManager userManager) {
        this.userManager = userManager;
        clientInfo = new ConcurrentHashMap<>();
    }

    public long generateNewClientId() {
        return clientIdGenerator.incrementAndGet();
    }

    public ClientInformation getClientInformationByUserId(Long userId) {
        return clientInfo.get(userId);
    }

public void updateClientInformation(Long userId, ClientInformation clientInformation) {
    synchronized (this) {
        ClientInformation currentInfo = clientInfo.get(userId);
        if (currentInfo != null) {
            clientInformation.setId(currentInfo.getId());
        } else {
            long newClientId = generateNewClientId();
            clientInformation.setId(newClientId);
        }
        clientInfo.put(userId, clientInformation);
        
        // Fetch the user
        UserCredentials user = userManager.getUserById(userId);
        // Set the updated client information to the user
        user.setClientInformation(clientInformation);
    }
}
}
*/
