package br.org.demaosunidas.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.org.demaosunidas.domain.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{


//	@Transactional(readOnly=true)
//	@Query("SELECT DISTINCT obj FROM Aluno obj WHERE ( :nome is null or obj.nome LIKE %:nome% )" )
//	Page<Aluno> searchQuery(@Param("nome") String nome, Pageable pageRequest);
	@Transactional(readOnly=true)
    Usuario findByLoginUsuario(String loginUsuario); 
}
