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

import br.org.demaosunidas.domain.SituacaoIdentificada;
import br.org.demaosunidas.dto.SituacaoIdentificadaDTO;
import br.org.demaosunidas.services.SituacaoIdentificadaService;

@RestController
@RequestMapping(value="/situacao_identificada")
public class SituacaoIdentificadaResource {
	
	@Autowired
	private SituacaoIdentificadaService service;
	
	@RequestMapping(value = "/familia/{idFamilia}", method = RequestMethod.GET)
	@CrossOrigin
	public ResponseEntity<Page<SituacaoIdentificadaDTO>> findPlanoFamiliar (@PathVariable Integer idFamilia,
			@RequestParam(value="page",defaultValue="0") Integer page,
			@RequestParam(value="linesPerPage",defaultValue="24") Integer linesPerPage,
			@RequestParam(value="orderBy",defaultValue="acao") String orderBy,
			@RequestParam(value="direction",defaultValue="ASC") String direction) {
		
		if (page > 0) {
			page = page - 1;
		}
		
		Page<SituacaoIdentificada> lista = service.searchPorFamilia(idFamilia, page, linesPerPage, orderBy, direction);
		
		Page<SituacaoIdentificadaDTO> planoPage = lista.map(obj -> new SituacaoIdentificadaDTO(obj));
		return ResponseEntity.ok().body(planoPage);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody SituacaoIdentificadaDTO dto){
		service.insert(dto);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().
				path("/{id}").buildAndExpand("0").toUri();
		
		return ResponseEntity.created(uri).build();
		
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@PathVariable Integer id,@RequestBody SituacaoIdentificadaDTO dto){
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
