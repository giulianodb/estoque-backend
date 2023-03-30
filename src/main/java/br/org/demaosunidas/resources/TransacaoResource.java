package br.org.demaosunidas.resources;

import java.net.URI;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.org.demaosunidas.domain.Transacao;
import br.org.demaosunidas.dto.TransacaoDTO;
import br.org.demaosunidas.services.TransacaoService;

@RestController
@RequestMapping(value="/transacoes")
public class TransacaoResource {
	
	@Autowired
	private TransacaoService service;
	
	@RequestMapping(method = RequestMethod.GET)
	@CrossOrigin
//	@PreAuthorize( "hasAnyRole('ROLE_Administrador','ROLE_Estoque')")
	public ResponseEntity<Page<TransacaoDTO>> findPage (
			@RequestParam(value="page",defaultValue="0") Integer page,
			@RequestParam(value="idConta",defaultValue="0") Integer idConta,
			@RequestParam(value="dataInicio",defaultValue="0") LocalDateTime dataInicio,
			@RequestParam(value="dataFim",defaultValue="0") LocalDateTime dataFim,
			@RequestParam(value="linesPerPage",defaultValue="10") Integer linesPerPage,
			@RequestParam(value="orderBy",defaultValue="data") String orderBy,
			@RequestParam(value="direction",defaultValue="ASC") String direction) {
		
		
		Page<Transacao> lista = service.search(dataInicio,idConta, page,linesPerPage,orderBy,direction);
		
		Page<TransacaoDTO> TransacaoListDTO = lista.map(obj -> new TransacaoDTO(obj));
		
		return ResponseEntity.ok().body(TransacaoListDTO );
	}
	
	@RequestMapping(method=RequestMethod.POST)
//	@PreAuthorize( "hasAnyRole('ROLE_Administrador','ROLE_Estoque')")
	public ResponseEntity<Void> insert(@RequestBody TransacaoDTO obj){
		
		try {
			service.insert(new Transacao(obj));
			
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().
					path("/{id}").buildAndExpand(obj.getId()).toUri();
			return ResponseEntity.created(uri).build();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return ResponseEntity.badRequest().build();
		}
	}
	
	
	
}
