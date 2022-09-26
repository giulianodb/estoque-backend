package br.org.demaosunidas.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.org.demaosunidas.dto.AvaliacaoContextoDTO;
import br.org.demaosunidas.dto.AvaliacaoContextoPerguntasDTO;
import br.org.demaosunidas.dto.AvaliacaoContextoRespostaDTO;
import br.org.demaosunidas.dto.CriancaDTO;
import br.org.demaosunidas.services.AvaliacaoContextoService;
import br.org.demaosunidas.services.CriancaService;

@RestController
@RequestMapping(value="/avaliacao_contexto")
public class AvaliacaoContextoResource {
	
	@Autowired
	private AvaliacaoContextoService service;
	
	
	@Autowired
	private CriancaService criancaService;
	
	
//	@RequestMapping(value="/familia/{idFamilia}", method = RequestMethod.GET)
//	@CrossOrigin
//	public ResponseEntity<Page<Crianca>> buscarCriancaFamilia (
//			@RequestParam(value="page",defaultValue="0") Integer page,
//			@RequestParam(value="linesPerPage",defaultValue="200") Integer linesPerPage,
//			@RequestParam(value="orderBy",defaultValue="nome") String orderBy,
//			@RequestParam(value="direction",defaultValue="ASC") String direction,
//			@PathVariable Integer idFamilia) {
//		
//		
//		if (page > 0) {
//			page = page - 1;
//		}
//		Page<Crianca> lista = service.buscarPorFamilia(idFamilia,page,linesPerPage,orderBy,direction);
//		
//		return ResponseEntity.ok().body(lista);
//	}
	
	
//	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
//	public ResponseEntity<Void> update(@PathVariable Integer id,@RequestBody Crianca crianca){
//		crianca.setId(id);
//		service.update(crianca);
//		
//		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().
//				path("/{id}").buildAndExpand(crianca.getId()).toUri();
//		
//		return ResponseEntity.created(uri).build();
//		
//	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody AvaliacaoContextoDTO avaliacaoContexto){
		
		service.insertResposta(avaliacaoContexto);
		
		return ResponseEntity.created(null).build();
		
	}
	
	@RequestMapping(method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody AvaliacaoContextoDTO avaliacaoContexto){
		
		service.updateResposta(avaliacaoContexto);
		
		return ResponseEntity.created(null).build();
		
	}
	
	@RequestMapping(value="/perguntas", method = RequestMethod.GET)
	@CrossOrigin
	public ResponseEntity<List<AvaliacaoContextoPerguntasDTO>> buscarPerguntas () {
		
		List<AvaliacaoContextoPerguntasDTO> lista = service.buscarPerguntas();
		
		return ResponseEntity.ok().body(lista);
	}
	
	
	@RequestMapping(value="/crianca/{idCrianca}/respostas", method = RequestMethod.GET)
	@CrossOrigin
	public ResponseEntity<AvaliacaoContextoDTO> buscarRespostas (@PathVariable Integer idCrianca) {
		AvaliacaoContextoDTO avaliacaoContextoDTO = new AvaliacaoContextoDTO();
		List<AvaliacaoContextoRespostaDTO> lista = service.buscarRespostasPorCrianca(idCrianca);
		
		CriancaDTO criancaDTO = new CriancaDTO (criancaService.findById(idCrianca));
		
		if (lista ==null || lista.size() == 0) {
			lista = service.montarRespostaVazia(idCrianca);
			avaliacaoContextoDTO.setNovo(true);
			avaliacaoContextoDTO.setTotalAfirmacao(0);
			avaliacaoContextoDTO.setTotalSituacao(0);
		}
		
		avaliacaoContextoDTO.setListAvaliacaoContextoResposta(lista);
		avaliacaoContextoDTO.setCrianca(criancaDTO);
		
		return ResponseEntity.ok().body(avaliacaoContextoDTO);
		
		
	}
}
