package com.rt.dto.mapper;


import com.rt.dto.ClientDTO;
import com.rt.dto.EmailDTO;
import com.rt.dto.TelefoneDTO;
import com.rt.model.Client;
import com.rt.model.Email;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ClientMapper {

    public ClientDTO toDTO(Client client) {
        if (client == null) {
            return null;
        }
        List<TelefoneDTO> telefones = client.getTelefones()
                .stream()
                .map(telefone -> new TelefoneDTO(telefone.getId(), telefone.getNumero(),telefone.getTipo()))
                .collect(Collectors.toList());

        List<EmailDTO> emails = client.getEmails()
                .stream()
                .map(email -> new EmailDTO(email.getId(),email.getEmail()))
                .collect(Collectors.toList());

        return new ClientDTO(client.getId(), client.getNome(),
                client.getCpf(), client.getEndereco(), client.getCep(), telefones, emails);
    }

    public Client toEntity(ClientDTO dto) {

        if (dto == null) {
            return null;
        }
        Client client = new Client();

        if (dto.id() != null){
            client.setId(dto.id());
        }
        client.setNome(dto.nome());
        client.setCpf(dto.cpf());
        client.setEndereco(dto.endereco());
        client.setCep(dto.cep());

        return client;
    }
}
