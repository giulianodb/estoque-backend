package br.org.demaosunidas.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.org.demaosunidas.domain.Crianca;
import br.org.demaosunidas.domain.enums.ProjetoEnum;

@Repository
public interface CriancaRepository extends JpaRepository<Crianca, Integer>{


	@Transactional(readOnly=true)
	@Query("SELECT DISTINCT obj FROM Crianca obj WHERE ( (:nome is null or obj.nome LIKE %:nome%) AND (:matriculado  is null OR obj.matriculado = :matriculado) AND (:espera  is null OR obj.listaEspera = :espera) AND (:projeto is null OR obj.projeto = :projeto) )" )
	Page<Crianca> searchQuery(@Param("nome") String nome, @Param("projeto") ProjetoEnum projeto, @Param("matriculado") boolean matriculado,@Param("espera") boolean espera, Pageable pageRequest);
	
	@Transactional(readOnly=true)
	@Query("SELECT DISTINCT obj FROM Crianca obj WHERE ( (:nome is null or obj.nome LIKE %:nome%) AND (:matriculado  is null OR obj.matriculado = :matriculado) )" )
	Page<Crianca> searchQueryTeste(@Param("nome") String nome, @Param("matriculado") boolean matriculado, Pageable pageRequest);
	
	
	@Transactional(readOnly=true)
	@Query("SELECT DISTINCT obj FROM Crianca obj WHERE ( obj.familia.id = :idFamilia )" )
	Page<Crianca> searchQueryPorFamilia(@Param("idFamilia") Integer idFamilia, Pageable pageRequest);
	
}
