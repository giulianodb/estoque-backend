package br.org.demaosunidas.services;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import br.org.demaosunidas.domain.MembroFamilia;
import br.org.demaosunidas.repository.MembroFamiliaRepository;

@Service
public class FamiliaMembroService {
	
	@Autowired
	private MembroFamiliaRepository repo;
	
	
	public void delete(MembroFamilia membroFamilia) {
		repo.deleteById(membroFamilia.getId());
	}
	
	public void save(MembroFamilia membroFamilia) {
		repo.save(membroFamilia);
		
	}

	public void saveAll(Set<MembroFamilia> listMembroFamilia) {
		repo.saveAll(listMembroFamilia);
		
	}


	public MembroFamilia findById(int i) {
		// TODO Auto-generated method stub
		return repo.findById(i).get();
	}

	
}
