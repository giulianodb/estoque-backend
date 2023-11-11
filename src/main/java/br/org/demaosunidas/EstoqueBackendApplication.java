package br.org.demaosunidas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EstoqueBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(EstoqueBackendApplication.class, args);
	}
	
	   @Bean
	    public ConfigurableServletWebServerFactory webServerFactory() {
	        TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
	        factory.addConnectorCustomizers(connector -> {
	            connector.setUseIPVHosts(true);
//	            connector.setBindOnInit(false);
	            connector.setAttribute("address", "0.0.0.0");
	            connector.setPort(8080); // Porta na qual o servidor escutará
	        });
	        return factory;
	    }
}
