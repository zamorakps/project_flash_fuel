package com.flashfuel.project;

import com.flashfuel.project.model.ClientInformation;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class ClientInformationManager {
    private Map<Long, ClientInformation> clientInfo;

    public ClientInformationManager() {
        clientInfo = new ConcurrentHashMap<>();
    }

    public ClientInformation getClientInformationByUserId(Long userId) {
        return clientInfo.get(userId);
    }

    public void addClientInformation(Long userId, ClientInformation clientInformation) {
        synchronized (this) {
            clientInfo.put(userId, clientInformation);
        }
    }
}

