package br.org.demaosunidas.domain.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum TipoParceiroEnum {
	/**
	 * 
	 */
	FAMILIA(0,"Família"),
	DOADOR(1,"Doador"),
	INSTITUICAO(2,"Instituição"),
	CPF(3,"Cliente PF"),
	FPF(4,"Fornecedor PF"),
	CPJ(5,"Cliente PJ "),
	FPJ(6,"Fornecedor PJ"),
	ANONIMO(7,"Anônimo");
	
	private String descricao;
	private int cod;
	
	private TipoParceiroEnum(int cod, String descricao) {
		this.descricao = descricao;
		this.cod = cod;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getCod() {
		return cod;
	}

	public void setCod(int cod) {
		this.cod = cod;
	}
	
}
