package br.org.demaosunidas.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.org.demaosunidas.dto.ParceiroDTO;
import br.org.demaosunidas.services.ParceiroService;

@RestController
@RequestMapping(value="/parceiros")
public class ParceiroResource {
	
	@Autowired
	private ParceiroService service;
	
	@RequestMapping(method = RequestMethod.GET)
	@CrossOrigin
	public ResponseEntity<ParceiroDTO> find() {
		
		return ResponseEntity.ok(service.obterParceiros());
		
	}
	
}
