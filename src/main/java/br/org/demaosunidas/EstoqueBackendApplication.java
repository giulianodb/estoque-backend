package br.org.demaosunidas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class EstoqueBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(EstoqueBackendApplication.class, args);
	}
	
	@Bean public BCryptPasswordEncoder bCryptPasswordEncoder() {
	    return new BCryptPasswordEncoder(); 
	}

}
