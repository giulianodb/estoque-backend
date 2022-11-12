package br.org.demaosunidas.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.org.demaosunidas.domain.Familia;
import br.org.demaosunidas.domain.SituacaoIdentificada;
import br.org.demaosunidas.dto.SituacaoIdentificadaDTO;
import br.org.demaosunidas.repository.SituacaoIdentificadaRepository;

@Service
public class SituacaoIdentificadaService {
	
	@Autowired
	private SituacaoIdentificadaRepository repo;

	public Page<SituacaoIdentificada> searchPorFamilia(Integer idFamilia, Integer page,Integer linesPerPage, String orderBy, String direction) {
		Familia c = new Familia(idFamilia);
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		Page<SituacaoIdentificada> teste = repo. findAllByFamiliaOrderByAcao(c,pageRequest);
		return teste;
		
	}
	
	public void insert(SituacaoIdentificadaDTO dto) {
		SituacaoIdentificada obj = new SituacaoIdentificada(dto); 
		repo.save(obj);
	}
	
	public void update(SituacaoIdentificadaDTO dto) {
		SituacaoIdentificada objBanco = repo.getOne(dto.getId());
		
		repo.save(this.updateSituacaoIdentificada(objBanco, dto));
	}
	
	public void delete (Integer idAtendimento) {
		repo.deleteById(idAtendimento);
	}
	
	private SituacaoIdentificada updateSituacaoIdentificada(SituacaoIdentificada objBanco, SituacaoIdentificadaDTO dto) {
		objBanco.setAcao(dto.getAcao()); 
		objBanco.setPrazo(dto.getPrazo());
		objBanco.setResponsabilidadeFamilia(dto.getResponsabilidadeFamilia());
		objBanco.setResponsabilidadeTecnica(dto.getResponsabilidadeTecnica());
		objBanco.setResultadosEsperados(dto.getResultadosEsperados());
		objBanco.setResultadosObtidos(dto.getResultadosObtidos());
		
		return objBanco;
	}
	
}
