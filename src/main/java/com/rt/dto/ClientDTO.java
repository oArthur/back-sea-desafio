package com.rt.dto;

import com.rt.model.Email;
import java.util.List;

public record ClientDTO(Long id,
                        String nome,
                        String cpf,
                        String endereco,
                        String cep,
                        List<TelefoneDTO> telefones,
                        List<EmailDTO> emails) { }
