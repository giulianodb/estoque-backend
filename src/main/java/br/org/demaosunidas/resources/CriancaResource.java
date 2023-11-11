package br.org.demaosunidas.resources;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

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

import br.org.demaosunidas.domain.Aluno;
import br.org.demaosunidas.domain.Crianca;
import br.org.demaosunidas.domain.enums.ProjetoEnum;
import br.org.demaosunidas.dto.AvaliacaoContextoDTO;
import br.org.demaosunidas.dto.CriancaGetDTO;
import br.org.demaosunidas.dto.ProdutoGetDTO;
import br.org.demaosunidas.services.CriancaService;

@RestController
@RequestMapping(value="/criancas")
public class CriancaResource {
	
	@Autowired
	private CriancaService service;
	
	@RequestMapping(method = RequestMethod.GET)
	@CrossOrigin
	@PreAuthorize( "hasAnyRole('ROLE_Administrador','ROLE_AssistenteSocial')")
	public ResponseEntity<Page<CriancaGetDTO>> findPage (
			@RequestParam(value="page",defaultValue="0") Integer page,
			@RequestParam(value="linesPerPage",defaultValue="200") Integer linesPerPage,
			@RequestParam(value="orderBy",defaultValue="nome") String orderBy,
			@RequestParam(value="direction",defaultValue="ASC") String direction,
			@RequestParam(value="nome",required = false) String nome,
			@RequestParam(value="projeto",required = false) Integer projeto,
			@RequestParam(value="matriculado",required = false) Boolean matriculado,
			@RequestParam(value="espera",required = false) Boolean espera,
			@RequestParam(value="idCrianca",required = false) Integer idCrianca) {
		
		System.out.println("CHAMEI O FIND PAGE");
		if (page > 0) {
			page = page - 1;
		}
		
		ProjetoEnum projetoEnum = null;
		
		List<ProjetoEnum> teste = Arrays.asList(ProjetoEnum.values());
		if (projeto != -1) {
			for (ProjetoEnum p : teste) {
				if (projeto != null && p.ordinal() == projeto) {
					projetoEnum = p;
				}
			}
		}
		
		Page<Crianca> lista = null;
		if (idCrianca > 0) {
			lista = service.searchPorId(page,linesPerPage,orderBy,direction,idCrianca);
		}
		else {
			lista = service.search(nome, projetoEnum,matriculado,espera, page,linesPerPage,orderBy,direction,idCrianca);
		}
		
		Page<CriancaGetDTO> listCriancaDTO = lista.map(obj -> new CriancaGetDTO(obj));
		
		
		return ResponseEntity.ok().body(listCriancaDTO);
	}
	
	
	@RequestMapping(value="/familia/{idFamilia}", method = RequestMethod.GET)
	@CrossOrigin
	@PreAuthorize( "hasAnyRole('ROLE_Administrador','ROLE_AssistenteSocial')")
	public ResponseEntity<Page<CriancaGetDTO>> buscarCriancaFamilia (
			@RequestParam(value="page",defaultValue="0") Integer page,
			@RequestParam(value="linesPerPage",defaultValue="200") Integer linesPerPage,
			@RequestParam(value="orderBy",defaultValue="nome") String orderBy,
			@RequestParam(value="direction",defaultValue="ASC") String direction,
			@PathVariable Integer idFamilia) {
		
		
		if (page > 0) {
			page = page - 1;
		}
		Page<Crianca> lista = service.buscarPorFamilia(idFamilia,page,linesPerPage,orderBy,direction);
		
		Page<CriancaGetDTO> listaDTO = lista.map(obj -> new CriancaGetDTO(obj));
		
		
		return ResponseEntity.ok().body(listaDTO);
	}
	
	
	
	@RequestMapping(method=RequestMethod.POST)
	@PreAuthorize( "hasAnyRole('ROLE_Administrador','ROLE_AssistenteSocial')")
	public ResponseEntity<Void> insert(@RequestBody Crianca crianca){
		service.insert(crianca);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().
				path("/{id}").buildAndExpand(crianca.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
		
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	@PreAuthorize( "hasAnyRole('ROLE_Administrador','ROLE_AssistenteSocial')")
	public ResponseEntity<Void> update(@PathVariable Integer id,@RequestBody Crianca crianca){
		crianca.setId(id);
		service.update(crianca);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().
				path("/{id}").buildAndExpand(crianca.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
		
	}
	
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@CrossOrigin
	@PreAuthorize( "hasAnyRole('ROLE_Administrador','ROLE_AssistenteSocial')")
	public ResponseEntity<CriancaGetDTO> findById(@PathVariable Integer id){
			
		Crianca obj = service.findById(id);
		
		return ResponseEntity.ok().body(new CriancaGetDTO(obj));
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@CrossOrigin
	@PreAuthorize( "hasAnyRole('ROLE_Administrador','ROLE_AssistenteSocial')")
	public ResponseEntity<Crianca> Excluir(@PathVariable Integer id){
			
		service.deletar(id);
		
		return ResponseEntity.noContent().build() ;
	}
	
	
}
