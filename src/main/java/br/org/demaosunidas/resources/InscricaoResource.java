package br.org.demaosunidas.resources;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

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

import br.org.demaosunidas.domain.Inscricao;
import br.org.demaosunidas.domain.enums.ProjetoEnum;
import br.org.demaosunidas.domain.enums.Status;
import br.org.demaosunidas.dto.InscricaoDTO;
import br.org.demaosunidas.services.InscricaoService;

@RestController
@RequestMapping(value="/inscricao")
@CrossOrigin
public class InscricaoResource {
	
	@Autowired
	private InscricaoService service;
	
	@RequestMapping(method = RequestMethod.GET)
	@CrossOrigin
	public ResponseEntity<Page<InscricaoDTO>> findPage (
			@RequestParam(value="page",defaultValue="0") Integer page,
			@RequestParam(value="linesPerPage",defaultValue="200") Integer linesPerPage,
			@RequestParam(value="orderBy",defaultValue="nome") String orderBy,
			@RequestParam(value="direction",defaultValue="ASC") String direction,
			@RequestParam(value="nomeCrianca",required = false) String nome,
			@RequestParam(value="projeto",required = false) Integer projeto,
			@RequestParam(value="ano",required = false) Integer ano,
			@RequestParam(value="espera",required = false) Boolean espera) {
		
		
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
		
		Page<Inscricao> lista = service.search(nome, projetoEnum,espera, page,linesPerPage,orderBy,direction);
		
		Page<InscricaoDTO> listCriancaDTO = lista.map(obj -> new InscricaoDTO(obj));
		
		
		return ResponseEntity.ok().body(listCriancaDTO);
	}
	
	@RequestMapping(value="/crianca/{idCrianca}", method = RequestMethod.GET)
	@CrossOrigin
	public ResponseEntity<Page<InscricaoDTO>> findPagePorCrianca (
			@RequestParam(value="page",defaultValue="0") Integer page,
			@RequestParam(value="linesPerPage",defaultValue="200") Integer linesPerPage,
			@RequestParam(value="orderBy",defaultValue="ano") String orderBy,
			@RequestParam(value="direction",defaultValue="DESC") String direction,
			@RequestParam(value="projeto",required = false) Integer projeto,
			@RequestParam(value="ano",required = false) Integer ano,
			@RequestParam(value="espera",required = false) Boolean espera,
			@PathVariable Integer idCrianca ) {
		
		
		if (page > 0) {
			page = page - 1;
		}
		
		
		Page<Inscricao> lista = service.buscarPorCrianca( idCrianca, page,linesPerPage,orderBy,direction);
		
		Page<InscricaoDTO> listCriancaDTO = lista.map(obj -> new InscricaoDTO(obj));
		
		return ResponseEntity.ok().body(listCriancaDTO);
	}
	
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody InscricaoDTO inscricaoDTO){
		inscricaoDTO.setId(null);
		inscricaoDTO.setStatus(Status.ATIVO);
		Inscricao i = new Inscricao(inscricaoDTO);
		service.insert(i);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().
				path("/{id}").buildAndExpand(i.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
		
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@PathVariable Integer id,@RequestBody InscricaoDTO inscricaoDTO){
		inscricaoDTO.setId(id);
		Inscricao i = new Inscricao(inscricaoDTO);
		service.update(i);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().
				path("/{id}").buildAndExpand(i.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
		
	}
	
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@CrossOrigin
	public ResponseEntity<InscricaoDTO> findById(@PathVariable Integer id){
			
		Inscricao obj = service.findById(id);
		
		return ResponseEntity.ok().body(new InscricaoDTO(obj));
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@CrossOrigin
	public ResponseEntity<Void> Excluir(@PathVariable Integer id){
			
		service.deletar(id);
		
		return ResponseEntity.noContent().build() ;
	}
	
	
}
