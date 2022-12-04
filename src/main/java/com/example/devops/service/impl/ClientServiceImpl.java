package com.example.devops.service.impl;

import com.example.devops.entity.ClientEntity;
import com.example.devops.repository.ClientRepository;
import com.example.devops.service.ClientService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public List<ClientEntity> findAllClients() {
        return clientRepository.findAll();
    }

    @Override
    public Optional<ClientEntity> findById(Long id) {
        return clientRepository.findById(id);
    }

    @Override
    public ClientEntity addClient(ClientEntity client) {
        return clientRepository.save(client);
    }

    @Override
    public ClientEntity updateClient(ClientEntity client) {
        return clientRepository.save(client);
    }

    @Override
    public void deleteClient(Long id) {
        clientRepository.deleteById(id);

    }
}
