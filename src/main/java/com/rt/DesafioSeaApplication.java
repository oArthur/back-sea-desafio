package com.rt;

import com.rt.model.Client;
import com.rt.model.Email;
import com.rt.model.Telefone;
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
			c.setCep("71071000");

			Telefone t = new Telefone();
			t.setTipo("Celular");
			t.setNumero("61982726392");
			t.setClient(c);
			c.getTelefones().add(t);

			Telefone t1 = new Telefone();
			t1.setTipo("Celular");
			t1.setNumero("55619809752");
			t1.setClient(c);
			c.getTelefones().add(t1);

			Email e = new Email();
			e.setEmail("arthur@gmail.com");
			e.setClient(c);
			c.getEmails().add(e);

			Email e1 = new Email();
			e1.setEmail("arthur@hotmail.com");
			e1.setClient(c);
			c.getEmails().add(e1);
			clientRepository.save(c);
		};
	}
}
