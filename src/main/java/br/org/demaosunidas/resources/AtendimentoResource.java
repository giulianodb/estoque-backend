package br.org.demaosunidas.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.org.demaosunidas.domain.Atendimento;
import br.org.demaosunidas.dto.AtendimentoDTO;
import br.org.demaosunidas.services.AtendimentoService;

@RestController
@RequestMapping(value="/atendimento")
public class AtendimentoResource {
	
	@Autowired
	private AtendimentoService service;
	
	@RequestMapping(value = "/crianca/{idCrianca}", method = RequestMethod.GET)
	@CrossOrigin
	@PreAuthorize( "hasAnyRole('ROLE_Administrador','ROLE_AssistenteSocial')")
	public ResponseEntity<Page<AtendimentoDTO>> findAtendmento (@PathVariable Integer idCrianca,
			@RequestParam(value="page",defaultValue="0") Integer page,
			@RequestParam(value="linesPerPage",defaultValue="24") Integer linesPerPage,
			@RequestParam(value="orderBy",defaultValue="dataAtendimento") String orderBy,
			@RequestParam(value="direction",defaultValue="ASC") String direction) {
		
		if (page > 0) {
			page = page - 1;
		}
		
		Page<Atendimento> lista = service.searchPorCrianca(idCrianca,page, linesPerPage, orderBy, direction);
		
		Page<AtendimentoDTO> atendimentoPage = lista.map(obj -> new AtendimentoDTO(obj));
		return ResponseEntity.ok().body(atendimentoPage);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@PreAuthorize( "hasAnyRole('ROLE_Administrador','ROLE_AssistenteSocial')")
	public ResponseEntity<Void> insert(@RequestBody AtendimentoDTO atendimento){
		service.insert(atendimento);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().
				path("/{id}").buildAndExpand("0").toUri();
		
		return ResponseEntity.created(uri).build();
		
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	@PreAuthorize( "hasAnyRole('ROLE_Administrador','ROLE_AssistenteSocial')")
	public ResponseEntity<Void> update(@PathVariable Integer id,@RequestBody AtendimentoDTO atendimentoDTO){
		atendimentoDTO.setId(id);
		service.update(atendimentoDTO);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().
				path("/{id}").buildAndExpand(atendimentoDTO.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
		
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@PreAuthorize( "hasAnyRole('ROLE_Administrador','ROLE_AssistenteSocial')")
	@CrossOrigin
	public ResponseEntity<Void> Excluir(@PathVariable Integer id){
			
		service.delete(id);
		
		return ResponseEntity.noContent().build() ;
	}
	
	
}
