package com.rt;

import com.rt.model.Client;
import com.rt.repository.ClientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DesafioSeaApplication {

	public static void main(String[] args) {
		SpringApplication.run(DesafioSeaApplication.class, args);
	}

	@Bean
	CommandLineRunner initDataBase(ClientRepository clientRepository){
		return args -> {
			clientRepository.deleteAll();

			Client c = new Client();
			c.setNome("Arthur");
			c.setCpf("04929782191");
			c.setEndereco("Guara");
			c.setTelefone("61982726392");
			c.setTipo("Celular");
			c.setCep("71071000");
			c.setEmail("arthur@gmail.com");
			clientRepository.save(c);
		};
	}
}
