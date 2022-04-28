package br.org.demaosunidas.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.org.demaosunidas.domain.Familia;
import br.org.demaosunidas.domain.enums.Status;
import br.org.demaosunidas.repository.FamiliaRepository;
import br.org.demaosunidas.services.exception.ObjectNotFoudException;

@Service
public class FamiliaService {
	
	@Autowired
	private FamiliaRepository repo;
	
	public List<Familia> listar() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}
	
	public Page<Familia> search (String nome, Integer page,Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
				
		
		return repo. searchQuery(nome,pageRequest);
	}
	
	public Familia findById(Integer id) {
		Optional<Familia> obj = repo.findById(id);

		return obj. orElseThrow(() -> new ObjectNotFoudException("Objet non trouvé"));
	}
	
	public void insert(Familia obj) {
		obj.setId(null);
		repo.save(obj);
	}
	
	public Familia update(Familia objAlterado) {
		Familia objBanco = findById(objAlterado.getId());
		updateData(objBanco,objAlterado);
		return repo.save(objBanco);
	}
	
	public void deletar (Integer id) {
		Familia obj = findById(id);
		obj.setStatus(Status.INATIVO);
		repo.save(obj);
	}
	
	private void updateData(Familia objBanco, Familia objAnterado) {
		objBanco.setNomeResponsavel(objAnterado.getNomeResponsavel());
		
		objBanco.setBairro(objAnterado.getBairro());
		objBanco.setCelular(objAnterado.getCelular());
		objBanco.setCidade(objAnterado.getCidade());
		objBanco.setCpfResponsavel(objAnterado.getCpfResponsavel());
		objBanco.setEmail(objAnterado.getEmail());
		objBanco.setEstado(objAnterado.getEstado());
		objBanco.setRgResponsavel(objAnterado.getRgResponsavel());
		objBanco.setRua(objAnterado.getRua());
		objBanco.setTelefone(objAnterado.getTelefone());
		objBanco.setStatus(objAnterado.getStatus());
		
	}

	
	
}
