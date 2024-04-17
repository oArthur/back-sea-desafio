package com.rt.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //TODO colocar min e max de length/
    @Column(length = 100, nullable = false)
    @Length(min = 3, max = 100)
    @NotNull
    private String nome;

    @NotNull
    @Column(length = 11, nullable = false)
    private String cpf;

    @NotNull
    @Column(nullable = false)
    private String endereco;

    @NotNull
    @Column(length = 20, nullable = false)
    private String cep;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "client")
    private List<Telefone> telefones = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "client")
    private List<Email> emails = new ArrayList<>();
}
