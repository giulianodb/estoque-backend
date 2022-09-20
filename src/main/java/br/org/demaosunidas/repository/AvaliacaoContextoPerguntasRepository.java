package br.org.demaosunidas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.org.demaosunidas.domain.AvaliacaoContextoPerguntas;

@Repository
public interface AvaliacaoContextoPerguntasRepository extends JpaRepository<AvaliacaoContextoPerguntas, Integer>{

//
//	@Transactional(readOnly=true)
//	@Query("SELECT DISTINCT obj FROM Crianca obj WHERE ( :nome is null or obj.nome LIKE %:nome% )" )
//	Page<Crianca> searchQuery(@Param("nome") String nome, Pageable pageRequest);
//	
//	@Transactional(readOnly=true)
//	@Query("SELECT DISTINCT obj FROM Crianca obj WHERE ( obj.familia.id = :idFamilia )" )
//	Page<Crianca> searchQueryPorFamilia(@Param("idFamilia") Integer idFamilia, Pageable pageRequest);
	
}
