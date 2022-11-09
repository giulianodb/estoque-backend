package br.org.demaosunidas.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.demaosunidas.domain.Atendimento;
import br.org.demaosunidas.domain.Crianca;
import br.org.demaosunidas.dto.AtendimentoDTO;
import br.org.demaosunidas.dto.CriancaDTO;
import br.org.demaosunidas.repository.AtendimentoRepository;

@Service
public class AtendimentoService {
	
	@Autowired
	private AtendimentoRepository repo;

	public List<AtendimentoDTO> searchPorCrianca(Integer idCrianca) {
		Crianca c = new Crianca(idCrianca);
		return this.toListAtendimentoDTO(repo.findAllByCriancaOrderByDataAtendimento(c));
		
	}
	
	public List<AtendimentoDTO> toListAtendimentoDTO (List<Atendimento> listAtendimento) {
		List<AtendimentoDTO> listDto = new ArrayList<AtendimentoDTO>();
		listAtendimento.stream().forEach(x -> 
				{
					AtendimentoDTO dto = new AtendimentoDTO();
					CriancaDTO criancaDTO = new CriancaDTO();
					criancaDTO.setId(x.getCrianca().getId());
					criancaDTO.setNome(x.getCrianca().getNome());
					
					dto.setCrianca(criancaDTO);
					dto.setDataAtendimento(x.getDataAtendimento());
					dto.setRegistro(x.getRegistroAtendimento());
					dto.setId(x.getId());
					listDto.add(dto);
				});
		
		return listDto;
		
	}

	public void insert(AtendimentoDTO atendimentoDTO) {
		repo.save(this.toAtendimento(atendimentoDTO));
	}
	
	public void update(AtendimentoDTO atendimentoDTO) {
		Atendimento atendimentoBanco = repo.getOne(atendimentoDTO.getId());
		
		repo.save(this.updateAtendimento(atendimentoBanco, atendimentoDTO));
	}
	
	public void delete (Integer idAtendimento) {
		repo.deleteById(idAtendimento);
	}
	
	private Atendimento updateAtendimento(Atendimento atendimentoBanco, AtendimentoDTO atendimentoDTO) {
		atendimentoBanco.setDataAtendimento(atendimentoDTO.getDataAtendimento());
		atendimentoBanco.setRegistroAtendimento(atendimentoDTO.getRegistro());
		
		return atendimentoBanco;
	}

	public Atendimento toAtendimento(AtendimentoDTO atendimentoDTO) {
		Atendimento ate = new Atendimento();
		
		Crianca c = new Crianca();
		c.setId(atendimentoDTO.getCrianca().getId());
		ate.setCrianca(c);
		ate.setDataAtendimento(atendimentoDTO.getDataAtendimento());
		ate.setRegistroAtendimento(atendimentoDTO.getRegistro());
		
		return ate;
	}
	
	
}
