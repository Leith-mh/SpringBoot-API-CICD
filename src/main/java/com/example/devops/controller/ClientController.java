package com.example.devops.controller;


import com.example.devops.entity.ClientEntity;
import com.example.devops.service.ClientService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

@RestController
@RequestMapping("/client")
public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public List<ClientEntity> findAllClients(){
        return clientService.findAllClients();
    }
    @GetMapping("/{id}")
    public Optional<ClientEntity> findClientById(@PathVariable("id") long id){
        return clientService.findById(id);
    }
    @PostMapping
    public ClientEntity addClient(@RequestBody ClientEntity clientEntity){
        return clientService.addClient(clientEntity);
    }
    @PutMapping
    public ClientEntity updateClient(@RequestBody ClientEntity clientEntity){
        return clientService.updateClient(clientEntity);
    }
    @DeleteMapping("{id}")
    public void deleteClient(@PathVariable("id")Long id){
        clientService.deleteClient(id);
    }
}
