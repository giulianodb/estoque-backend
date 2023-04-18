package br.org.demaosunidas.services;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.org.demaosunidas.domain.Conta;
import br.org.demaosunidas.domain.Saldo;
import br.org.demaosunidas.domain.Transacao;
import br.org.demaosunidas.dto.TransacaoDTO;
import br.org.demaosunidas.repository.TransacaoRepository;
import br.org.demaosunidas.services.exception.ObjectNotFoudException;

@Service
public class TransacaoService {
	
	@Autowired
	private TransacaoRepository repo;
	
	@Autowired
	private SaldoService saldoService;

	public List<Transacao> listar() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}
	
	public List<Transacao> search (LocalDate dataInicio,LocalDate dataFim, Integer idConta) {
		
		PageRequest pageRequest = PageRequest.of(0, 150, Direction.ASC, "data");
				
		return repo.obterTransacoesPorData(dataInicio, dataFim, idConta, pageRequest).getContent();
	}
	
	public List<TransacaoDTO> listarTransacaoComSaldo (LocalDate dataInicio,LocalDate dataFim, Integer idConta) {
		
		Saldo saldo = saldoService.obterUltimoSaldo(idConta, dataInicio);
		
		List<Transacao> listaTransacao = this.search(dataInicio, dataFim, idConta);
		List<TransacaoDTO> listaTransacaoDTO = new ArrayList<>();
		
		BigDecimal valorASomar = saldo.getValor();
		
		for (Transacao x : listaTransacao) {
			TransacaoDTO dto = new TransacaoDTO(x,valorASomar,true);
			valorASomar = dto.getSaldo();
			listaTransacaoDTO.add(dto);
		}
        Collections.reverse(listaTransacaoDTO);
		return listaTransacaoDTO;
	}
	
	
	public Transacao findById(Integer id) {
		Optional<Transacao> obj = repo.findById(id);

		return obj. orElseThrow(() -> new ObjectNotFoudException("Objet non trouvé"));
	}
	
	@Transactional
	public Transacao insert(Transacao obj) {
		obj.setId(null);
		
		Saldo s = new Saldo();
		s.setConta(obj.getConta());
		s.setData(obj.getData());
		s.setValor(obj.getValor());
		
		saldoService.insert(s);
		
		return repo.save(obj);
	}
	
	@Transactional
	public void update(Transacao obj) {
		
		Transacao transacaoBanco = repo.findById(obj.getId()).get();
		
		//Verificar a necessidade de alterar os SALDOS
		
		if (obj.getValor().compareTo(transacaoBanco.getValor()) != 0  || 
				!obj.getData().equals(transacaoBanco.getData()) || 
				!obj.getConta().getId().equals(transacaoBanco.getConta().getId())) {
			
			saldoService.desfazerSaldosEmFrente(transacaoBanco);
			
			Saldo novoSaldo = new Saldo();
			novoSaldo.setConta(obj.getConta());
			novoSaldo.setData(obj.getData());
			novoSaldo.setValor(obj.getValor());
			
			saldoService.insert(novoSaldo);
		}
		
		// Data é a mesma
		// Valor é o mesmo
		
		// Conta é a mesma
				  /*
				   *  ---- Data não é a mesma?
				   *       
				   */
				
		
		
		//Verificar se a conta é a mesma
		
		
		/*
		  Conta não é a mesma
	 	  obter o saldo daquela dia,
		  diminuir o saldo com valor original (transacaoBanco)
		 * obter os saldos para frente
		 * diminuir dos saldos da frente o valor original
		 * criar objeto saldo e salvar o novo saldo com a conta nova
		 * -------
		 */
		
		transacaoBanco.setConta(obj.getConta());
		transacaoBanco.setData(obj.getData());
		transacaoBanco.setDescricao(obj.getDescricao());
		transacaoBanco.setTipoTransacaoEnum(obj.getTipoTransacaoEnum());
		transacaoBanco.setValor(obj.getValor());
		
		repo.save(transacaoBanco);
	}
	
	
	@Transactional
	public void delete (Integer id) {
		Transacao transacaoBanco = repo.findById(id).get();
		saldoService.desfazerSaldosEmFrente(transacaoBanco);
		repo.delete(transacaoBanco);
	}
	
	public List<Transacao> buscarTransacaoPorConta(Integer codConta){
		Conta lote = new Conta(codConta);
		
		return repo.findByConta(lote);
	}
	
}
