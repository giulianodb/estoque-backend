package br.org.demaosunidas.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.org.demaosunidas.domain.Familia;
import br.org.demaosunidas.domain.SituacaoIdentificada;

@Repository
public interface SituacaoIdentificadaRepository extends JpaRepository<SituacaoIdentificada, Integer>{

	@Transactional(readOnly=true)
	Page<SituacaoIdentificada> findAllByFamiliaOrderByAcao(Familia familia,Pageable pageRequest);
	
}
