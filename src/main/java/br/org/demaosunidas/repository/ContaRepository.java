package br.org.demaosunidas.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.org.demaosunidas.domain.Conta;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Integer>{


//	@Transactional(readOnly=true)
//	@Query("SELECT DISTINCT obj FROM Crianca obj LEFT JOIN obj.listInscricao i WHERE  (:nome is null or obj.nome LIKE %:nome%) " )
//	Page<Crianca> searchQuery(@Param("nome") String nome, Pageable pageRequest);
//	@Transactional(readOnly=true)
//	@Query("SELECT DISTINCT obj FROM Crianca obj WHERE ( (:nome is null or obj.nome LIKE %:nome%) AND (:matriculado  is null OR obj.matriculado = :matriculado) )" )
//	Page<Crianca> searchQueryTeste(@Param("nome") String nome, @Param("matriculado") boolean matriculado, Pageable pageRequest);
	
	
//	@Transactional(readOnly=true)
//	@Query("SELECT DISTINCT obj FROM Crianca obj LEFT JOIN obj.listInscricao i WHERE ( obj.familia.id = :idFamilia ) " )
//	Page<Crianca> searchQueryPorFamilia(@Param("idFamilia") Integer idFamilia, Pageable pageRequest);
	
	
	@Query("SELECT DISTINCT (c) from Conta c "
			+ "JOIN FETCH c.listTransacao m "
			+ "RIGHT JOIN FETCH c.listSaldo s "
			+ "WHERE (m.data BETWEEN :dataInicio AND :dataFim) "
			
			
//			+ "AND (s.data < :dataInicio )"
			+ ""
			+ "ORDER BY m.data , s.data")
	List<Conta> getContasComSaldoAndMovimentosPorData(@Param("dataInicio")LocalDate dataInicio,@Param("dataFim") LocalDate dataFim);
	
}
