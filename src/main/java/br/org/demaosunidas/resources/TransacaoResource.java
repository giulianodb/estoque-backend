package br.org.demaosunidas.resources;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
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
	public ResponseEntity<List<TransacaoDTO>> findPage (
			@RequestParam(value="idConta") Integer idConta,
			@RequestParam(value="dataInicio", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE,pattern = "yyyy-MM-dd")  LocalDate dataInicio,
			@RequestParam(value="dataFim", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE,pattern = "yyyy-MM-dd")  LocalDate dataFim,
			@RequestParam(value="dias30",defaultValue="false") Boolean dias30,
			@RequestParam(value="dias60",defaultValue="false") Boolean dias60
			
			) {
		
//		Page<Transacao> lista = service.search(dataInicio, dataFim, idConta);
		
//		Page<TransacaoDTO> TransacaoListDTO = lista.map(obj -> new TransacaoDTO(obj));
		
		if (dias30) {
			dataFim = LocalDate.now();
			dataInicio = LocalDate.now().minusMonths(1);
		} else if(dias60) {
			dataFim = LocalDate.now();
			dataInicio = LocalDate.now().minusMonths(2);
		}
		
		List<TransacaoDTO> listaDTO = service.listarTransacaoComSaldo(dataInicio, dataFim, idConta);
		
		return ResponseEntity.ok().body(listaDTO );
	}
	
	@RequestMapping(method=RequestMethod.POST)
//	@PreAuthorize( "hasAnyRole('ROLE_Administrador','ROLE_Estoque')")
	public ResponseEntity<Void> insert(@RequestBody TransacaoDTO obj){
		
		try {
			Transacao ret = service.insert(new Transacao(obj));
			
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().
					path("/{id}").buildAndExpand(ret.getId()).toUri();
			return ResponseEntity.created(uri).build();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return ResponseEntity.badRequest().build();
		}
	}
	
	@RequestMapping(method=RequestMethod.PUT,value = "/{id}")
//	@PreAuthorize( "hasAnyRole('ROLE_Administrador','ROLE_Estoque')")
	public ResponseEntity<Void> update(@RequestBody TransacaoDTO obj, @PathVariable("id") Integer id){
		
		try {
			obj.setId(id);
			service.update(new Transacao(obj));
			
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().
					path("/{id}").buildAndExpand(obj.getId()).toUri();
			return ResponseEntity.created(uri).build();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return ResponseEntity.badRequest().build();
		}
	}
	
	@RequestMapping(method=RequestMethod.DELETE,value = "/{id}")
//	@PreAuthorize( "hasAnyRole('ROLE_Administrador','ROLE_Estoque')")
	public ResponseEntity<Void> delete(@PathVariable("id") Integer id){
		
		try {
			service.delete(id);
			
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return ResponseEntity.badRequest().build();
		}
	}
	
	
	
}
