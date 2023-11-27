package br.org.demaosunidas.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.org.demaosunidas.domain.Doador;
import br.org.demaosunidas.domain.enums.Status;
import br.org.demaosunidas.dto.DoadorDTO;
import br.org.demaosunidas.repository.DoadorRepository;
import br.org.demaosunidas.services.exception.ObjectNotFoudException;

@Service
public class DoadorService {
	
	@Autowired
	private DoadorRepository repo;
	
	public List<Doador> listar() {
		// TODO Auto-generated method stub
		return repo.findByStatusOrderByNome(Status.ATIVO);
	}
	
	public Page<Doador> search (String nome, Integer page,Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
				
		
		return repo. searchQuery(nome, Status.ATIVO, pageRequest);
	}
	
	public Doador findById(Integer id) {
		Optional<Doador> obj = repo.findById(id);

		return obj. orElseThrow(() -> new ObjectNotFoudException("Objet non trouvé"));
	}
	
	public void insert(Doador obj) {
		obj.setId(null);
		obj.setStatus(Status.ATIVO);
		repo.save(obj);
	}
	
	public Doador update(Doador objAlterado) {
		Doador objBanco = findById(objAlterado.getId());
		updateData(objBanco,objAlterado);
		return repo.save(objBanco);
	}
	
	public void deletar (Integer id) {
		Doador obj = findById(id);
		obj.setStatus(Status.INATIVO);
		repo.save(obj);
	}
	
	private void updateData(Doador objBanco, Doador objAnterado) {
		objBanco.setBairro(objAnterado.getBairro());
		objBanco.setCelular(objAnterado.getCelular());
		objBanco.setCidade(objAnterado.getCidade());
		objBanco.setCpf(objAnterado.getCpf());
		objBanco.setDataNascimento(objAnterado.getDataNascimento());
		objBanco.setEmail(objAnterado.getEmail());
		objBanco.setEstado(objAnterado.getEstado());
		objBanco.setNome(objAnterado.getNome());
		objBanco.setRg(objAnterado.getRg());
		objBanco.setRua(objAnterado.getRua());
		objBanco.setTelefone(objAnterado.getTelefone());
		objBanco.setStatus(objAnterado.getStatus());
		
	}

	
	public static DoadorDTO entityToDto(Doador entity) {
		if (entity == null) {
			return null;
		}
		
		DoadorDTO dto = new DoadorDTO();
		
		dto.setId(entity.getId());
		dto.setBairro(entity.getBairro());
		dto.setCelular(entity.getCelular());
		dto.setCidade(entity.getCidade());
		dto.setCpf(entity.getCpf());
		dto.setDataNascimento(entity.getDataNascimento());
		dto.setEmail(entity.getEmail());
		dto.setEstado(entity.getEstado());
		dto.setNome(entity.getNome());
		dto.setRg(entity.getRg());
		dto.setRua(entity.getRua());
		dto.setTelefone(entity.getTelefone());
		dto.setStatus(entity.getStatus());
		
		dto.setCliente(entity.getCliente());
		dto.setEstoque(entity.getEstoque());
		dto.setFornecedor(entity.getFornecedor());
		
		return dto;
	}
	
	public static Doador dtoToEntity(DoadorDTO dto) {
		if (dto == null) {
			return null;
		}
		
		Doador entity = new Doador();
		
		entity.setId(dto.getId());
		entity.setBairro(dto.getBairro());
		entity.setCelular(dto.getCelular());
		entity.setCidade(dto.getCidade());
		entity.setCpf(dto.getCpf());
		entity.setDataNascimento(dto.getDataNascimento());
		entity.setEmail(dto.getEmail());
		entity.setEstado(dto.getEstado());
		entity.setNome(dto.getNome());
		entity.setRg(dto.getRg());
		entity.setRua(dto.getRua());
		entity.setTelefone(dto.getTelefone());
		entity.setStatus(dto.getStatus());
		
		return entity;
	}
	
	
}
