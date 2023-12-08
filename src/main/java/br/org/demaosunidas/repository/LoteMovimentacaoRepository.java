package br.org.demaosunidas.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.org.demaosunidas.domain.LoteMovimentacao;
import br.org.demaosunidas.domain.Transacao;

@Repository
public interface LoteMovimentacaoRepository extends JpaRepository<LoteMovimentacao, Integer>{


	@Transactional(readOnly=true)
	@Query("SELECT obj FROM LoteMovimentacao obj" )
	Page<LoteMovimentacao> searchQuery(Pageable pageRequest);

	
    @Query(value = "SELECT COUNT(DISTINCT familia_id) AS qtd_distinta "
    		+ "FROM ("
    		+ "  SELECT l.familia_id FROM estoque.lotemovimentacao l "
    		+ "  WHERE l.familia_id IS NOT NULL "
    		+ " AND dataentrada >= :dataInicio AND dataentrada <= :dataFim "
    		+ " AND tipoMovimentacaoEnum = 1 "
    		+ "  UNION "
    		+ " "
    		+ "  SELECT l.familiacampanha_id FROM estoque.lotemovimentacao l "
    		+ "  WHERE l.familiacampanha_id IS NOT NULL "
    		 + " AND dataentrada >= :dataInicio AND dataentrada <= :dataFim"
    		 + " AND tipoMovimentacaoEnum = 1 "
    		+ " ) AS ids_distintos "
    		+ " "
            
            ,nativeQuery = true)
     Long countDistinctFamilies(@Param("dataInicio") LocalDate primeiroDiaDoMes, @Param("dataFim") LocalDate ultimoDiaDoMes);
	
	
}
