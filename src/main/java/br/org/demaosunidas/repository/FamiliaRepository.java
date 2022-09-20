package br.org.demaosunidas.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.org.demaosunidas.domain.Familia;

@Repository
public interface FamiliaRepository extends JpaRepository<Familia, Integer>{


	@Transactional(readOnly=true)
	@Query("SELECT DISTINCT obj FROM Familia obj WHERE ( :nome is null or lower(obj.nomeResponsavel) LIKE lower (concat ('%',:nome ,'%' )))")
	Page<Familia> searchQuery(@Param("nome") String nome, Pageable pageRequest);
	
	@Transactional(readOnly=true)
	@Query("SELECT DISTINCT obj FROM Familia obj " )
	Page<Familia> searchQueryTeste(Pageable pageRequest);
}
