package br.org.demaosunidas.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.org.demaosunidas.domain.Crianca;
import br.org.demaosunidas.domain.Crianca;
import br.org.demaosunidas.domain.enums.Status;
import br.org.demaosunidas.repository.CriancaRepository;
import br.org.demaosunidas.repository.CriancaRepository;
import br.org.demaosunidas.services.exception.ObjectNotFoudException;

@Service
public class CriancaService {
	
	@Autowired
	private CriancaRepository repo;
	
//	public List<Crianca> listar() {
//		// TODO Auto-generated method stub
//		return repo.findAll();
//	}
	
	public Page<Crianca> search (String nome, Integer page,Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
				
		
		return repo. searchQuery(nome,pageRequest);
	}
	
	public Crianca findById(Integer id) {
		Optional<Crianca> obj = repo.findById(id);

		return obj.orElseThrow(() -> new ObjectNotFoudException("Objeto não encontrado"));
	}
	
	public void insert(Crianca obj) {
		obj.setId(null);
		repo.save(obj);
	}
	
	public Crianca update(Crianca objAlterado) {
		Crianca objBanco = findById(objAlterado.getId());
		updateData(objBanco,objAlterado);
		return repo.save(objBanco);
	}
//	
	public void deletar (Integer id) {
		Crianca obj = findById(id);
		obj.setStatus(Status.INATIVO);
		repo.save(obj);
	}
	
	private void updateData(Crianca objBanco, Crianca objAnterado) {
		objBanco.setAlergia(objAnterado.getAlergia());
		objBanco.setStatus(objAnterado.getStatus());
		
	}

	public Page<Crianca> buscarPorFamilia(Integer idFamilia, Integer page, Integer linesPerPage, String orderBy,
			String direction) {
		

		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
				
		
		return repo.searchQueryPorFamilia(idFamilia,pageRequest);
		
	}

	
	
}
