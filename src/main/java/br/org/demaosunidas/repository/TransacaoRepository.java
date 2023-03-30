package br.org.demaosunidas.repository;

import org.springframework.data.domain.Pageable;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.org.demaosunidas.domain.Conta;
import br.org.demaosunidas.domain.Movimentacao;
import br.org.demaosunidas.domain.Transacao;

@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, Integer>{

//	@Transactional(readOnly=true)
//	@Query("SELECT obj FROM Movimentacao obj" )
//	Page<Movimentacao> searchQuery(Pageable pageRequest);
	
	@Transactional(readOnly=true)
	@Query("SELECT obj FROM Transacao obj WHERE obj.data >= :data AND obj.conta.id = :idConta ORDER BY obj.data ASC" )
	Page <Transacao> obterTransacoesPosteriores(@Param("data") LocalDateTime data, @Param("idConta") Integer idConta, Pageable pageRequest);
	
	List<Transacao> findByConta(Conta conta);
	
	@Transactional(readOnly=true)
	@Query("SELECT obj FROM Transacao obj WHERE obj.conta.id = :idConta ORDER BY obj.data ASC" )
	List <Transacao> obterTransacoesPorConta(@Param("idConta") Integer idConta);
}
