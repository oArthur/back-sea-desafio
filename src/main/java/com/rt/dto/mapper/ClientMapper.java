package com.rt.dto.mapper;


import com.rt.dto.ClientDTO;
import com.rt.dto.EmailDTO;
import com.rt.dto.TelefoneDTO;
import com.rt.model.Client;
import com.rt.model.Email;
import com.rt.model.Telefone;
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

    public Client toEntity(ClientDTO clientDTO) {

        if (clientDTO == null) {
            return null;
        }
        Client client = new Client();

        if (clientDTO.id() != null){
            client.setId(clientDTO.id());
        }
        client.setNome(clientDTO.nome());
        client.setCpf(clientDTO.cpf());
        client.setEndereco(clientDTO.endereco());
        client.setCep(clientDTO.cep());

        List<Telefone> telefones = clientDTO.telefones().stream().map(telefoneDTO -> {
            var telefone = new Telefone();
            telefone.setId(telefoneDTO.id());
            telefone.setNumero(telefoneDTO.numero());
            telefone.setTipo(telefoneDTO.tipo());
            telefone.setClient(client);
            return telefone;
        }).collect(Collectors.toList());
        client.setTelefones(telefones);

        List<Email> emails = clientDTO.emails().stream().map(emailDTO -> {
            var email = new Email();
            email.setId(emailDTO.id());
            email.setEmail(emailDTO.email());
            email.setClient(client);
            return email;

        }).collect(Collectors.toList());
        client.setEmails(emails);

        return client;
    }
}
