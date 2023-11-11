package br.org.demaosunidas.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.demaosunidas.domain.CentroCusto;
import br.org.demaosunidas.dto.CentroCustoDTO;
import br.org.demaosunidas.repository.CentroCustoRepository;
import br.org.demaosunidas.services.exception.ObjectNotFoudException;

@Service
public class CentroCustoService {
	
	@Autowired
	private CentroCustoRepository repo;
	
	public List<CentroCusto> listar() {
		return repo.findAll();
	}
	
	public CentroCusto findById(Integer id) {
		Optional<CentroCusto> obj = repo.findById(id);

		return obj.orElseThrow(() -> new ObjectNotFoudException("Objet non trouvé"));
	}

	public static CentroCustoDTO entityToDto(CentroCusto x) {
		
		CentroCustoDTO dto = new CentroCustoDTO();
		
		dto.setDescricao(x.getDescricao());
		dto.setId(x.getId());
		dto.setNome(x.getNome());
		dto.setStatus(x.getStatus());
		
		return dto;
	}
	
}
