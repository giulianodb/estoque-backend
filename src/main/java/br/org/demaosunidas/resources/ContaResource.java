package br.org.demaosunidas.resources;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.security.PermitAll;

import org.springframework.beans.factory.annotation.Autowired;
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

import br.org.demaosunidas.domain.Conta;
import br.org.demaosunidas.domain.Saldo;
import br.org.demaosunidas.dto.ContaDTO;
import br.org.demaosunidas.dto.ContaPorTipoDTO;
import br.org.demaosunidas.dto.SaldoDTO;
import br.org.demaosunidas.services.ContaService;
import br.org.demaosunidas.services.SaldoService;

@RestController
@RequestMapping(value="/contas")
public class ContaResource {
	
	@Autowired
	private ContaService service;
	
	@Autowired
	private SaldoService saldoService;
	
	@RequestMapping(method = RequestMethod.GET)
	@CrossOrigin
	@PreAuthorize( "hasAnyRole('ROLE_Administrador','ROLE_Financeiro')")
	public ResponseEntity<List<ContaDTO> > findAll () {
		List<ContaDTO> listaDTO = new ArrayList<>();
		
		for (Conta x : service.listar()) {
			listaDTO.add(new ContaDTO(x));
		}
		
		return ResponseEntity.ok().body(listaDTO);
	}
	
	@RequestMapping(path = "/tipo_conta/todos", method = RequestMethod.GET)
	@CrossOrigin
	@PreAuthorize( "hasAnyRole('ROLE_Administrador','ROLE_Financeiro')")
	public ResponseEntity<List<ContaPorTipoDTO> > findAllTipoConta () {
		List<ContaPorTipoDTO> listaTipo = new ArrayList<>();
		
		for (Conta x : service.listar()) {
			Saldo s = saldoService.obterSaldo(x.getId(), LocalDate.now());
			ContaDTO c = new ContaDTO(x);
			c.setSaldo(new SaldoDTO(s));
			
			if (listaTipo.size() == 0) {
				ContaPorTipoDTO ctDto = new ContaPorTipoDTO();
				ctDto.setTipoConta(c.getTipoConta());
				ctDto.setSaldoContas(c.getSaldo().getValor());
				ctDto.setContas(new ArrayList<>());
				ctDto.getContas().add(c);
				listaTipo.add(ctDto);
			} else {
				
				boolean encontrou = false;
				for (ContaPorTipoDTO contaPorTipoDTO : listaTipo) {
					if (contaPorTipoDTO.getTipoConta().equals(c.getTipoConta())) {
						contaPorTipoDTO.setSaldoContas(contaPorTipoDTO.getSaldoContas().add(c.getSaldo().getValor()));
						contaPorTipoDTO.getContas().add(c);
						encontrou = true;
					}
				} 
				if (!encontrou) {
					ContaPorTipoDTO ctDto = new ContaPorTipoDTO();
					ctDto.setTipoConta(c.getTipoConta());
					ctDto.setSaldoContas(c.getSaldo().getValor());
					ctDto.setContas(new ArrayList<>());
					ctDto.getContas().add(c);
					listaTipo.add(ctDto);
				}
				
			}
			
		}
		
		return ResponseEntity.ok().body(listaTipo);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@PreAuthorize( "hasAnyRole('ROLE_Administrador','ROLE_Financeiro')")
	public ResponseEntity<Void> insert(@RequestBody ContaDTO conta){
		
		conta = service.insert(conta);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().
				path("/{id}").buildAndExpand(conta.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
		
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	@PreAuthorize( "hasAnyRole('ROLE_Administrador','ROLE_Financeiro')")
	public ResponseEntity<Void> update(@PathVariable Integer id,@RequestBody ContaDTO contaDTO){
		contaDTO.setId(id);
		
		service.update(contaDTO);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().
				path("/{id}").buildAndExpand(id).toUri();
		
		return ResponseEntity.created(uri).build();
		
	}
//	
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@PreAuthorize( "hasAnyRole('ROLE_Administrador','ROLE_Estoque')")
	@CrossOrigin
	public ResponseEntity<ContaDTO> findById(@PathVariable Integer id){
			
		ContaDTO obj = new ContaDTO(service.findById(id));
		
		return ResponseEntity.ok().body(obj);
	}
	
	
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	@PreAuthorize( "hasAnyRole('ROLE_Administrador','ROLE_Financeiro')")
	public ResponseEntity<Void> update(@PathVariable Integer id){
		service.delete(id);
		
		return ResponseEntity.ok().build();
		
	}
	
//	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
//	@PreAuthorize( "hasAnyRole('ROLE_Administrador','ROLE_Estoque')")
//	@CrossOrigin
//	public ResponseEntity<Doador> Excluir(@PathVariable Integer id){
//			
//		service.deletar(id);
//		
//		return ResponseEntity.noContent().build() ;
//	}
//	
	
  	@GetMapping("/extrato")
	@CrossOrigin
	@PreAuthorize( "hasAnyRole('ROLE_Administrador','ROLE_Financeiro')")
	public ResponseEntity<byte[]> getExtrato (
			@RequestParam(value="dataInicio", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE,pattern = "yyyy-MM-dd")  LocalDate dataInicio,
			@RequestParam(value="dataFim", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE,pattern = "yyyy-MM-dd")  LocalDate dataFim,
			@RequestParam(value="dias30",defaultValue="false") Boolean dias30,
			@RequestParam(value="dias60",defaultValue="false") Boolean dias60
			
			) throws IOException {
		
		if (dias30) {
			dataFim = LocalDate.now();
			dataInicio = LocalDate.now().minusMonths(1);
		} else if(dias60) {
			dataFim = LocalDate.now();
			dataInicio = LocalDate.now().minusMonths(2);
		}
		
		service.relatorioExtrato(dataInicio, dataFim);
		
		 String pathToPDF = "/tmp/extrato.pdf";
	        Path pdfPath = Paths.get(pathToPDF);
	        byte[] pdfBytes = Files.readAllBytes(pdfPath);

	        HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_PDF); // Configurando o Content-Type

	        return ResponseEntity
	                .ok()
	                .headers(headers)
	                .body(pdfBytes);
		 
	}
	
	
}
