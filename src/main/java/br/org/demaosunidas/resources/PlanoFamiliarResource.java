package br.org.demaosunidas.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.org.demaosunidas.domain.PlanoFamiliar;
import br.org.demaosunidas.dto.PlanoFamiliarDTO;
import br.org.demaosunidas.services.PlanoFamiliarService;

@RestController
@RequestMapping(value="/plano_acao_familiar")
public class PlanoFamiliarResource {
	
	@Autowired
	private PlanoFamiliarService service;
	
	@RequestMapping(value = "/familia/{idFamilia}", method = RequestMethod.GET)
	@CrossOrigin
	public ResponseEntity<Page<PlanoFamiliarDTO>> findPlanoFamiliar (@PathVariable Integer idFamilia,
			@RequestParam(value="page",defaultValue="0") Integer page,
			@RequestParam(value="linesPerPage",defaultValue="24") Integer linesPerPage,
			@RequestParam(value="orderBy",defaultValue="nome") String orderBy,
			@RequestParam(value="direction",defaultValue="ASC") String direction) {
		
		if (page > 0) {
			page = page - 1;
		}
		
		Page<PlanoFamiliar> lista = service.searchPorFamilia(idFamilia, page, linesPerPage, orderBy, direction);
		
		Page<PlanoFamiliarDTO> planoPage = lista.map(obj -> new PlanoFamiliarDTO(obj));
		return ResponseEntity.ok().body(planoPage);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody PlanoFamiliarDTO dto){
		service.insert(dto);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().
				path("/{id}").buildAndExpand("0").toUri();
		
		return ResponseEntity.created(uri).build();
		
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@PathVariable Integer id,@RequestBody PlanoFamiliarDTO dto){
		dto.setId(id);
		service.update(dto);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().
				path("/{id}").buildAndExpand(dto.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
		
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@CrossOrigin
	public ResponseEntity<Void> Excluir(@PathVariable Integer id){
			
		service.delete(id);
		
		return ResponseEntity.noContent().build() ;
	}
	
	
}
