package com.rt.controller;

import com.rt.dto.ClientDTO;
import com.rt.dto.mapper.ClientMapper;
import com.rt.exception.RecordNotFoundException;
import com.rt.model.Client;
import com.rt.repository.ClientRepository;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Validated
@RestController
@RequestMapping("/api/clients")
public class ClientsController {


    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    public ClientsController(ClientRepository clientRepository, ClientMapper clientMapper) {
        this.clientRepository = clientRepository;
        this.clientMapper = clientMapper;
    }

    @GetMapping
    public List<ClientDTO> list(){
        return clientRepository.findAll()
                .stream()
                .map(clientMapper::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDTO> findById(@PathVariable @NotNull @Positive Long id){
        return clientRepository.findById(id).map(clientMapper::toDTO)
                .map(res -> ResponseEntity.ok().body(res))
                .orElse(ResponseEntity.notFound().build());
    }//Voltar aqui depois

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public ClientDTO create(@RequestBody ClientDTO client){ //pega a reposta e mapeia com o Client

        return clientMapper.toDTO(clientRepository.save(clientMapper.toEntity(client)));
    }

    //TODO voltar aqui para possiveis correcoes.
    @PutMapping("/{id}")
    public ClientDTO update(@PathVariable @NotNull @Positive Long id, @RequestBody ClientDTO clientDTO){
        return clientRepository.findById(id)
                .map(res -> {
                    Client client = clientMapper.toEntity(clientDTO);
                    res.setNome(clientDTO.nome());
                    res.setCpf(clientDTO.cpf());
                    res.setEndereco(clientDTO.endereco());
                    res.setCep(clientDTO.cep());

                    res.getTelefones().clear();
                    res.getTelefones().addAll(client.getTelefones());

                    // Atualizando a coleção de emails
                    res.getEmails().clear();
                    res.getEmails().addAll(client.getEmails());


                    return clientMapper.toDTO(clientRepository.save(res));
                })
                .orElseThrow(() -> new RecordNotFoundException(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable @NotNull @Positive Long id){
        return clientRepository.findById(id)
                .map(res -> {
                    clientRepository.deleteById(id);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElseThrow(() -> new RecordNotFoundException(id));
    }
}
