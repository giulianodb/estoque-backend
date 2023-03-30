package br.org.demaosunidas.resources;

import java.net.URI;
import java.util.ArrayList;
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

import br.org.demaosunidas.domain.Conta;
import br.org.demaosunidas.domain.Doador;
import br.org.demaosunidas.dto.ContaDTO;
import br.org.demaosunidas.services.ContaService;
import br.org.demaosunidas.services.DoadorService;

@RestController
@RequestMapping(value="/contas")
public class ContaResource {
	
	@Autowired
	private ContaService service;
	
	@RequestMapping(method = RequestMethod.GET)
	@CrossOrigin
//	@PreAuthorize( "hasAnyRole('ROLE_Administrador','ROLE_Estoque')")
	public ResponseEntity<List<ContaDTO> > findAll () {
		List<ContaDTO> listaDTO = new ArrayList<>();
		
		for (Conta x : service.listar()) {
			listaDTO.add(new ContaDTO(x));
		}
		
		return ResponseEntity.ok().body(listaDTO);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	//@PreAuthorize( "hasAnyRole('ROLE_Administrador','ROLE_Estoque')")
	public ResponseEntity<Void> insert(@RequestBody ContaDTO conta){
		service.insert(new Conta(conta));
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().
				path("/{id}").buildAndExpand(conta.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
		
	}
	
//	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
//	@PreAuthorize( "hasAnyRole('ROLE_Administrador','ROLE_Estoque')")
//	public ResponseEntity<Void> update(@PathVariable Integer id,@RequestBody Doador doador){
//		doador.setId(id);
//		service.update(doador);
//		
//		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().
//				path("/{id}").buildAndExpand(doador.getId()).toUri();
//		
//		return ResponseEntity.created(uri).build();
//		
//	}
//	
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@PreAuthorize( "hasAnyRole('ROLE_Administrador','ROLE_Estoque')")
	@CrossOrigin
	public ResponseEntity<ContaDTO> findById(@PathVariable Integer id){
			
		ContaDTO obj = new ContaDTO(service.findById(id));
		
		return ResponseEntity.ok().body(obj);
	}
	
//	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
//	@PreAuthorize( "hasAnyRole('ROLE_Administrador','ROLE_Estoque')")
//	@CrossOrigin
//	public ResponseEntity<Doador> Excluir(@PathVariable Integer id){
//			
//		service.deletar(id);
//		
//		return ResponseEntity.noContent().build() ;
//	}
//	
	
	
}
