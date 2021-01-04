package br.org.demaosunidas.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.org.demaosunidas.domain.Configuracao;

@RestController
@RequestMapping(value="/configuracoes")
public class ConfiguracaoResource {
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Configuracao> listar() {
		
		Configuracao config = new Configuracao(1,1,2021);
		Configuracao config2 = new Configuracao(2,1,2021);
		
		List<Configuracao> lista = new ArrayList<>();
		lista.add(config);
		lista.add(config2);
		
		return lista;
	}
}
