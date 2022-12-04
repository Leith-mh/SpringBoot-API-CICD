package com.example.devops.service;

import com.example.devops.entity.ClientEntity;

import java.util.List;
import java.util.Optional;

public interface ClientService {
    List<ClientEntity> findAllClients();
    Optional<ClientEntity> findById(Long id);
    ClientEntity addClient(ClientEntity client);
    ClientEntity updateClient(ClientEntity client);
    void deleteClient(Long id);

}
