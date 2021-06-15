package br.org.demaosunidas.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.org.demaosunidas.domain.Movimentacao;

@Repository
public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Integer>{

	@Transactional(readOnly=true)
	@Query("SELECT obj FROM Movimentacao obj" )
	Page<Movimentacao> searchQuery(Pageable pageRequest);
	
	@Transactional(readOnly=true)
	@Query("SELECT obj FROM Movimentacao obj WHERE obj.data >= :data AND obj.produto.id = :idProduto ORDER BY obj.data ASC" )
	List <Movimentacao> obterMovimentacoesPosteriores(@Param("data") LocalDateTime data, @Param("idProduto") Integer idProduto);
	
}
