package br.org.demaosunidas.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.org.demaosunidas.domain.Crianca;

@Repository
public interface CriancaRepository extends JpaRepository<Crianca, Integer>{


	@Transactional(readOnly=true)
	@Query("SELECT DISTINCT obj FROM Crianca obj WHERE ( :nome is null or obj.nome LIKE %:nome% )" )
	Page<Crianca> searchQuery(@Param("nome") String nome, Pageable pageRequest);
	
	@Transactional(readOnly=true)
	@Query("SELECT DISTINCT obj FROM Crianca obj WHERE ( obj.familia.id = :idFamilia )" )
	Page<Crianca> searchQueryPorFamilia(@Param("idFamilia") Integer idFamilia, Pageable pageRequest);
	
}
