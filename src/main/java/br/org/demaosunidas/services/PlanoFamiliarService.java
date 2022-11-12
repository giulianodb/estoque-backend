package br.org.demaosunidas.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.org.demaosunidas.domain.Familia;
import br.org.demaosunidas.domain.PlanoFamiliar;
import br.org.demaosunidas.dto.PlanoFamiliarDTO;
import br.org.demaosunidas.repository.PlanoFamiliarRepository;

@Service
public class PlanoFamiliarService {
	
	@Autowired
	private PlanoFamiliarRepository repo;

	public Page<PlanoFamiliar> searchPorFamilia(Integer idFamilia, Integer page,Integer linesPerPage, String orderBy, String direction) {
		Familia c = new Familia(idFamilia);
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		Page<PlanoFamiliar> teste = repo.findAllByFamiliaOrderByNome(c,pageRequest);
		return teste;
		
	}
	
	public void insert(PlanoFamiliarDTO dto) {
		PlanoFamiliar plano = new PlanoFamiliar(dto); 
		repo.save(plano);
	}
	
	public void update(PlanoFamiliarDTO dto) {
		PlanoFamiliar planoBanco = repo.getOne(dto.getId());
		
		repo.save(this.updatePlanoFamiliar(planoBanco, dto));
	}
	
	public void delete (Integer idAtendimento) {
		repo.deleteById(idAtendimento);
	}
	
	private PlanoFamiliar updatePlanoFamiliar(PlanoFamiliar objBanco, PlanoFamiliarDTO dto) {
		objBanco.setMeta(dto.getMeta());
		objBanco.setNome(dto.getNome());
		objBanco.setVinculo(dto.getVinculo());
		
		return objBanco;
	}
	
}
