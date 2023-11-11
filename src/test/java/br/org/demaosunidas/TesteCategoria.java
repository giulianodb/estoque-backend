package br.org.demaosunidas;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.org.demaosunidas.services.CategoriaService;
import br.org.demaosunidas.services.TransacaoService;

@SpringBootTest
public class TesteCategoria {
	
	
	@Autowired
	private CategoriaService service;
	
	private TransacaoService ts;
	
	@Test
	public void teste1() {
		try {
			ts.relatorioRazaoTeste(null, null);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
