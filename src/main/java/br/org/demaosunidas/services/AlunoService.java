package br.org.demaosunidas.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.org.demaosunidas.domain.Aluno;
import br.org.demaosunidas.domain.enums.Status;
import br.org.demaosunidas.repository.AlunoRepository;
import br.org.demaosunidas.services.exception.ObjectNotFoudException;

@Service
public class AlunoService {
	
	@Autowired
	private AlunoRepository repo;
	
	public List<Aluno> listar() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}
	
	public Page<Aluno> search (String nome, Integer page,Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
				
		
		return repo. searchQuery(nome,pageRequest);
	}
	
	public Aluno findById(Integer id) {
		Optional<Aluno> obj = repo.findById(id);

		return obj. orElseThrow(() -> new ObjectNotFoudException("Objet non trouvé"));
	}
	
	public void insert(Aluno obj) {
		obj.setId(null);
		repo.save(obj);
	}
	
	public Aluno update(Aluno objAlterado) {
		Aluno objBanco = findById(objAlterado.getId());
		updateData(objBanco,objAlterado);
		return repo.save(objBanco);
	}
	
	public void deletar (Integer id) {
		Aluno obj = findById(id);
		obj.setStatus(Status.INATIVO);
		repo.save(obj);
	}
	
	private void updateData(Aluno objBanco, Aluno objAnterado) {
		objBanco.setBairro(objAnterado.getBairro());
		objBanco.setCidade(objAnterado.getCidade());
		objBanco.setDataNascimento(objAnterado.getDataNascimento());
		objBanco.setEstado(objAnterado.getEstado());
		objBanco.setNome(objAnterado.getNome());
		objBanco.setRua(objAnterado.getRua());
		objBanco.setStatus(objAnterado.getStatus());
		
	}

	
	
}
