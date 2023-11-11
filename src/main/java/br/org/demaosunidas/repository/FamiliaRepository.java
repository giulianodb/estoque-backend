package br.org.demaosunidas.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.org.demaosunidas.domain.Familia;
import br.org.demaosunidas.domain.enums.Status;

@Repository
public interface FamiliaRepository extends JpaRepository<Familia, Integer>{


	@Transactional(readOnly=true)
//	@Query("SELECT DISTINCT obj FROM Familia obj WHERE ( (:nome is null or lower(obj.nomeResponsavel) LIKE lower (concat ('%',:nome ,'%' ))) AND obj.status = :status )")
	@Query("SELECT DISTINCT obj FROM Familia obj WHERE (   (:nome is null or obj.nomeResponsavel LIKE %:nome%) AND obj.status = :status) ")
	Page<Familia> searchQuery(@Param("nome") String nome, @Param("status") Status status, Pageable pageRequest);
	
	@Query("SELECT DISTINCT obj FROM Familia obj WHERE (   (:nome is null or obj.nomeResponsavel LIKE %:nome%) AND obj.status = :status AND SIZE(obj.listCrianca) > 0 ) ")
	Page<Familia> searchQueryComCriancas(@Param("nome") String nome, @Param("status") Status status, Pageable pageRequest);
	
	@Transactional(readOnly=true)
	@Query("SELECT DISTINCT obj FROM Familia obj " )
	Page<Familia> searchQueryTeste(Pageable pageRequest);
	
	@Transactional(readOnly=true)
	Familia findByCpfResponsavel(@Param("cpfResponsavel") String cpfResponsavel);
	
	@Transactional(readOnly=true)
	List<Familia> findAllByOrderByNomeResponsavel();
}
