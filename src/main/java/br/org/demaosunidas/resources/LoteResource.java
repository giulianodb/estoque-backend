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

import br.org.demaosunidas.domain.LoteMovimentacao;
import br.org.demaosunidas.dto.LoteMovimentacaoGetDTO;
import br.org.demaosunidas.dto.LoteMovimentacaoInsertDTO;
import br.org.demaosunidas.services.LoteMovimentacaoService;

@RestController
@RequestMapping(value="/lotes")
public class LoteResource {
	
	@Autowired
	private LoteMovimentacaoService service;
	
	@RequestMapping(method = RequestMethod.GET)
	@CrossOrigin
	@PreAuthorize( "hasAnyRole('ROLE_Administrador','ROLE_Estoque')")
	public ResponseEntity<Page<LoteMovimentacaoGetDTO>> findPage (
			@RequestParam(value="page",defaultValue="0") Integer page,
			@RequestParam(value="linesPerPage",defaultValue="10") Integer linesPerPage,
			@RequestParam(value="orderBy",defaultValue="data") String orderBy,
			@RequestParam(value="direction",defaultValue="ASC") String direction) {
		
		
		Page<LoteMovimentacao> lista = service.search(page,linesPerPage,orderBy,direction);
		
		Page<LoteMovimentacaoGetDTO> lotesDTO = lista.map(obj -> new LoteMovimentacaoGetDTO(obj));
		
		return ResponseEntity.ok().body(lotesDTO);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@PreAuthorize( "hasAnyRole('ROLE_Administrador','ROLE_Estoque')")
	public ResponseEntity<Void> insert(@RequestBody LoteMovimentacaoInsertDTO loteInsertDto){
		
		LoteMovimentacao lote;
		try {
			lote = service.insert(service.fromInsertDTO(loteInsertDto));
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().
					path("/{id}").buildAndExpand(lote.getCodigo()).toUri();
			return ResponseEntity.created(uri).build();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return ResponseEntity.badRequest().build();
		}
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	@PreAuthorize( "hasAnyRole('ROLE_Administrador','ROLE_Estoque')")
	public ResponseEntity<Void> update(@PathVariable Integer id,@RequestBody LoteMovimentacaoInsertDTO loteInsertDto){
		
		LoteMovimentacao lote = service.fromInsertDTO(loteInsertDto);
		
		lote.setCodigo(id);
		try {
			service.update(lote);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return ResponseEntity.badRequest().build();
		}

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().
				path("/{id}").buildAndExpand(lote.getCodigo()).toUri();
		
		return ResponseEntity.created(uri).build();
		
	}
	
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@PreAuthorize( "hasAnyRole('ROLE_Administrador','ROLE_Estoque')")
	@CrossOrigin
	public ResponseEntity<LoteMovimentacaoGetDTO> findById(@PathVariable Integer id){
			
		LoteMovimentacaoGetDTO obj = service.toDTO(service.findById(id));
		
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@PreAuthorize( "hasAnyRole('ROLE_Administrador','ROLE_Estoque')")
	@CrossOrigin
	public ResponseEntity<LoteMovimentacao> Excluir(@PathVariable Integer id){
			
		service.deletar(id);
		
		return ResponseEntity.noContent().build() ;
	}
	
	
	
}
