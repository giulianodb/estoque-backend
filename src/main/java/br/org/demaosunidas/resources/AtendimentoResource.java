package br.org.demaosunidas.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.org.demaosunidas.dto.AtendimentoDTO;
import br.org.demaosunidas.services.AtendimentoService;

@RestController
@RequestMapping(value="/atendimento")
public class AtendimentoResource {
	
	@Autowired
	private AtendimentoService service;
	
	@RequestMapping(value = "/crianca/{idCrianca}", method = RequestMethod.GET)
	@CrossOrigin
	public ResponseEntity<List<AtendimentoDTO>> findAtendmento (@PathVariable Integer idCrianca) {
		List<AtendimentoDTO> lista = service.searchPorCrianca(idCrianca);
		return ResponseEntity.ok().body(lista);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody AtendimentoDTO atendimento){
		service.insert(atendimento);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().
				path("/{id}").buildAndExpand("0").toUri();
		
		return ResponseEntity.created(uri).build();
		
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@PathVariable Integer id,@RequestBody AtendimentoDTO atendimentoDTO){
		atendimentoDTO.setId(id);
		service.update(atendimentoDTO);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().
				path("/{id}").buildAndExpand(atendimentoDTO.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
		
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@CrossOrigin
	public ResponseEntity<Void> Excluir(@PathVariable Integer id){
			
		service.delete(id);
		
		return ResponseEntity.noContent().build() ;
	}
	
	
}
