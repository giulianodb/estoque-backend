package br.org.demaosunidas.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.org.demaosunidas.domain.Inscricao;
import br.org.demaosunidas.domain.enums.ProjetoEnum;

@Repository
public interface InscricaoRepository extends JpaRepository<Inscricao, Integer>{


//	@Transactional(readOnly=true)
	@Query("SELECT DISTINCT obj FROM Inscricao obj LEFT JOIN obj.crianca c WHERE ( (:nome is null or c.nome LIKE %:nome%)  AND (:espera  is null OR obj.listaEspera = :espera) AND (:projeto is null OR obj.projeto = :projeto)AND (:ano is null OR obj.ano = :ano) )" )
	Page<Inscricao> searchQuery(@Param("nome") String nome, @Param("projeto") ProjetoEnum projeto ,@Param("espera") boolean espera,@Param ("ano") Integer ano, Pageable pageRequest);
	
//	@Transactional(readOnly=true)
//	@Query("SELECT DISTINCT obj FROM Crianca obj WHERE ( (:nome is null or obj.nome LIKE %:nome%) AND (:matriculado  is null OR obj.matriculado = :matriculado) )" )
//	Page<Inscricao> searchQueryTeste(@Param("nome") String nome, @Param("matriculado") boolean matriculado, Pageable pageRequest);
	
	
	@Transactional(readOnly=true)
	@Query("SELECT DISTINCT obj FROM Inscricao obj WHERE ( obj.crianca.id = :idCrianca AND obj.status = 0) " )
	Page<Inscricao> searchQueryPorCrianca(@Param("idCrianca") Integer idCrianca, Pageable pageRequest);
	
}
