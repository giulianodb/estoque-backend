package br.org.demaosunidas.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.org.demaosunidas.domain.CentroCusto;
import br.org.demaosunidas.dto.CentroCustoDTO;
import br.org.demaosunidas.dto.ListaCategoriasDTO;
import br.org.demaosunidas.services.CategoriaService;
import br.org.demaosunidas.services.CentroCustoService;

@RestController
@RequestMapping(value="/transacaocategoria")
public class CategoriaResource {
	
	@Autowired
	private CategoriaService service;
	
	@RequestMapping(method = RequestMethod.GET)
	@CrossOrigin
	@PreAuthorize( "hasAnyRole('ROLE_Administrador','ROLE_Financeiro')")
	public ResponseEntity<ListaCategoriasDTO> buscarListasCategorias () {
		
		
		return ResponseEntity.ok().body(service.obterCategorias());
	}
	
	
	
	
}
