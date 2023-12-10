package br.org.demaosunidas.services;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.org.demaosunidas.domain.Saldo;
import br.org.demaosunidas.domain.Transacao;
import br.org.demaosunidas.dto.SaldoDTO;
import br.org.demaosunidas.repository.SaldoRepository;

@Service
public class SaldoService {
	
	@Autowired
	private SaldoRepository repo;
	
	public Saldo obterSaldo(Integer idConta, LocalDate data) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = PageRequest.of(0,1);
		List<Saldo> result =  repo.obterSaldoPorContaAndData(idConta, data,pageRequest);
		
		if (result.size() > 0) {
			return result.get(0);
		} else {
			return new Saldo();
		}
	}
	
	public Saldo obterUltimoSaldo(Integer idConta, LocalDate data) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = PageRequest.of(0,1);
		List<Saldo> result =  repo.obterUltimoSaldoPorContaAndData(idConta, data,pageRequest);
		
		if (result.size() > 0) {
			return result.get(0);
		} else {
			return new Saldo();
		}
	}
	
	
	/**
	 * obtem saldos para frente, desconsidera da dataa atual
	 * @param idConta
	 * @param data
	 * @return
	 */
	public List<Saldo> obterTodosSaldoMenosData(Integer idConta, LocalDate data) {
		// TODO Auto-generated method stub
		return repo.obterListaSaldoPorContaAndDataEmFrente(idConta, data);
	}
	
	/**
	 * Obtem todos os saldos inclusive da mesma data
	 * @param idConta
	 * @param data
	 * @return
	 */
	public List<Saldo> obterTodosSaldo(Integer idConta, LocalDate data) {
		// TODO Auto-generated method stub
		return repo.obterListaSaldoPorContaAndData(idConta, data);
	}
	
	public void insert(Saldo obj) {
		obj.setId(null);
		
		Saldo saldoBanco = repo.findByContaAndData(obj.getConta(),obj.getData());
		BigDecimal valor = obj.getValor();
		
		if (saldoBanco != null) {
			saldoBanco.setValor(saldoBanco.getValor().add(valor));
			
		} else {
			PageRequest pageRequest = PageRequest.of(0,1);
			
			List<Saldo> result = repo.obterSaldoPorContaAndData(obj.getConta().getId(),obj.getData(),pageRequest);
			
			if (result.size() > 0) {
				Saldo ultimoSaldoConta = result.get(0);
				
				//somar o ultimo saldo com o novo saldo.
				//valor.add(ultimoSaldoConta.getValor());
				obj.setValor(obj.getValor().add(ultimoSaldoConta.getValor()));
			}
			
			saldoBanco = obj;
		}
		
		List<Saldo> saldosParaFrente = this.obterTodosSaldoMenosData(obj.getConta().getId(), obj.getData());
		
		if (saldosParaFrente.size() > 0) {
			for (Saldo saldo : saldosParaFrente) {
				saldo.setValor(saldo.getValor().add(valor));
				this.update(saldo);
			}
		}
		
		repo.save(saldoBanco);
	}
	
	public Saldo update(Saldo objAlterado) {
		Saldo objBanco = findById(objAlterado.getId());
		updateData(objBanco,objAlterado);
		return repo.save(objBanco);
	
	}

	private Saldo findById(Integer id) {
		return repo.findById(id).get();
	}

	private void updateData(Saldo objBanco, Saldo objAlterado) {
		objBanco.setValor(objAlterado.getValor());
		
	}
	/**
	 * Alterar os saldos em frente, desfazendo os valores da transacação 
	 * @param objBanco
	 */
	public void desfazerSaldosEmFrente(Transacao objBanco) {
		
		List<Saldo> saldosParaFrente = this.obterTodosSaldo(objBanco.getConta().getId(), objBanco.getData());
		for (Saldo saldo : saldosParaFrente) {
			saldo.setValor(saldo.getValor().subtract(objBanco.getValor()));
			repo.saveAndFlush(saldo);
		}
		
	}

//	public List<Saldo> obterSaldosPorData(LocalDate dataInicio) {
//		return repo.obterSaldosPorData(dataInicio);
//	}
	
	
	public static SaldoDTO entityToDto(Saldo entity) {
		SaldoDTO dto = new SaldoDTO();
		if (entity.getConta() != null) {
			dto.setConta(ContaService.entityToDto(entity.getConta()));
		}
		
		dto.setData(entity.getData());
		dto.setId(entity.getId());
		dto.setValor(entity.getValor());
		
		return dto;
	}
	
	
}