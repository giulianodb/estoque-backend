package br.org.demaosunidas.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.org.demaosunidas.domain.Inscricao;
import br.org.demaosunidas.domain.enums.ProjetoEnum;
import br.org.demaosunidas.domain.enums.Status;
import br.org.demaosunidas.repository.InscricaoRepository;
import br.org.demaosunidas.services.exception.ObjectNotFoudException;

@Service
public class InscricaoService {
	
	@Autowired
	private InscricaoRepository repo;
	
	public Page<Inscricao> search (String nomeCrianca, ProjetoEnum projeto, Boolean espera, Integer page,Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		
//		return repo. searchQuery(nome,projeto,matriculada,espera,pageRequest);
//		return repo. searchQueryTeste(nome,matriculada,pageRequest);
		
		return null;
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
		
	}

	public Page<Inscricao> buscarPorCrianca(Integer idCrianca, Integer page, Integer linesPerPage, String orderBy,
			String direction) {
		

		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
				
		
		return repo.searchQueryPorCrianca(idCrianca,pageRequest);
		
	}

	
	
}
