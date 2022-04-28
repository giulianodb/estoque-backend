package br.org.demaosunidas.dto;

import java.io.Serializable;

import br.org.demaosunidas.domain.Produto;
import br.org.demaosunidas.domain.enums.Status;
import br.org.demaosunidas.domain.enums.TipoMedidaEnum;
import br.org.demaosunidas.domain.enums.TipoProdutoEnum;

public class ProdutoGetDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private String nome;
	
	private TipoProdutoEnum tipoProduto;
	
	private TipoMedidaEnum tipoMedida;
	
	private Float quantidadeEstoque;
	
	private Float saldoEstoque;
	
	private Float quantidadeHistoricaTotal;
	
	private Float valorHistoricoTotal;
	
	private String descricao;
	
//	@OneToMany(mappedBy = "produto",fetch = FetchType.LAZY)
//	@OrderBy("data asc")
//	private Set<Movimentacao> listaMovimentacao;
	
	//Listas usadas para separar as movimentacoes de entrada e saida
//	@Transient
//	private List<Movimentacao> movimentacaoEntrada;
//	@Transient
//	private List<Movimentacao> movimentacaoSaida;
	
	private Status status;
	
	

	public ProdutoGetDTO(Integer id, String nome, TipoProdutoEnum tipoProduto, TipoMedidaEnum tipoMedida,
		Float quantidadeEstoque, Float saldoEstoque, Float quantidadeHistoricaTotal, Float valorHistoricoTotal,
		String descricao, Status status) {
	super();
	this.id = id;
	this.nome = nome;
	this.tipoProduto = tipoProduto;
	this.tipoMedida = tipoMedida;
	this.quantidadeEstoque = quantidadeEstoque;
	this.saldoEstoque = saldoEstoque;
	this.quantidadeHistoricaTotal = quantidadeHistoricaTotal;
	this.valorHistoricoTotal = valorHistoricoTotal;
	this.descricao = descricao;
	this.status = status;
}
	
	public ProdutoGetDTO(Produto obj) {
		super();
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.tipoProduto = obj.getTipoProduto();
		this.tipoMedida = obj.getTipoMedida();
		this.quantidadeEstoque = obj.getQuantidadeEstoque();
		this.saldoEstoque = obj.getSaldoEstoque();
		this.quantidadeHistoricaTotal = obj.getQuantidadeHistoricaTotal();
		this.valorHistoricoTotal = obj.getValorHistoricoTotal();
		this.descricao = obj.getDescricao();
		this.status = obj.getStatus();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public TipoProdutoEnum getTipoProduto() {
		return tipoProduto;
	}

	public void setTipoProduto(TipoProdutoEnum tipoProduto) {
		this.tipoProduto = tipoProduto;
	}

	public TipoMedidaEnum getTipoMedida() {
		return tipoMedida;
	}

	public void setTipoMedida(TipoMedidaEnum tipoMedida) {
		this.tipoMedida = tipoMedida;
	}

	public Float getQuantidadeEstoque() {
		return quantidadeEstoque;
	}

	public void setQuantidadeEstoque(Float quantidadeEstoque) {
		this.quantidadeEstoque = quantidadeEstoque;
	}

	public Float getSaldoEstoque() {
		return saldoEstoque;
	}

	public void setSaldoEstoque(Float saldoEstoque) {
		this.saldoEstoque = saldoEstoque;
	}

	public Float getQuantidadeHistoricaTotal() {
		return quantidadeHistoricaTotal;
	}

	public void setQuantidadeHistoricaTotal(Float quantidadeHistoricaTotal) {
		this.quantidadeHistoricaTotal = quantidadeHistoricaTotal;
	}

	public Float getValorHistoricoTotal() {
		return valorHistoricoTotal;
	}

	public void setValorHistoricoTotal(Float valorHistoricoTotal) {
		this.valorHistoricoTotal = valorHistoricoTotal;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	
}
