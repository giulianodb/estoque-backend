package br.org.demaosunidas.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.org.demaosunidas.domain.Usuario;
import br.org.demaosunidas.dto.UsuarioNewDTO;
import br.org.demaosunidas.services.UsuarioService;

@RestController
@RequestMapping(value="/usuarios")
public class UsuarioResource {
	
	@Autowired
	private UsuarioService service;
	
	@RequestMapping(method=RequestMethod.POST)
//	@PreAuthorize( "hasAnyRole('ROLE_Administrador')")
	public ResponseEntity<Void> insert(@RequestBody UsuarioNewDTO obj){
		
		service.insert(service.fromDTO(obj));
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().
				path("/{id}").buildAndExpand(obj.getIdUsuario()).toUri();
		
		return ResponseEntity.created(uri).build();
		
	}
	
//	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
//	public ResponseEntity<Void> update(@PathVariable Integer id,@RequestBody Usuario obj){
//		obj.setId(id);
//		service.update(obj);
//		
//		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().
//				path("/{id}").buildAndExpand(aluno.getId()).toUri();
//		
//		return ResponseEntity.created(uri).build();
//		
//	}
	
//	
//	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
//	@CrossOrigin
//	public ResponseEntity<Aluno> findById(@PathVariable Integer id){
//			
//		Aluno obj = service.findById(id);
//		
//		return ResponseEntity.ok().body(obj);
//	}
//	
	
//	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
//	@CrossOrigin
//	public ResponseEntity<Aluno> Excluir(@PathVariable Integer id){
//			
//		service.deletar(id);
//		
//		return ResponseEntity.noContent().build() ;
//	}
//	
//	
	
}
