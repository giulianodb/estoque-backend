package br.org.demaosunidas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.org.demaosunidas.domain.Atendimento;
import br.org.demaosunidas.domain.Crianca;

@Repository
public interface AtendimentoRepository extends JpaRepository<Atendimento, Integer>{

	@Transactional(readOnly=true)
	List<Atendimento> findAllByCriancaOrderByDataAtendimento(Crianca idCrianca);
	
}
