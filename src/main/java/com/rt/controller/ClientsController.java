package com.rt.controller;

import com.rt.model.Client;
import com.rt.repository.ClientRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController //diz que é uma API
@RequestMapping("/api/clients") //ROTA
public class ClientsController {


    private ClientRepository clientRepository;

    public ClientsController(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @GetMapping
    public List<Client> list(){
        return clientRepository.findAll();
    }
}
