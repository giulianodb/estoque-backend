package br.org.demaosunidas.dto;

import java.io.Serializable;

import br.org.demaosunidas.domain.TipoMedidaEnum;
import br.org.demaosunidas.domain.TipoProdutoEnum;

public class ProdutoInsertDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String nome;
	
	private TipoProdutoEnum tipoProduto;
	
	private TipoMedidaEnum tipoMedida;
	
	private String descricao;

	public ProdutoInsertDTO(String nome, TipoProdutoEnum tipoProduto, TipoMedidaEnum tipoMedida,
			String descricao) {
		super();
		this.nome = nome;
		this.tipoProduto = tipoProduto;
		this.tipoMedida = tipoMedida;
		this.descricao = descricao;
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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	
}
