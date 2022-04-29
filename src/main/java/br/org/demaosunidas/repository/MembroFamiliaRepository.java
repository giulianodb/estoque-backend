package br.org.demaosunidas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.org.demaosunidas.domain.MembroFamilia;

@Repository
public interface MembroFamiliaRepository extends JpaRepository<MembroFamilia, Integer>{
	
}
