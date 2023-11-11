package br.org.demaosunidas.resources;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.org.demaosunidas.dto.TransacaoDTO;
import br.org.demaosunidas.services.TransacaoService;

@RestController
@RequestMapping(value="/transacoes")
public class TransacaoResource {
	
	@Autowired
	private TransacaoService service;
	
	@RequestMapping(method = RequestMethod.GET)
	@CrossOrigin
	@PreAuthorize( "hasAnyRole('ROLE_Administrador','ROLE_Financeiro')")
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
	
//	@RequestMapping(method = RequestMethod.GET,path = "/razao")
    @GetMapping("/razao")
	@CrossOrigin
	@PreAuthorize( "hasAnyRole('ROLE_Administrador','ROLE_Financeiro')")
	public ResponseEntity<byte[]> getRazao (
			@RequestParam(value="dataInicio", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE,pattern = "yyyy-MM-dd")  LocalDate dataInicio,
			@RequestParam(value="dataFim", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE,pattern = "yyyy-MM-dd")  LocalDate dataFim,
			@RequestParam(value="dias30",defaultValue="false") Boolean dias30,
			@RequestParam(value="dias60",defaultValue="false") Boolean dias60
			
			) throws IOException {
		
//		Page<Transacao> lista = service.search(dataInicio, dataFim, idConta);
		
//		Page<TransacaoDTO> TransacaoListDTO = lista.map(obj -> new TransacaoDTO(obj));
		
		if (dias30) {
			dataFim = LocalDate.now();
			dataInicio = LocalDate.now().minusMonths(1);
		} else if(dias60) {
			dataFim = LocalDate.now();
			dataInicio = LocalDate.now().minusMonths(2);
		}
		
		service.relatorioRazao(dataInicio, dataFim);
		
//		 Resource resource = new FileSystemResource("/home/giuliano/out.pdf");
/*
	        if (resource.exists()) {
	            return ResponseEntity.ok()
	                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=razao.pdf" )
	                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
	                    .body(resource);
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	*/        
	        
//	        Path pdfPath = Paths.get("/home/giuliano/out.pdf");
//	        byte[] pdfBytes;
//			try {
//				pdfBytes = Files.readAllBytes(pdfPath);
//		        HttpHeaders headers = new HttpHeaders();
//		        headers.setContentType(MediaType.parseMediaType("application/pdf"));
//		        headers.setContentDispositionFormData("attachment", "seu_arquivo.pdf");
//
//		        return ResponseEntity
//		                .ok()
//		                .headers(headers)
//		                .body(pdfBytes);
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//				return ResponseEntity.notFound().build();
//			}

		
		 String pathToPDF = "/tmp/out.pdf";
	        Path pdfPath = Paths.get(pathToPDF);
	        byte[] pdfBytes = Files.readAllBytes(pdfPath);

	        HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_PDF); // Configurando o Content-Type

	        return ResponseEntity
	                .ok()
	                .headers(headers)
	                .body(pdfBytes);
		 
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@PreAuthorize( "hasAnyRole('ROLE_Administrador','ROLE_Financeiro')")
	public ResponseEntity<Void> insert(@RequestBody TransacaoDTO obj){
		
		try {
			obj.definirObjetos();
			obj = service.insert(obj);
			
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().
					path("/{id}").buildAndExpand(obj.getId()).toUri();
			return ResponseEntity.created(uri).build();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return ResponseEntity.badRequest().build();
		}
	}
	
	@RequestMapping(method=RequestMethod.PUT,value = "/{id}")
	@PreAuthorize( "hasAnyRole('ROLE_Administrador','ROLE_Financeiro')")
	public ResponseEntity<Void> update(@RequestBody TransacaoDTO obj, @PathVariable("id") Integer id){
		
		try {
			obj.definirObjetos();
			obj.setId(id);
			service.update(obj);
			
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
	@PreAuthorize( "hasAnyRole('ROLE_Administrador','ROLE_Financeiro')")
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
