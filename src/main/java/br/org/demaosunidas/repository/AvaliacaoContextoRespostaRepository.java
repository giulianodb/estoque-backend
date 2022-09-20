package br.org.demaosunidas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.org.demaosunidas.domain.AvaliacaoContextoResposta;
import br.org.demaosunidas.domain.Crianca;

@Repository
public interface AvaliacaoContextoRespostaRepository extends JpaRepository<AvaliacaoContextoResposta, Integer>{

//
//	@Transactional(readOnly=true)
//	@Query("SELECT DISTINCT obj FROM Crianca obj WHERE ( :nome is null or obj.nome LIKE %:nome% )" )
//	Page<Crianca> searchQuery(@Param("nome") String nome, Pageable pageRequest);
//	
//	@Transactional(readOnly=true)
//	@Query("SELECT DISTINCT obj FROM Crianca obj WHERE ( obj.familia.id = :idFamilia )" )
//	Page<Crianca> searchQueryPorFamilia(@Param("idFamilia") Integer idFamilia, Pageable pageRequest);
	
	
	@Transactional(readOnly=true)
	List<AvaliacaoContextoResposta> findByCriancaOrderById(Crianca crianca);
	
}
