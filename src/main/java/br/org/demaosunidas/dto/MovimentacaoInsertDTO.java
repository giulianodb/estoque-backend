package br.org.demaosunidas.dto;

import java.io.Serializable;

public class MovimentacaoInsertDTO implements Serializable{
	
	private static final long serialVersionUID = 8996467323445786863L;
	private Integer id;
	
	private Integer idProduto;
	
	private Float quantidade;
	
	private Float valor;
	
	private String numeroNF;
	
	private String descricaoNota;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Integer idProduto) {
		this.idProduto = idProduto;
	}

	public Float getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Float quantidade) {
		this.quantidade = quantidade;
	}

	public Float getValor() {
		return valor;
	}

	public void setValor(Float valor) {
		this.valor = valor;
	}

	public String getNumeroNF() {
		return numeroNF;
	}

	public void setNumeroNF(String numeroNF) {
		this.numeroNF = numeroNF;
	}

	public String getDescricaoNota() {
		return descricaoNota;
	}

	public void setDescricaoNota(String descricaoNota) {
		this.descricaoNota = descricaoNota;
	}
	
}
