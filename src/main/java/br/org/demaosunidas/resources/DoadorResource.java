package br.org.demaosunidas.resources;

import java.net.URI;

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

import br.org.demaosunidas.domain.Doador;
import br.org.demaosunidas.services.DoadorService;

@RestController
@RequestMapping(value="/doadores")
public class DoadorResource {
	
	@Autowired
	private DoadorService service;
	
	@RequestMapping(method = RequestMethod.GET)
	@CrossOrigin
	@PreAuthorize( "hasAnyRole('ROLE_Administrador','ROLE_Estoque')")
	public ResponseEntity<Page<Doador>> findPage (
			@RequestParam(value="page",defaultValue="0") Integer page,
			@RequestParam(value="linesPerPage",defaultValue="200") Integer linesPerPage,
			@RequestParam(value="orderBy",defaultValue="nome") String orderBy,
			@RequestParam(value="direction",defaultValue="ASC") String direction,
			@RequestParam(value="nome",required = false) String nome) {
		
		if (page > 0) {
			page = page - 1;
		}
		
		Page<Doador> lista = service.search(nome,page,linesPerPage,orderBy,direction);
		
		
		return ResponseEntity.ok().body(lista);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@PreAuthorize( "hasAnyRole('ROLE_Administrador','ROLE_Estoque')")
	public ResponseEntity<Void> insert(@RequestBody Doador doador){
		service.insert(doador);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().
				path("/{id}").buildAndExpand(doador.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
		
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	@PreAuthorize( "hasAnyRole('ROLE_Administrador','ROLE_Estoque')")
	public ResponseEntity<Void> update(@PathVariable Integer id,@RequestBody Doador doador){
		doador.setId(id);
		service.update(doador);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().
				path("/{id}").buildAndExpand(doador.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
		
	}
	
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@PreAuthorize( "hasAnyRole('ROLE_Administrador','ROLE_Estoque')")
	@CrossOrigin
	public ResponseEntity<Doador> findById(@PathVariable Integer id){
			
		Doador obj = service.findById(id);
		
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@PreAuthorize( "hasAnyRole('ROLE_Administrador','ROLE_Estoque')")
	@CrossOrigin
	public ResponseEntity<Doador> Excluir(@PathVariable Integer id){
			
		service.deletar(id);
		
		return ResponseEntity.noContent().build() ;
	}
	
	
	
}
