package dev.kush.oauth2authserver.services.impl;

import dev.kush.oauth2authserver.mappers.ClientMapper;
import dev.kush.oauth2authserver.models.client.Client;
import dev.kush.oauth2authserver.repos.client.ClientRepository;
import dev.kush.oauth2authserver.services.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    private final ClientMapper clientMapper;

    @Override
    public void save(RegisteredClient registeredClient) {
        // save client to db
        Client c = clientMapper.mapRegisteredClientToClient(registeredClient);
        clientRepository.save(c);
    }

    @Override
    public RegisteredClient findById(String id) {
        return clientRepository.findById(Integer.parseInt(id))
                .map(clientMapper::mapClientToRegisteredClient)
                .orElseThrow(() -> new RuntimeException("Client not found"));
    }

    @Override
    public RegisteredClient findByClientId(String clientId) {
        return clientRepository.findByClientId(clientId)
                .map(clientMapper::mapClientToRegisteredClient)
                .orElseThrow(() -> new RuntimeException("Client not found"));
    }
}
