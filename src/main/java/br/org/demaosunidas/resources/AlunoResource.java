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

import br.org.demaosunidas.domain.Aluno;
import br.org.demaosunidas.services.AlunoService;

@RestController
@RequestMapping(value="/alunos")
public class AlunoResource {
	
	@Autowired
	private AlunoService service;
	
	@RequestMapping(method = RequestMethod.GET)
	@CrossOrigin
	public ResponseEntity<Page<Aluno>> findPage (
			@RequestParam(value="page",defaultValue="0") Integer page,
			@RequestParam(value="linesPerPage",defaultValue="200") Integer linesPerPage,
			@RequestParam(value="orderBy",defaultValue="nome") String orderBy,
			@RequestParam(value="direction",defaultValue="ASC") String direction,
			@RequestParam(value="nome",required = false) String nome) {
		
		
		if (page > 0) {
			page = page - 1;
		}
		Page<Aluno> lista = service.search(nome,page,linesPerPage,orderBy,direction);
		
		return ResponseEntity.ok().body(lista);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody Aluno aluno){
		service.insert(aluno);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().
				path("/{id}").buildAndExpand(aluno.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
		
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@PathVariable Integer id,@RequestBody Aluno aluno){
		aluno.setId(id);
		service.update(aluno);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().
				path("/{id}").buildAndExpand(aluno.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
		
	}
	
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@CrossOrigin
	public ResponseEntity<Aluno> findById(@PathVariable Integer id){
			
		Aluno obj = service.findById(id);
		
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@CrossOrigin
	public ResponseEntity<Aluno> Excluir(@PathVariable Integer id){
			
		service.deletar(id);
		
		return ResponseEntity.noContent().build() ;
	}
	
	
	
}
