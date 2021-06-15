package br.org.demaosunidas.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.org.demaosunidas.domain.Movimentacao;
import br.org.demaosunidas.domain.Produto;
import br.org.demaosunidas.domain.TipoMovimentacaoEnum;
import br.org.demaosunidas.repository.MovimentacaoRepository;
import br.org.demaosunidas.services.exception.ObjectNotFoudException;
import br.org.demaosunidas.util.DateUtil;
import br.org.demaosunidas.util.NumeroUtil;

@Service
public class MovimentacaoService {
	
	@Autowired
	private MovimentacaoRepository repo;
	
	@Autowired
	private ProdutoService produtoService;
	
	public List<Movimentacao> listar() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}
	
	public Page<Movimentacao> search (String nome, Integer page,Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
				
		return repo. searchQuery(pageRequest);
	}
	
	public Movimentacao findById(Integer id) {
		Optional<Movimentacao> obj = repo.findById(id);

		return obj. orElseThrow(() -> new ObjectNotFoudException("Objet non trouvé"));
	}
	
	@Transactional
	public boolean insert(Movimentacao obj) {
		
		List<Movimentacao> movimentacaoPosteriores = repo.obterMovimentacoesPosteriores(obj.getData(),obj.getProduto().getId());
		boolean podeInserir = true;
		
		Produto produto = produtoService.findById(obj.getProduto().getId());
		
		//ajustar data movimentacao
		ajustarDataMovimentacao(movimentacaoPosteriores,obj);
		
		//SAÌDA
		if (obj.getLoteMovimentacao().getTipoMovimentacaoEnum().equals(TipoMovimentacaoEnum.SAIDA)) {
			podeInserir = !saldoNegativo(movimentacaoPosteriores,obj,produto);
			if (!podeInserir) {
				return podeInserir;
			} 
		}
		
		
		atualizarSaldosMovEtProd(movimentacaoPosteriores,obj,produto);
		
		produtoService.update(produto);
		repo.save(obj);
		return podeInserir;
	}
	
	private void ajustarDataMovimentacao(List<Movimentacao> movimentacaoPosteriores, Movimentacao obj) {
		
		int contMovMesmoDia = 0;
		List <Movimentacao> listaMovimentacaoExcluir = new ArrayList<>();
		
		if (movimentacaoPosteriores.size() > 0) {
			for (Movimentacao movimentacao : movimentacaoPosteriores) {
				if (movimentacao.getData().getDayOfMonth() == obj.getData().getDayOfMonth() &&
						movimentacao.getData().getMonth() == obj.getData().getMonth() && 
								movimentacao.getData().getYear() == obj.getData().getYear()
						) {
					contMovMesmoDia++;
					listaMovimentacaoExcluir.add(movimentacao);
					
				}
			}
		}
		
		movimentacaoPosteriores.removeAll(listaMovimentacaoExcluir);
		obj.setData(LocalDateTime.of(obj.getData().getYear(),obj.getData().getMonth(), obj.getData().getDayOfMonth(), 0, 0, 0));
		obj.setData(obj.getData().plusSeconds(contMovMesmoDia));
	}
	
	private void atualizarSaldosMovEtProd(List<Movimentacao> movimentacaoPosteriores, Movimentacao obj, Produto produto) {
		Float quantidadeMov = 0f;
		Float valorMov = 0f;
		
		if (obj.getLoteMovimentacao().getTipoMovimentacaoEnum().equals(TipoMovimentacaoEnum.ENTRADA)) {
			quantidadeMov = obj.getQuantidade();
			valorMov = obj.getValor();
			produto.setQuantidadeHistoricaTotal(NumeroUtil.somarDinheiro(produto.getQuantidadeHistoricaTotal(), obj.getQuantidade(), 10));
			produto.setValorHistoricoTotal(NumeroUtil.somarDinheiro(produto.getValorHistoricoTotal(), obj.getValor(), 10));
			
		}
		else {
			quantidadeMov = obj.getQuantidade() *-1;
			valorMov = obj.getValor()*-1;
		}
		
		
		if (movimentacaoPosteriores.size() > 0) {
			obj.setQuantidadeUltimo(movimentacaoPosteriores.get(0).getQuantidadeUltimo());
			obj.setSaldoUltimo(movimentacaoPosteriores.get(0).getSaldoUltimo());
		} else {
			obj.setSaldoUltimo(produto.getSaldoEstoque());
			obj.setQuantidadeUltimo(produto.getQuantidadeEstoque());
		}
		
		
		
		for (Movimentacao movimentacao : movimentacaoPosteriores) {
			movimentacao.setQuantidadeUltimo(NumeroUtil.somarDinheiro(movimentacao.getQuantidadeUltimo(), quantidadeMov, 5));
			movimentacao.setSaldoUltimo(NumeroUtil.somarDinheiro(movimentacao.getSaldoUltimo(), valorMov, 5));
		}
		
		//Atualizado estoque atual produto
		produto.setSaldoEstoque(NumeroUtil.somarDinheiro(produto.getSaldoEstoque(), valorMov, 5));
		produto.setQuantidadeEstoque(NumeroUtil.somarDinheiro(produto.getQuantidadeEstoque(), quantidadeMov, 5));
	
	}

	private boolean saldoNegativo(List<Movimentacao> movimentacaoPosteriores,Movimentacao mov, Produto produto) {
		
		Float quantidadeMov = mov.getQuantidade() *-1;
		Float valorMov = mov.getValor() *-1;
		Float quantidade = 0f;
		Float saldo = 0f;
		
		for (Movimentacao movimentacao : movimentacaoPosteriores) {
			
			if (movimentacao.getLoteMovimentacao().getTipoMovimentacaoEnum().equals(TipoMovimentacaoEnum.ENTRADA)) {
//				quantidade =  movimentacao.getQuantidadeUltimo() + movimentacao.getQuantidade() + quantidadeMov;
				quantidade = NumeroUtil.somarDinheiro(movimentacao.getQuantidadeUltimo(), quantidadeMov, 5);
//				quantidade = NumeroUtil.somarDinheiro(quantidade, quantidadeMov, 5);
				
//				saldo = movimentacao.getSaldoUltimo() + movimentacao.getValor() + valorMov;
				saldo = NumeroUtil.somarDinheiro(movimentacao.getSaldoUltimo(), valorMov, 5);
//				saldo = NumeroUtil.somarDinheiro(quantidade,valorMov,5);
				
				
				if(quantidade < 0 || saldo < 0) {
					return true;
				}
			} else {
//				quantidade = movimentacao.getQuantidadeUltimo() - movimentacao.getQuantidade() + quantidadeMov;
//				saldo = movimentacao.getSaldoUltimo() - movimentacao.getValor() + valorMov;;

				quantidade = NumeroUtil.diminuirDinheiro(movimentacao.getQuantidadeUltimo(),  movimentacao.getQuantidade(), 5);
				quantidade = NumeroUtil.somarDinheiro(quantidade, quantidadeMov, 5);
				
				saldo = NumeroUtil.diminuirDinheiro(movimentacao.getSaldoUltimo(), movimentacao.getValor(), 5);
				saldo = NumeroUtil.somarDinheiro(quantidade,valorMov,5);
				
				if(quantidade < 0 || saldo < 0) {
					return true;
				}
			}
			
		}
		
		quantidade = NumeroUtil.somarDinheiro(quantidadeMov,  produto.getQuantidadeEstoque(), 5);
		saldo = NumeroUtil.somarDinheiro(valorMov, produto.getSaldoEstoque(), 5) ;
		
		if(quantidade < 0 || saldo < 0) {
			return true;
		}
		
		return false;
		
		
		
	}


	public Movimentacao update(Movimentacao objAlterado) {
		Movimentacao objBanco = findById(objAlterado.getId());
		updateData(objBanco,objAlterado);
		return repo.save(objBanco);
	}
	
	public void deletar (Integer id) {
//		Movimentacao obj = findById(id);
//		obj.setStatus(Status.INATIVO);
//		repo.save(obj);
	}
	
	private void updateData(Movimentacao objBanco, Movimentacao objAnterado) {
		
//		objBanco.setDescricao(objAnterado.getDescricao());
//		objBanco.setMesFim(objAnterado.getMesFim());
//		objBanco.setMesInicio(objAnterado.getMesInicio());
//		objBanco.setNome(objAnterado.getNome());
//		objBanco.setStatus(objAnterado.getStatus());
		
	}

	
	
}
