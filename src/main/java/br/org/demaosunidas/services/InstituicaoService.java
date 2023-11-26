package br.org.demaosunidas.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.org.demaosunidas.domain.Instituicao;
import br.org.demaosunidas.domain.enums.Status;
import br.org.demaosunidas.dto.InstituicaoDTO;
import br.org.demaosunidas.repository.InstituicaoRepository;
import br.org.demaosunidas.services.exception.ObjectNotFoudException;

@Service
public class InstituicaoService {
	
	@Autowired
	private InstituicaoRepository repo;
	
	public List<Instituicao> listar() {
		// TODO Auto-generated method stub
		return repo.findByStatusOrderByNome(Status.ATIVO);
	}
	
	public Page<Instituicao> search (String nome, Integer page,Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
				
		return repo. searchQuery(nome, Status.ATIVO, pageRequest);
	}
	
	public Instituicao findById(Integer id) {
		Optional<Instituicao> obj = repo.findById(id);

		return obj. orElseThrow(() -> new ObjectNotFoudException("Objet non trouvé"));
	}
	
	public void insert(Instituicao obj) {
		obj.setId(null);
		obj.setStatus(Status.ATIVO);
		repo.save(obj);
	}
	
	public void deletar (Integer id) {
		Instituicao obj = findById(id);
		obj.setStatus(Status.INATIVO);
		repo.save(obj);
	}
	
	public static InstituicaoDTO entityToDto(Instituicao entity) {
		if (entity == null) {
			return null;
		}
		
		InstituicaoDTO dto = new InstituicaoDTO();
		
		dto.setId(entity.getId());
		dto.setNome(entity.getNome());
		dto.setCliente(entity.getCliente());
		dto.setEstoque(entity.getEstoque());
		dto.setFornecedor(entity.getFornecedor());
		
		return dto;
	}
	
	public static Instituicao dtoToEntity(InstituicaoDTO dto) {
		if (dto == null) {
			return null;
		}
		
		Instituicao entity = new Instituicao();
		
		entity.setId(dto.getId());
		entity.setNome(dto.getNome());
		
		
		
		return entity;
	}
}
