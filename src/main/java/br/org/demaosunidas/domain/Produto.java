package br.org.demaosunidas.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="produto",schema="estoque")
public class Produto implements Serializable{

	private static final long serialVersionUID = 1L;
	
	
	@Id
	@SequenceGenerator(name = "PRODUTO_ID", sequenceName = "id_produto_seq", schema="estoque",allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "PRODUTO_ID")
	@Column(name = "id")
	private Integer id;
	
//	@Size(max=50)
	private String nome;
	
	@Column(name = "nomesemacento")
	private String nomeSemAcento;
	
	@Enumerated
	@Column(name = "tipoproduto")
	private TipoProdutoEnum tipoProduto;
	
	@Enumerated
	@Column(name = "tipomedida")
	private TipoMedidaEnum tipoMedida;
	
	@Column(name = "quantidadeestoque")
	private Float quantidadeEstoque;
	
	@Column(name = "saldoestoque")
	private Float saldoEstoque;
	
	//Campo para obter a média do produto quando necessário sem a necessidade de percorrer o banco sempre
	@Column(name = "quantidadehistoricatotal")
	private Float quantidadeHistoricaTotal;
	
	//Campo para obter a média do produto quando necessário sem a necessidade de percorrer o banco sempre
	@Column(name = "valorhistoricototal")
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
	
	@Enumerated
	@Column(name="status")
	private Status status;
	
	
	
	public Produto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Produto(Integer id, String nome, String nomeSemAcento, TipoProdutoEnum tipoProduto,
			TipoMedidaEnum tipoMedida, Float quantidadeEstoque, Float saldoEstoque, Float quantidadeHistoricaTotal,
			Float valorHistoricoTotal, String descricao, Status status) {
		super();
		this.id = id;
		this.nome = nome;
		this.nomeSemAcento = nomeSemAcento;
		this.tipoProduto = tipoProduto;
		this.tipoMedida = tipoMedida;
		this.quantidadeEstoque = quantidadeEstoque;
		this.saldoEstoque = saldoEstoque;
		this.quantidadeHistoricaTotal = quantidadeHistoricaTotal;
		this.valorHistoricoTotal = valorHistoricoTotal;
		this.descricao = descricao;
		this.status = status;
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

	public String getNomeSemAcento() {
		return nomeSemAcento;
	}

	public void setNomeSemAcento(String nomeSemAcento) {
		this.nomeSemAcento = nomeSemAcento;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
