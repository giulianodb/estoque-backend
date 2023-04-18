package br.org.demaosunidas.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.org.demaosunidas.domain.Campanha;
import br.org.demaosunidas.domain.enums.Status;
import br.org.demaosunidas.repository.CampanhaRepository;
import br.org.demaosunidas.services.exception.ObjectNotFoudException;

@Service
public class CampanhaService {
	
	@Autowired
	private CampanhaRepository repo;
	
	public List<Campanha> listar() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}
	
	public Page<Campanha> search (String nome, Integer page,Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
				
		
		return repo. searchQuery(nome,pageRequest);
	}
	
	public Campanha findById(Integer id) {
		Optional<Campanha> obj = repo.findById(id);

		return obj. orElseThrow(() -> new ObjectNotFoudException("Objet non trouvé"));
	}
	
	public void insert(Campanha obj) {
		obj.setId(null);
		obj.setStatus(Status.ATIVO);
		repo.save(obj);
	}
	
	public Campanha update(Campanha objAlterado) {
		Campanha objBanco = findById(objAlterado.getId());
		updateData(objBanco,objAlterado);
		return repo.save(objBanco);
	}
	
	public void deletar (Integer id) {
		Campanha obj = findById(id);
		obj.setStatus(Status.INATIVO);
		repo.save(obj);
	}
	
	private void updateData(Campanha objBanco, Campanha objAnterado) {
		objBanco.setDescricao(objAnterado.getDescricao());
		objBanco.setMesFim(objAnterado.getMesFim());
		objBanco.setMesInicio(objAnterado.getMesInicio());
		objBanco.setNome(objAnterado.getNome());
		objBanco.setStatus(objAnterado.getStatus());
		
	}

	
	
}
