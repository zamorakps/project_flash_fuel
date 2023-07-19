package com.flashfuel.project;

import com.flashfuel.project.model.ClientInformation;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class ClientInformationManager {
    private Map<Long, ClientInformation> clientInfo;
    private AtomicLong clientIdGenerator = new AtomicLong(0);

    public ClientInformationManager() {
        clientInfo = new ConcurrentHashMap<>();
    }

    public long generateNewClientId() {
        return clientIdGenerator.incrementAndGet();
    }

    public ClientInformation getClientInformationByUserId(Long userId) {
        return clientInfo.get(userId);
    }

    public void addClientInformation(Long userId, ClientInformation clientInformation) {
        synchronized (this) {
            long newClientId = generateNewClientId();
            clientInformation.setId(newClientId);
            clientInfo.put(userId, clientInformation);
        }
    }
}

