package com.rt;

import com.rt.model.Client;
import com.rt.repository.ClientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Collections;

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
			c.setCpf("2121212");
			c.setEndereco("adsadasdasdasd");
			c.setTelefone("61982726392");
			c.setEmails("arthur@gmail.com");
			clientRepository.save(c);
		};
	}
}
