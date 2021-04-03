package br.org.demaosunidas.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.org.demaosunidas.domain.Instituicao;
import br.org.demaosunidas.services.InstituicaoService;

@RestController
@RequestMapping(value="/instituicoes")
public class InstituicaoResource {
	
	@Autowired
	private InstituicaoService service;
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Instituicao> listar() {
		
		List<Instituicao> lista = service.listar();
		
		return lista;
	}
}
