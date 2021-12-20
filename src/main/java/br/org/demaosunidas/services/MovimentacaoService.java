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

import br.org.demaosunidas.domain.LoteMovimentacao;
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
			
			definirValorSaida(obj,movimentacaoPosteriores,produto);
			
			podeInserir = !saldoNegativo(movimentacaoPosteriores,obj,produto);
			if (!podeInserir) {
				return podeInserir;
			} 
		}
		
		
		atualizarSaldosMovEtProd(movimentacaoPosteriores,obj,produto,null,false ,null);
		
		produtoService.update(produto);
		repo.save(obj);
		return podeInserir;
	}
	
	private void definirValorSaida(Movimentacao obj, List<Movimentacao> movimentacaoPosteriores, Produto produto) {
		if (movimentacaoPosteriores.size() > 0) {
			float valorMedio = NumeroUtil.DividirDinheiro(movimentacaoPosteriores.get(0).getQuantidadeUltimo(), movimentacaoPosteriores.get(0).getSaldoUltimo(), 5);
			obj.setValor(NumeroUtil.multiplicarDinheiro(valorMedio, obj.getQuantidade(), 5));
		}
		else {
			float valorMedio = NumeroUtil.DividirDinheiro(produto.getQuantidadeEstoque(), produto.getSaldoEstoque(), 5);
			obj.setValor(NumeroUtil.multiplicarDinheiro(valorMedio, obj.getQuantidade(), 5));
		}
		
	}

	private void ajustarDataMovimentacao(List<Movimentacao> movimentacaoPosteriores, Movimentacao obj) {
		
		int segundos = 0;
		int minutos = 0;
		List <Movimentacao> listaMovimentacaoExcluir = new ArrayList<>();
		
		if (movimentacaoPosteriores.size() > 0) {
			for (Movimentacao movimentacao : movimentacaoPosteriores) {
				if (movimentacao.getData().getDayOfMonth() == obj.getData().getDayOfMonth() &&
						movimentacao.getData().getMonth() == obj.getData().getMonth() && 
								movimentacao.getData().getYear() == obj.getData().getYear()
						) {
					
					minutos = movimentacao.getData().getMinute(); 
					segundos = movimentacao.getData().getSecond(); 
					
					listaMovimentacaoExcluir.add(movimentacao);
					
				}
			}
		}
		
		segundos = (minutos * 60) + segundos + 1;
		
		movimentacaoPosteriores.removeAll(listaMovimentacaoExcluir);
		obj.setData(LocalDateTime.of(obj.getData().getYear(),obj.getData().getMonth(), obj.getData().getDayOfMonth(), 0, 0, 0));
		obj.setData(obj.getData().plusSeconds(segundos));
	}

	//responsável em atualizar os saldos de cada movimentação posterior
	//Atualiza o saldo do produto
	private void atualizarSaldosMovEtProd(List<Movimentacao> movimentacaoPosteriores, Movimentacao obj, Produto produto,Movimentacao objBanco, Boolean update,LocalDateTime dataMaisAntiga) {
		Float quantidadeMov = 0f;
		Float valorMov = 0f;
		float diffQuantidade = 0f;
		float diffValor = 0f;
		
		//Se for update definir difereça para uso posterior
		if(update) {
			diffQuantidade = NumeroUtil.diminuirDinheiro(obj.getQuantidade(),objBanco.getQuantidade(), 5);
			diffValor = NumeroUtil.diminuirDinheiro( obj.getValor(), objBanco.getValor(), 5);
		}
		
		if (obj.getLoteMovimentacao().getTipoMovimentacaoEnum().equals(TipoMovimentacaoEnum.ENTRADA)) {
			quantidadeMov = obj.getQuantidade();
			valorMov = obj.getValor();
			
			//Caso for entrada e alteração ele só atualiza o preço histórico com a diferença
			if (update) {
				produto.setQuantidadeHistoricaTotal(NumeroUtil.somarDinheiro(produto.getQuantidadeHistoricaTotal(), diffQuantidade, 10));
				produto.setValorHistoricoTotal(NumeroUtil.somarDinheiro(produto.getValorHistoricoTotal(), diffValor, 10));
					
			} else {
				produto.setQuantidadeHistoricaTotal(NumeroUtil.somarDinheiro(produto.getQuantidadeHistoricaTotal(), obj.getQuantidade(), 10));
				produto.setValorHistoricoTotal(NumeroUtil.somarDinheiro(produto.getValorHistoricoTotal(), obj.getValor(), 10));
			}
			
		}
		else {
			quantidadeMov = obj.getQuantidade() *-1;
			valorMov = obj.getValor()*-1;
		}
		
		if (dataMaisAntiga == null && update) {
			//Nesse caso não foi alterada a data e é uma alteração. Nao fazemos nada. Só fazemos caso for alteração
		} else {
			if (movimentacaoPosteriores.size() > 0) {
				obj.setQuantidadeUltimo(movimentacaoPosteriores.get(0).getQuantidadeUltimo());
				obj.setSaldoUltimo(movimentacaoPosteriores.get(0).getSaldoUltimo());
			} else {
				obj.setSaldoUltimo(produto.getSaldoEstoque());
				obj.setQuantidadeUltimo(produto.getQuantidadeEstoque());
			}

		}
		
		
		//Caso for update e data não ser alterado precisamos atualizar somente os valores com a diferença
		if (dataMaisAntiga == null && update) {
			
			for (Movimentacao movimentacao : movimentacaoPosteriores) {
				movimentacao.setQuantidadeUltimo(NumeroUtil.somarDinheiro(movimentacao.getQuantidadeUltimo(), diffQuantidade, 5));
				movimentacao.setSaldoUltimo(NumeroUtil.somarDinheiro(movimentacao.getSaldoUltimo(), diffValor, 5));
				
				//Devemos atualizar o valor das saídas baseado no valor médio
				if (movimentacao.getLoteMovimentacao().getTipoMovimentacaoEnum().equals(TipoMovimentacaoEnum.SAIDA)) {
					float valorMedio = NumeroUtil.DividirDinheiro(movimentacao.getQuantidadeUltimo(), movimentacao.getSaldoUltimo(), 5);
					movimentacao.setValor(NumeroUtil.multiplicarDinheiro(valorMedio, movimentacao.getQuantidade(), 5));
				}
				
			}
			
			
			//Atualizado estoque atual produto
			produto.setSaldoEstoque(NumeroUtil.somarDinheiro(produto.getSaldoEstoque(), diffValor, 5));
			produto.setQuantidadeEstoque(NumeroUtil.somarDinheiro(produto.getQuantidadeEstoque(), diffQuantidade, 5));
		} 
		
		
		
		else {
			for (Movimentacao movimentacao : movimentacaoPosteriores) {
				movimentacao.setQuantidadeUltimo(NumeroUtil.somarDinheiro(movimentacao.getQuantidadeUltimo(), quantidadeMov, 5));
				movimentacao.setSaldoUltimo(NumeroUtil.somarDinheiro(movimentacao.getSaldoUltimo(), valorMov, 5));
				
				//Devemos atualizar o valor das saídas baseado no valor médio
				if (movimentacao.getLoteMovimentacao().getTipoMovimentacaoEnum().equals(TipoMovimentacaoEnum.SAIDA)) {
					float valorMedio = NumeroUtil.DividirDinheiro(movimentacao.getQuantidadeUltimo(), movimentacao.getSaldoUltimo(), 5);
					movimentacao.setValor(NumeroUtil.multiplicarDinheiro(valorMedio, movimentacao.getQuantidade(), 5));
				}
			}
			
			
			//Atualizado estoque atual produto
			produto.setSaldoEstoque(NumeroUtil.somarDinheiro(produto.getSaldoEstoque(), valorMov, 5));
			produto.setQuantidadeEstoque(NumeroUtil.somarDinheiro(produto.getQuantidadeEstoque(), quantidadeMov, 5));
			
		}
		
		
		
		
		
		
	
	
	}
	
	//Verifica se a movimentação provocará saldo negativo
		
	private boolean saldoNegativo(List<Movimentacao> movimentacaoPosteriores,Movimentacao mov, Produto produto) {
		
		Float quantidadeMov = 0f;
		Float valorMov = 0f;
		
		
		if (mov.getLoteMovimentacao().getTipoMovimentacaoEnum().equals(TipoMovimentacaoEnum.SAIDA)) {
			quantidadeMov = mov.getQuantidade() *-1;
			valorMov = mov.getValor() *-1;
			
		} else {
			quantidadeMov = mov.getQuantidade();
			valorMov = mov.getValor();
			
		}
		
		Float quantidade = 0f;
		Float saldo = 0f;
		
		for (Movimentacao movimentacao : movimentacaoPosteriores) {
			
			if (movimentacao.getLoteMovimentacao().getTipoMovimentacaoEnum().equals(TipoMovimentacaoEnum.ENTRADA)) {
			
				quantidade = NumeroUtil.somarDinheiro(movimentacao.getQuantidadeUltimo(), quantidadeMov, 5);
				saldo = NumeroUtil.somarDinheiro(movimentacao.getSaldoUltimo(), valorMov, 5);
				
				if(quantidade < 0 || saldo < 0) {
					return true;
				}
			} else {
				/*
				 * 
				quantidade = NumeroUtil.diminuirDinheiro(movimentacao.getQuantidadeUltimo(),  movimentacao.getQuantidade(), 5);
				quantidade = NumeroUtil.somarDinheiro(quantidade, quantidadeMov, 5);
				
				saldo = NumeroUtil.diminuirDinheiro(movimentacao.getSaldoUltimo(), movimentacao.getValor(), 5);
				saldo = NumeroUtil.somarDinheiro(quantidade,valorMov,5);
				 */
				
				quantidade = NumeroUtil.somarDinheiro(movimentacao.getQuantidadeUltimo(), quantidadeMov, 5);
				saldo = NumeroUtil.somarDinheiro(movimentacao.getSaldoUltimo(), valorMov, 5);
				
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

	/**
	 * 
	 * @param objAlterado - Objeto que foi alterado
	 * @param dataMaisAntiga - Entre a data alterada e a data do banco esse é a data mais antiga
	 * @param alterarTodasPosteriores - Define se devemos ajustar as movimentações posterioes da lista toda ou precisamos chegar até a movimentação
	 * 									Essa validação serve para alteração de mov com data anterior a data antiga
	 * @return
	 * @throws Exception 
	 */
	public Movimentacao update(Movimentacao objAlterado, LocalDateTime dataMaisAntiga,boolean alterarTodasPosteriores,boolean desfazerTodosSaldosLista, boolean usarDataMovimentacao) throws Exception {
		Movimentacao objBanco = findById(objAlterado.getId());
		
		
		
		//Identificar as informações que foram alteradas:
		boolean valorAlterado = verificarValorAlterado(objAlterado,objBanco); 
		boolean quantidadeAlterada = verificarQuantidadeAlterada(objAlterado,objBanco);
		
		if (valorAlterado || quantidadeAlterada || dataMaisAntiga != null) {
			
			LocalDateTime dataParaPesquisa = dataMaisAntiga;
			if (usarDataMovimentacao) {
				dataParaPesquisa = objBanco.getData();
			}
			
			List<Movimentacao> movimentacoesPosteriores = repo.obterMovimentacoesPosteriores (dataParaPesquisa, objBanco.getProduto().getId());
			
			if (objBanco.getLoteMovimentacao().getTipoMovimentacaoEnum().equals(TipoMovimentacaoEnum.ENTRADA))
				updateSaida(objAlterado,objBanco,dataMaisAntiga,valorAlterado,quantidadeAlterada,movimentacoesPosteriores,alterarTodasPosteriores,desfazerTodosSaldosLista);
			else 
				updateSaida(objAlterado,objBanco,dataMaisAntiga,valorAlterado,quantidadeAlterada,movimentacoesPosteriores,alterarTodasPosteriores,desfazerTodosSaldosLista);
			
			updateData(objBanco,objAlterado,valorAlterado,quantidadeAlterada,dataMaisAntiga);
		}
		
		return repo.save(objBanco);
	}
	
	/**
	 * Alteração de movimentação de saída
	 * @param objAlterado - O objeto com as alterações
	 * @param objBanco - Objeto original do banco de dados
	 * @param dataMaisAntiga - A data mais antiga a danta alterada ou a data do objeto do banco
	 * @param valorAlterado - O valor alterado * Caso for null não foi alterado
	 * @param quantidadeAlterada - quantidade alterada * Caso for null não foi alterado
	 * @param movimentacoesPosteriores  - Lista de movimentações posteriores a data mais antiga
	 * @param alterarTodasPosteriores - Flag que indica se devemos ajustar todas as movimentações da lista de posteriores ou se devemos alterar
	 * 			somente as que estiverem após o onjeto encontrado do banco
	 *    
	 *    
	 *    <----------A--------|----------------------->
	 *               ---------_________________________ 
	 *                    
	 *    <-------------------|---------A------------->
	 *                        ________________________
	 * @throws Exception 
	 *                        
	 *                                 
	 */
	private void updateSaida(Movimentacao objAlterado, Movimentacao objBanco, LocalDateTime dataMaisAntiga,
			boolean valorAlterado, boolean quantidadeAlterada, List<Movimentacao> movimentacoesPosteriores, boolean alterarTodasPosteriores,boolean desfazerTodosSaldosLista) throws Exception {
		
		//ajustar data movimentacao
		//Verificar se a data foi alterada. Caso a data não foi alterada devemos manter a data
		if (dataMaisAntiga != null) {
			ajustarDataMovimentacao(movimentacoesPosteriores,objAlterado);
		}
		
		Produto produto = produtoService.findById(objAlterado.getProduto().getId());
		if (objBanco.getLoteMovimentacao().getTipoMovimentacaoEnum().equals(TipoMovimentacaoEnum.SAIDA)) {
			definirValorSaida(objAlterado,movimentacoesPosteriores,produto);
		}
		
		//para desfazer verifica se a data foi alterada
		if (dataMaisAntiga != null || quantidadeAlterada || valorAlterado) {
			desfazerSaldosMovimentacao(movimentacoesPosteriores,desfazerTodosSaldosLista,objBanco,produto);
		}	
			if (saldoNegativo(movimentacoesPosteriores, objAlterado, produto)) {
				throw new Exception("Negativo");
			}
			
			if (alterarTodasPosteriores) {
				atualizarSaldosMovEtProd( movimentacoesPosteriores, objAlterado,produto, objBanco,true,dataMaisAntiga);
			
			} 
			else {
//				movimentacoesPosteriores.stream().forEach(m -> this.teste(m,listaParaAlteracao,objBanco));
				
				atualizarSaldosMovEtProd(teste(movimentacoesPosteriores,objAlterado),objAlterado,produto,objBanco,true,dataMaisAntiga);
				
			}
			
		
		
	}
	
	
	public List<Movimentacao> teste(List<Movimentacao> movimentacoesPosteriores,Movimentacao obj) {
		List<Movimentacao> listaParaAlteracao = new ArrayList<Movimentacao>();
		
		for (Movimentacao movimentacao : movimentacoesPosteriores) {
			
			if (obj.getData().isBefore(movimentacao.getData())) {
				listaParaAlteracao.add(movimentacao);
			}
		}
		
		return listaParaAlteracao;
		
	}
	
	/**
	 * Responsável em desfazer os saldos e quantidade ultimos.
	 * 
	 * @param movimentacoesPosteriores
	 * @param desfazerTodosSaldosLista - Verifica se deve alterar toda a lista ou deve alterar somente apartir do código do banco
	 * 	Essa regra depende se a alteração foi anterior ou após a data atual do banco
	 * @param objBanco
	 */
	private void desfazerSaldosMovimentacao(List<Movimentacao> movimentacoesPosteriores,
			boolean desfazerTodosSaldosLista, Movimentacao objBanco, Produto produto) {
	
		float quantidade = 0f;
		float valor = 0f;
		
		if (objBanco.getLoteMovimentacao().getTipoMovimentacaoEnum().equals(TipoMovimentacaoEnum.ENTRADA)) {
			quantidade = objBanco.getQuantidade() *-1;
			valor = objBanco.getValor() *-1;
		}
		else {
			quantidade = objBanco.getQuantidade();
			valor = objBanco.getValor();
		}
		
		
		if (desfazerTodosSaldosLista) {
			for (Movimentacao movimentacao : movimentacoesPosteriores) {
				movimentacao.setQuantidadeUltimo(NumeroUtil.somarDinheiro(movimentacao.getQuantidadeUltimo(), quantidade, 5));
				movimentacao.setSaldoUltimo(NumeroUtil.somarDinheiro(movimentacao.getSaldoUltimo(), valor, 5));

			}
		} 
		else {
			
			boolean podeAlterar = false;
			for (Movimentacao movimentacao : movimentacoesPosteriores) {
				if (podeAlterar) {
					movimentacao.setQuantidadeUltimo(NumeroUtil.somarDinheiro(movimentacao.getQuantidadeUltimo(), quantidade, 5));
					movimentacao.setSaldoUltimo(NumeroUtil.somarDinheiro(movimentacao.getSaldoUltimo(), valor, 5));
				}
				
				if (movimentacao.getId().equals(objBanco.getId())) {
					podeAlterar = true;
				}
				
			

			}
		}
		
		
		//Atualizado estoque atual produto
		produto.setSaldoEstoque(NumeroUtil.somarDinheiro(produto.getSaldoEstoque(), valor, 5));
		produto.setQuantidadeEstoque(NumeroUtil.somarDinheiro(produto.getQuantidadeEstoque(), quantidade, 5));
		
	}

	private void updateEntrada(Movimentacao objAlterado, Movimentacao objBanco, LocalDateTime dataMaisAntiga,
			boolean valorAlterado, boolean quantidadeAlterada, List<Movimentacao> movimentacoesPosteriores,boolean alterarTodasPosteriores) {
		// TODO Auto-generated method stub
		
		
		
//		desfazerSaldosMovimentacao(movimentacoesPosteriores);
		
	}

	private boolean verificarQuantidadeAlterada(Movimentacao objAlterado, Movimentacao objBanco) {
		return !objAlterado.getQuantidade().equals(objBanco.getQuantidade());
	}
	
	/**
	 * Caso a movimentação for saída o valor alterado não é utilizado. Para saída o que importa é a quantidade pois o valor da saída é calculado após
	 * @param objAlterado
	 * @param objBanco
	 * @return
	 */
	private boolean verificarValorAlterado(Movimentacao objAlterado, Movimentacao objBanco) {
		
		if (objAlterado.getLoteMovimentacao().getTipoMovimentacaoEnum().equals(TipoMovimentacaoEnum.ENTRADA)) {
			return !objAlterado.getValor().equals(objBanco.getValor());
		} else {
			return !objAlterado.getQuantidade().equals(objBanco.getQuantidade());
		}
	
		
	}

	public void deletar (Integer id) {
//		Movimentacao obj = findById(id);
//		obj.setStatus(Status.INATIVO);
//		repo.save(obj);
	}
	
	private void updateData(Movimentacao objBanco, Movimentacao objAnterado, boolean valorAlterado, boolean quantidadeAlterada, LocalDateTime dataMaisAntiga) {
		
		if (dataMaisAntiga != null) {
			objBanco.setData(objAnterado.getData());
		}
		
		objBanco.setQuantidade(objAnterado.getQuantidade());
		if (valorAlterado) {
			objBanco.setValor(objAnterado.getValor());
			
		}
		objBanco.setQuantidadeUltimo(objAnterado.getQuantidadeUltimo());
		objBanco.setSaldoUltimo(objAnterado.getSaldoUltimo());
//		objBanco.setMesFim(objAnterado.getMesFim());
//		objBanco.setMesInicio(objAnterado.getMesInicio());
//		objBanco.setNome(objAnterado.getNome());
//		objBanco.setStatus(objAnterado.getStatus());
		
	}

	public List<Movimentacao> buscarMovimentacaoPorLote(Integer codLote){
		LoteMovimentacao lote = new LoteMovimentacao(codLote, null, null, null, null, null, null, null, null, null);
		return repo.findByLoteMovimentacao(lote);
	}
	
	public List<Movimentacao> buscarMovimentacaoPorProduto(Integer codProduto){
//		Produto produto = new Produto(codProduto);
		return repo.obterMovimentacoesPorProduto(codProduto);
	}
	
}
