package br.org.demaosunidas.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.org.demaosunidas.domain.Conta;
import br.org.demaosunidas.domain.Transacao;
import br.org.demaosunidas.repository.TransacaoRepository;
import br.org.demaosunidas.services.exception.ObjectNotFoudException;

@Service
public class TransacaoService {
	
	@Autowired
	private TransacaoRepository repo;

	public List<Transacao> listar() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}
	
	public Page<Transacao> search (LocalDateTime data, Integer idConta,Integer page,Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
				
		return repo.obterTransacoesPosteriores(data, idConta, pageRequest);
	}
	
	public Transacao findById(Integer id) {
		Optional<Transacao> obj = repo.findById(id);

		return obj. orElseThrow(() -> new ObjectNotFoudException("Objet non trouvé"));
	}
	
	@Transactional
	public void insert(Transacao obj) {
		obj.setId(null);
		repo.save(obj);
	}
	
	
	public List<Transacao> buscarTransacaoPorConta(Integer codConta){
		Conta lote = new Conta(codConta);
		
		return repo.findByConta(lote);
	}
	
}
