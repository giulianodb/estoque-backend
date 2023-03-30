package br.org.demaosunidas.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.demaosunidas.domain.Conta;
import br.org.demaosunidas.domain.enums.Status;
import br.org.demaosunidas.repository.ContaRepository;
import br.org.demaosunidas.services.exception.ObjectNotFoudException;

@Service
public class ContaService {
	
	@Autowired
	private ContaRepository repo;
	
	public List<Conta> listar() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}
	
//	public Page<Campanha> search (String nome, Integer page,Integer linesPerPage, String orderBy, String direction) {
//		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
//				
//		
//		return repo. searchQuery(nome,pageRequest);
//	}
	
	public Conta findById(Integer id) {
		Optional<Conta> obj = repo.findById(id);

		return obj. orElseThrow(() -> new ObjectNotFoudException("Objet non trouvé"));
	}
	
	
	public void insert(Conta obj) {
		obj.setId(null);
		repo.save(obj);
	}
	
	public Conta update(Conta objAlterado) {
		Conta objBanco = findById(objAlterado.getId());
		updateData(objBanco,objAlterado);
		return repo.save(objBanco);
	}
	
	public void deletar (Integer id) {
		Conta obj = findById(id);
		obj.setStatus(Status.INATIVO);
		repo.save(obj);
	}
	
	private void updateData(Conta objBanco, Conta objAlterado) {
		objBanco.setAgenciaConta(objAlterado.getAgenciaConta());
		objBanco.setNomeConta(objAlterado.getNomeConta());
		objBanco.setNumeroConta(objAlterado.getNumeroConta());
		objBanco.setStatus(objAlterado.getStatus());
		objBanco.setStatus(objAlterado.getStatus());
		objBanco.setTipoConta(objAlterado.getTipoConta());
		
	}
	
}
