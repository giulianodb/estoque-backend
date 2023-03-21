package br.org.demaosunidas.resources;

import java.net.URI;
import java.security.Principal;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.realm.GenericPrincipal;
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

import br.org.demaosunidas.domain.Familia;
import br.org.demaosunidas.domain.MembroFamilia;
import br.org.demaosunidas.services.FamiliaMembroService;
import br.org.demaosunidas.services.FamiliaService;

@RestController
@RequestMapping(value="/familias")
public class FamiliaResource {
	
	@Autowired
	private FamiliaService service;
	
	
	@Autowired
	private FamiliaMembroService serviceMembro;
	
//	@PreAuthorize("hasAnyRole('ROLE_ADM')")
//	@RolesAllowed("Administrador")
	@PreAuthorize( "hasAnyRole('ROLE_Administrador','ROLE_AssistenteSocial')")
	@RequestMapping(method = RequestMethod.GET)
//	@CrossOrigin
	public ResponseEntity<Page<Familia>> findPage (
			@RequestParam(value="page",defaultValue="0") Integer page,
			@RequestParam(value="linesPerPage",defaultValue="200") Integer linesPerPage,
			@RequestParam(value="orderBy",defaultValue="nomeResponsavel") String orderBy,
			@RequestParam(value="direction",defaultValue="ASC") String direction,
			@RequestParam(value="nome",required = false) String nome, HttpServletRequest request) {
		
		if (page > 0) {
			page = page -1;
		}
		else {
			page = 0;
		}
		Page<Familia> lista = service.search(nome,page,linesPerPage,orderBy,direction);
		
		
		return ResponseEntity.ok().body(lista);
	}
	
	@PreAuthorize( "hasAnyRole('ROLE_Administrador','ROLE_AssistenteSocial')")
	@CrossOrigin
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody Familia familia, HttpServletRequest request){
		 if (request.isUserInRole("ADM")) {
		      System.out.println("ESTA");
		    }
		service.insert(familia);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().
				path("/{id}").buildAndExpand(familia.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
		
	}
	
	//@CrossOrigin(origins = "*")
	@PreAuthorize( "hasAnyRole('ROLE_Administrador','ROLE_AssistenteSocial')")
	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@PathVariable Integer id,@RequestBody Familia familia){
		familia.setId(id);
		Familia banco = service.update(familia);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().
				path("/{id}").buildAndExpand(familia.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
		
	}
//	
//	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
//	public ResponseEntity<Void> delete(@PathVariable Integer id){
//
//		serviceMembro.delete(id);
//		
//		return ResponseEntity.ok().build();
//		
//	}
	
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@CrossOrigin
	@PreAuthorize( "hasAnyRole('ROLE_Administrador','ROLE_AssistenteSocial')")
	public ResponseEntity<Familia> findById(@PathVariable Integer id){
			
		Familia obj = service.findById(id);
		
		return ResponseEntity.ok().body(obj);
	}
	

	@RequestMapping(value = "/cpf/{cpf}", method = RequestMethod.GET)
	@CrossOrigin
	@PreAuthorize( "hasAnyRole('ROLE_Administrador','ROLE_AssistenteSocial')")
	public ResponseEntity<Familia> findCpf(@PathVariable String cpf){
			
		Familia obj = service.findByCpfResponsavel(cpf);
		if (obj == null ) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@CrossOrigin
	@PreAuthorize( "hasAnyRole('ROLE_Administrador','ROLE_AssistenteSocial')")
	public ResponseEntity<Familia> Excluir(@PathVariable Integer id){
			
		service.deletar(id);
		
		return ResponseEntity.noContent().build() ;
	}
	
	
	
}
