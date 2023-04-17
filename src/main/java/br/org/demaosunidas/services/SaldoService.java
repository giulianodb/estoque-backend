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
		
		List<Saldo> saldosParaFrente = this.obterTodosSaldo(obj.getConta().getId(), obj.getData());
		
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
}