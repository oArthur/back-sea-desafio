package com.rt.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Client {
    @Id //Primary KEy
    @GeneratedValue(strategy = GenerationType.AUTO) //auto increment
    private Long id;

    @Column(length = 100, nullable = false)
    private String nome;

    @Column(length = 11, nullable = false)
    private String cpf;

    @Column(nullable = false)
    private String endereco;

    private String emails;
    private String telefone;
}
