package br.org.demaosunidas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.org.demaosunidas.domain.Categoria;
import br.org.demaosunidas.domain.CentroCusto;
import br.org.demaosunidas.domain.GrupoCategoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer>{
	
	@Transactional(readOnly=true)
	@Query("SELECT c FROM Categoria c JOIN c.grupoCategoria g WHERE g.tipoTransacao = 0" )
	List<Categoria> buscarCategoriaDespesas();
	
	@Transactional(readOnly=true)
	@Query("SELECT c FROM Categoria c JOIN c.grupoCategoria g WHERE g.tipoTransacao = 1" )
	List<Categoria> buscarCategoriaReceitas();
	
	
	@Transactional()
	@Modifying
	@Query("update Categoria u set u.nome = :nome where u.id = :id")
	void updatePhone(@Param(value = "id") Integer id, @Param(value = "nome") String phone);
}
