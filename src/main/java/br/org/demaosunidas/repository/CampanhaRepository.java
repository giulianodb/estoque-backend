package br.org.demaosunidas.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.org.demaosunidas.domain.Campanha;

@Repository
public interface CampanhaRepository extends JpaRepository<Campanha, Integer>{


	@Transactional(readOnly=true)
	@Query("SELECT DISTINCT obj FROM Campanha obj WHERE ( :nome is null or obj.nome LIKE %:nome% )" )
	Page<Campanha> searchQuery(@Param("nome") String nome, Pageable pageRequest);
	
}
