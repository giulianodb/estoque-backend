package br.org.demaosunidas.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.org.demaosunidas.domain.Conta;
import br.org.demaosunidas.domain.Saldo;

@Repository
public interface SaldoRepository extends JpaRepository<Saldo, Integer>{
	
	
	@Transactional(readOnly=true)
	@Query(nativeQuery = true, value = "SELECT obj FROM Saldo obj WHERE obj.conta.id = :idConta AND obj.data > :data ORDER BY obj.data ASC LIMIT 1" )
	Saldo obterSaldoPorContaAndData(Integer idConta, LocalDateTime data);

	@Transactional(readOnly=true)
	@Query("SELECT obj FROM Saldo obj WHERE obj.conta.id = :idConta AND obj.data > :data ORDER BY obj.data ASC " )
	List<Saldo> obterListaSaldoPorContaAndData(Integer idConta, LocalDateTime data);
	
	@Transactional(readOnly=true)
	Saldo findByContaAndData(Conta conta, LocalDateTime data);

//	@Transactional(readOnly=true)
//	@Query("SELECT DISTINCT obj FROM Aluno obj WHERE ( :nome is null or obj.nome LIKE %:nome% )" )
//	Page<Aluno> searchQuery(@Param("nome") String nome, Pageable pageRequest);
	
}
