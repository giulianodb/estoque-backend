package br.org.demaosunidas.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.org.demaosunidas.domain.LoteMovimentacao;

@Repository
public interface LoteMovimentacaoRepository extends JpaRepository<LoteMovimentacao, Integer>{


	@Transactional(readOnly=true)
	@Query("SELECT obj FROM LoteMovimentacao obj" )
	Page<LoteMovimentacao> searchQuery(Pageable pageRequest);
	
}
