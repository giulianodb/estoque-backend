package br.org.demaosunidas.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.org.demaosunidas.domain.Familia;
import br.org.demaosunidas.domain.Instituicao;
import br.org.demaosunidas.domain.MembroFamilia;
import br.org.demaosunidas.domain.Moradia;
import br.org.demaosunidas.domain.Motivo;
import br.org.demaosunidas.domain.ProgramasSociais;
import br.org.demaosunidas.domain.VisitaDomiciliar;
import br.org.demaosunidas.domain.enums.Status;
import br.org.demaosunidas.dto.DoadorDTO;
import br.org.demaosunidas.dto.FamiliaDTO;
import br.org.demaosunidas.dto.InstituicaoDTO;
import br.org.demaosunidas.dto.ParceiroDTO;
import br.org.demaosunidas.repository.FamiliaRepository;
import br.org.demaosunidas.services.exception.ObjectNotFoudException;

@Service
public class ParceiroService {
	
	@Autowired
	private InstituicaoService instituicaoService;
	
	@Autowired
	private DoadorService doadorService;
	
	@Autowired
	private FamiliaService familiaService;
	
	
	public ParceiroDTO obterParceiros() {
		
		List<InstituicaoDTO> listInstituicaos = new ArrayList<>();
		
		List<InstituicaoDTO> listInstituicaosCliente = new ArrayList<>();
		List<InstituicaoDTO> listInstituicaosFornecedor = new ArrayList<>();
		List<InstituicaoDTO> listInstituicaosEstoque = new ArrayList<>();
		
		List<DoadorDTO> listDoador = new ArrayList<>();
		
		List<DoadorDTO> listDoadorCliente = new ArrayList<>();
		List<DoadorDTO> listDoadorFornecedor = new ArrayList<>();
		List<DoadorDTO> listDoadorEstoque = new ArrayList<>();
		
		
		List<FamiliaDTO> listFamilia = new ArrayList<>();
		
		instituicaoService.listar().forEach(x -> {
			listInstituicaos.add(InstituicaoService.entityToDto(x));
		});
		
		doadorService.listar().forEach(x -> listDoador.add(DoadorService.entityToDto(x)));
//		familiaService.listar().forEach(x -> listFamilia.add(FamiliaService.entityToDto(x)));
		
		listInstituicaos.forEach(i -> {
			if (i.getEstoque()) 
				listInstituicaosEstoque.add(i);
			if(i.getCliente())
				listInstituicaosCliente.add(i);
			if (i.getFornecedor())
				listInstituicaosFornecedor.add(i);
		});
		
		listDoador.forEach(d -> {
			if (d.getEstoque()) 
				listDoadorEstoque.add(d);
			if(d.getCliente())
				listDoadorCliente.add(d);
			if (d.getFornecedor())
				listDoadorFornecedor.add(d);
		});
		
		
		ParceiroDTO dto = new ParceiroDTO();
//		dto.setListaDoador(listDoador);
//		dto.setListaFamilia(listFamilia);
//		dto.setListaInstituicaoDTOs(listInstituicaos);
		
		dto.setListaDoadorCliente(listDoadorCliente);
		dto.setListaDoadorFornecedor(listDoadorFornecedor);
		dto.setListaDoadorEstoque(listDoadorEstoque);
		
		dto.setListaInstituicaoCliente(listInstituicaosCliente);
		dto.setListaInstituicaoFornecedor(listInstituicaosFornecedor);
		dto.setListaInstituicaoEstoque(listInstituicaosEstoque);
		
		
		return dto;
	}
	
	
}
