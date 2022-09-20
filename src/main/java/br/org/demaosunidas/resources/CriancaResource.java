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
import br.org.demaosunidas.domain.Crianca;
import br.org.demaosunidas.dto.AvaliacaoContextoDTO;
import br.org.demaosunidas.services.CriancaService;

@RestController
@RequestMapping(value="/criancas")
public class CriancaResource {
	
	@Autowired
	private CriancaService service;
	
	@RequestMapping(method = RequestMethod.GET)
	@CrossOrigin
	public ResponseEntity<Page<Crianca>> findPage (
			@RequestParam(value="page",defaultValue="0") Integer page,
			@RequestParam(value="linesPerPage",defaultValue="200") Integer linesPerPage,
			@RequestParam(value="orderBy",defaultValue="nome") String orderBy,
			@RequestParam(value="direction",defaultValue="ASC") String direction,
			@RequestParam(value="nome",required = false) String nome) {
		
		
		if (page > 0) {
			page = page - 1;
		}
		Page<Crianca> lista = service.search(nome,page,linesPerPage,orderBy,direction);
		
		return ResponseEntity.ok().body(lista);
	}
	
	
	@RequestMapping(value="/familia/{idFamilia}", method = RequestMethod.GET)
	@CrossOrigin
	public ResponseEntity<Page<Crianca>> buscarCriancaFamilia (
			@RequestParam(value="page",defaultValue="0") Integer page,
			@RequestParam(value="linesPerPage",defaultValue="200") Integer linesPerPage,
			@RequestParam(value="orderBy",defaultValue="nome") String orderBy,
			@RequestParam(value="direction",defaultValue="ASC") String direction,
			@PathVariable Integer idFamilia) {
		
		
		if (page > 0) {
			page = page - 1;
		}
		Page<Crianca> lista = service.buscarPorFamilia(idFamilia,page,linesPerPage,orderBy,direction);
		
		return ResponseEntity.ok().body(lista);
	}
	
	
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody Crianca crianca){
		service.insert(crianca);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().
				path("/{id}").buildAndExpand(crianca.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
		
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@PathVariable Integer id,@RequestBody Crianca crianca){
		crianca.setId(id);
		service.update(crianca);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().
				path("/{id}").buildAndExpand(crianca.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
		
	}
	
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@CrossOrigin
	public ResponseEntity<Crianca> findById(@PathVariable Integer id){
			
		Crianca obj = service.findById(id);
		
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@CrossOrigin
	public ResponseEntity<Crianca> Excluir(@PathVariable Integer id){
			
		service.deletar(id);
		
		return ResponseEntity.noContent().build() ;
	}
	
	
}
