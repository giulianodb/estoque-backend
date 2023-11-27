package br.org.demaosunidas.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.org.demaosunidas.domain.Doador;
import br.org.demaosunidas.domain.enums.Status;

@Repository
public interface DoadorRepository extends JpaRepository<Doador, Integer>{


	@Transactional(readOnly=true)
	@Query("SELECT DISTINCT obj FROM Doador obj WHERE (:nome IS NULL OR LOWER(obj.nome) LIKE CONCAT('%', LOWER(:nome), '%')   AND obj.status = :status)" )
	Page<Doador> searchQuery(@Param("nome") String nome, @Param("status") Status status, Pageable pageRequest);
	
	List<Doador> findAllByOrderByNome();
	
	List<Doador> findByStatusOrderByNome(Status status);
}
