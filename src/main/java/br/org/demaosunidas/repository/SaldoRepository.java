package br.org.demaosunidas.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.org.demaosunidas.domain.Conta;
import br.org.demaosunidas.domain.Saldo;

@Repository
public interface SaldoRepository extends JpaRepository<Saldo, Integer>{
	
	
	@Transactional(readOnly=true)
	@Query(value = "SELECT obj FROM Saldo obj WHERE obj.conta.id = :idConta AND obj.data <= :data ORDER BY obj.data DESC" )
	List<Saldo> obterSaldoPorContaAndData(Integer idConta, LocalDate data, Pageable pageRequest);
	
	@Transactional(readOnly=true)
	@Query(value = "SELECT obj FROM Saldo obj WHERE obj.conta.id = :idConta AND obj.data < :data ORDER BY obj.data DESC" )
	List<Saldo> obterUltimoSaldoPorContaAndData(Integer idConta, LocalDate data, Pageable pageRequest);
	
	@Transactional(readOnly=true)
	@Query("SELECT obj FROM Saldo obj WHERE obj.conta.id = :idConta AND obj.data >= :data ORDER BY obj.data ASC " )
	List<Saldo> obterListaSaldoPorContaAndData(Integer idConta, LocalDate data);
	
	@Transactional(readOnly=true)
	@Query("SELECT obj FROM Saldo obj WHERE obj.conta.id = :idConta AND obj.data > :data ORDER BY obj.data ASC " )
	List<Saldo> obterListaSaldoPorContaAndDataEmFrente(Integer idConta, LocalDate data);
	
	@Transactional(readOnly=true)
	Saldo findByContaAndData(Conta conta, LocalDate data);
	
//	@Query(value = "SELECT s1 FROM Saldo s1 JOIN (SELECT s2.contaId, MAX(s2.data) AS ultimaData " +
//            "FROM Saldo s2 WHERE s2.data <= :dataParam GROUP BY s2.contaId) s3 " +
//            "ON s1.contaId = s3.contaId AND s1.data = s3.ultimaData")
//	List<Saldo> obterSaldosPorData (LocalDate data);
	
	
	
	

//	@Transactional(readOnly=true)
//	@Query( value = "SELECT obj FROM Saldo obj WHERE obj.conta.id = :idConta AND obj.data < :data ORDER BY obj.data ASC " )
//	Saldo findUltimoSaldoConta(Integer idConta, LocalDateTime data, Pageable pageRequest);
	
	
	
}
