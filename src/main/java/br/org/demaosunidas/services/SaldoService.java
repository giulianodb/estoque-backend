package br.org.demaosunidas.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.org.demaosunidas.domain.Aluno;
import br.org.demaosunidas.domain.Conta;
import br.org.demaosunidas.domain.Saldo;
import br.org.demaosunidas.domain.enums.Status;
import br.org.demaosunidas.repository.AlunoRepository;
import br.org.demaosunidas.repository.SaldoRepository;
import br.org.demaosunidas.services.exception.ObjectNotFoudException;

@Service
public class SaldoService {
	
	@Autowired
	private SaldoRepository repo;
	
	public Saldo obterSaldo(Integer idConta, LocalDateTime data) {
		// TODO Auto-generated method stub
		return repo.obterSaldoPorContaAndData(idConta, data);
	}
	
	public List<Saldo> obterTodosSaldo(Integer idConta, LocalDateTime data) {
		// TODO Auto-generated method stub
		return repo.obterListaSaldoPorContaAndData(idConta, data);
	}
	
	public void insert(Saldo obj) {
		obj.setId(null);
		
		Saldo saldoBanco = repo.findByContaAndData(obj.getConta(),obj.getData());
		
		if (saldoBanco != null) {
			this.updateData(saldoBanco, obj);
		}
		
		List<Saldo> saldosParaFrente = this.obterTodosSaldo(obj.getConta().getId(), obj.getData());
		
		if (saldosParaFrente.size() > 0) {
			for (Saldo saldo : saldosParaFrente) {
				saldo.setValor(saldo.getValor().add(obj.getValor()));
				this.update(saldo);
			}
		}
		
		repo.save(obj);
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