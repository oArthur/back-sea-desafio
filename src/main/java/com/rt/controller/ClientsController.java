package com.rt.controller;

import com.rt.model.Client;
import com.rt.repository.ClientRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}")
    public ResponseEntity<Client> findById(@PathVariable Long id){
        return clientRepository.findById(id)
                .map(res -> ResponseEntity.ok().body(res))
                .orElse(ResponseEntity.notFound().build());
    }//Voltar aqui depois

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Client create(@RequestBody Client client){ //pega a reposta e mapeia com o Client

        return clientRepository.save(client);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> update(@PathVariable Long id, @RequestBody Client client){
        return clientRepository.findById(id)
                .map(res -> {
                    res.setNome(client.getNome());
                    res.setCpf(client.getCpf());
                    res.setTelefone(client.getTelefone());
                    res.setTipo(client.getTipo());
                    res.setEmail(client.getEmail());
                    res.setEndereco(client.getEndereco());
                    res.setCep(client.getCep());
                    Client updatedClient = clientRepository.save(res);

                    return ResponseEntity.ok().body(updatedClient);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        return clientRepository.findById(id)
                .map(res -> {
                    clientRepository.deleteById(id);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
