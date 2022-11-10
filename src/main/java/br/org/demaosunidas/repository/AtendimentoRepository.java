package br.org.demaosunidas.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.org.demaosunidas.domain.Atendimento;
import br.org.demaosunidas.domain.Crianca;

@Repository
public interface AtendimentoRepository extends JpaRepository<Atendimento, Integer>{

	@Transactional(readOnly=true)
	Page<Atendimento> findAllByCriancaOrderByDataAtendimento(Crianca idCrianca,Pageable pageRequest);
	
}
