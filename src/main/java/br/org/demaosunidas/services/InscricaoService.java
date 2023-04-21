package br.org.demaosunidas.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.org.demaosunidas.domain.Inscricao;
import br.org.demaosunidas.domain.enums.PeriodoEnum;
import br.org.demaosunidas.domain.enums.ProjetoEnum;
import br.org.demaosunidas.domain.enums.Status;
import br.org.demaosunidas.dto.InscricaoDTO;
import br.org.demaosunidas.dto.InscricaoReportDTO;
import br.org.demaosunidas.repository.InscricaoRepository;
import br.org.demaosunidas.services.exception.ObjectNotFoudException;

@Service
public class InscricaoService {
	
	@Autowired
	private InscricaoRepository repo;
	
	public Page<Inscricao> search (String nomeCrianca, ProjetoEnum projeto, Boolean espera, Integer ano, Integer page,Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		
		return repo.searchQuery(nomeCrianca,projeto,espera,ano,pageRequest);
//		return repo. searchQueryTeste(nome,matriculada,pageRequest);
		
//		return null;
	}
	
	public Inscricao findById(Integer id) {
		Optional<Inscricao> obj = repo.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoudException("Objeto não encontrado"));
	}
	
	public void insert(Inscricao obj) {
		obj.setId(null);
		repo.save(obj);
	}
	
	public Inscricao update(Inscricao objAlterado) {
		Inscricao objBanco = findById(objAlterado.getId());
		updateData(objBanco,objAlterado);
		return repo.save(objBanco);
	}
//	
	
	public Inscricao desligar (Inscricao objAlterado) {
		Inscricao objBanco = findById(objAlterado.getId());
		objBanco.setDataDesligamento(objAlterado.getDataDesligamento());
		return repo.save(objBanco);
	}
//	
	
	public void deletar (Integer id) {
		Inscricao obj = findById(id);
		obj.setStatus(Status.INATIVO);
		repo.save(obj);
	}
	
	private void updateData(Inscricao objBanco, Inscricao objAlterado) {
		objBanco.setAno(objAlterado.getAno());
		objBanco.setDataInscricao(objAlterado.getDataInscricao());
		objBanco.setListaEspera(objAlterado.getListaEspera());
		objBanco.setProjeto(objAlterado.getProjeto());
		objBanco.setStatus(objAlterado.getStatus());
		objBanco.setPeriodo(objAlterado.getPeriodo());
		objBanco.setDataDesligamento(objAlterado.getDataDesligamento());
	}

	public Page<Inscricao> buscarPorCrianca(Integer idCrianca, Integer page, Integer linesPerPage, String orderBy,
			String direction) {
		

		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
				
		
		return repo.searchQueryPorCrianca(idCrianca,pageRequest);
		
	}

	public InscricaoReportDTO searchRelatorio(String nome, ProjetoEnum projetoEnum, Boolean espera, Integer ano,
			Integer page, Integer linesPerPage, String orderBy, String direction) {
			
			List<Inscricao> lista = this.search(nome, projetoEnum, espera, ano, page, linesPerPage, orderBy, direction).getContent();
			
			InscricaoReportDTO dto = new InscricaoReportDTO();
			
			for (Inscricao i : lista) {
				if (i.getListaEspera()) {
					dto.setTotalEspera(dto.getTotalEspera() + 1);
					
					if (i.getPeriodo().equals(PeriodoEnum.MANHA)) {
						dto.setTotalManhaEspera (dto.getTotalManhaEspera() + 1);
					} else {
						dto.setTotalTardeEspera(dto.getTotalTardeEspera() + 1);
					}
					
				} else {
					dto.setTotalInscritos(dto.getTotalInscritos() + 1);
					if (i.getPeriodo().equals(PeriodoEnum.MANHA)) {
						dto.setTotalManha(dto.getTotalManha() + 1);
					} else {
						dto.setTotalTarde(dto.getTotalTarde() + 1);
					}
				}
				
				
				dto.getListInscricao().add(new InscricaoDTO(i));
			}
			
		// TODO Auto-generated method stub
		return dto;
	}

	
	
}
