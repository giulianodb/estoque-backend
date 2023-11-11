package br.org.demaosunidas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.org.demaosunidas.domain.Categoria;
import br.org.demaosunidas.domain.CentroCusto;
import br.org.demaosunidas.domain.GrupoCategoria;

@Repository
public interface GrupoCategoriaRepository extends JpaRepository<GrupoCategoria, Integer>{

	@Transactional(readOnly=true)
	@Query("SELECT g FROM GrupoCategoria g WHERE g.tipoTransacao = 0" )
	List<GrupoCategoria> buscarCategoriaDespesas();
	
	@Transactional(readOnly=true)
	@Query("SELECT g FROM GrupoCategoria g WHERE g.tipoTransacao = 1" )
	List<GrupoCategoria> buscarCategoriaReceitas();
	
	
//	@Transactional(readOnly=true)
//	@Query("SELECT g FROM GrupoCategoria g    RIGHT JOIN FETCH  g.listCategoria  c  RIGHT  JOIN FETCH  c.listTransacao" )
//	List<GrupoCategoria> buscarCategoriaTeste();
	
}
