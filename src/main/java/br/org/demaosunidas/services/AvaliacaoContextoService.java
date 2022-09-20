package br.org.demaosunidas.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.demaosunidas.domain.AvaliacaoContextoPerguntas;
import br.org.demaosunidas.domain.AvaliacaoContextoResposta;
import br.org.demaosunidas.domain.Crianca;
import br.org.demaosunidas.dto.AvaliacaoContextoDTO;
import br.org.demaosunidas.dto.AvaliacaoContextoPerguntasDTO;
import br.org.demaosunidas.dto.AvaliacaoContextoRespostaDTO;
import br.org.demaosunidas.repository.AvaliacaoContextoPerguntasRepository;
import br.org.demaosunidas.repository.AvaliacaoContextoRespostaRepository;
import br.org.demaosunidas.services.exception.ObjectNotFoudException;

@Service
public class AvaliacaoContextoService {
	
	@Autowired
	private AvaliacaoContextoRespostaRepository repo;
	
	@Autowired
	private AvaliacaoContextoPerguntasRepository perguntasRepo;
	
	@Autowired
	private CriancaService criancaService;
	
	public AvaliacaoContextoResposta findById(Integer id) {
		Optional<AvaliacaoContextoResposta> obj = repo.findById(id);

		return obj. orElseThrow(() -> new ObjectNotFoudException("Objet non trouvé"));
	}
	
	public void insertResposta(AvaliacaoContextoDTO avaliacaoContexto) {
		
		Crianca crianca = criancaService.findById(avaliacaoContexto.getCrianca().getId());
		
		for (AvaliacaoContextoRespostaDTO acrDto : avaliacaoContexto.getListAvaliacaoContextoResposta()) {
			AvaliacaoContextoPerguntas pergunta = perguntasRepo.findById(acrDto.getAvaliacaoContextoPergunta().getId()).get(); 
			AvaliacaoContextoResposta resposta = new AvaliacaoContextoResposta(acrDto,crianca,pergunta);

			repo.save(resposta);
		}
	}
	
	public List<AvaliacaoContextoRespostaDTO> buscarRespostasPorCrianca(Integer idCrianca) {
		List<AvaliacaoContextoRespostaDTO> lista = new ArrayList<>();
		
		Crianca crianca = criancaService. findById(idCrianca);
		
		List<AvaliacaoContextoResposta> listaBanco = repo.findByCriancaOrderById(crianca); 
		
		listaBanco.stream().forEach(x -> {
			lista.add(new AvaliacaoContextoRespostaDTO (x));
		});
		
//		for (AvaliacaoContextoResposta avaliacaoContextoResposta : listaBanco) {
//			lista.add(new AvaliacaoContextoRespostaDTO (avaliacaoContextoResposta));
//		}
		
		return lista;
	}
	
	
	public List<AvaliacaoContextoPerguntasDTO> buscarPerguntas() {
		List<AvaliacaoContextoPerguntasDTO> lista = new ArrayList<>();
		
		perguntasRepo.findAll().stream().forEach(x -> {
			lista.add(new AvaliacaoContextoPerguntasDTO (x));
		});
		
		return lista;
	}
	
	public List<AvaliacaoContextoRespostaDTO> montarRespostaVazia(Integer idCrianca) {
		
		List<AvaliacaoContextoRespostaDTO> listaRespostaDTO = new ArrayList<AvaliacaoContextoRespostaDTO>();
		
		List<AvaliacaoContextoPerguntas> listaPerguntas = perguntasRepo.findAll();
		
		for (AvaliacaoContextoPerguntas pergunta : listaPerguntas) {
			AvaliacaoContextoRespostaDTO respostaDTO = new AvaliacaoContextoRespostaDTO();
			respostaDTO.setAvaliacaoContextoPergunta(new AvaliacaoContextoPerguntasDTO(pergunta));
			listaRespostaDTO.add(respostaDTO);
		}
		
		
		
		return listaRespostaDTO;
		
	}

	
	
	public void updateResposta(AvaliacaoContextoDTO avaliacaoContexto) {
		
		for (AvaliacaoContextoRespostaDTO acrDto : avaliacaoContexto.getListAvaliacaoContextoResposta()) {
			AvaliacaoContextoResposta respostaBanco = repo.getOne(acrDto.getId());
			this.updateData(respostaBanco, acrDto);
			repo.save(respostaBanco);
			
		}
		
	}

	
	public AvaliacaoContextoResposta update(AvaliacaoContextoRespostaDTO objAlterado) {
		AvaliacaoContextoResposta objBanco = findById(objAlterado.getId());
		updateData(objBanco,objAlterado);
		return repo.save(objBanco);
	}
	
	private void updateData(AvaliacaoContextoResposta objBanco, AvaliacaoContextoRespostaDTO objAnterado) {
		objBanco.setObservacao(objAnterado.getObservacao());
		objBanco.setResposta(objAnterado.getResposta());
		objBanco.setData(objAnterado.getData());
	}


}
