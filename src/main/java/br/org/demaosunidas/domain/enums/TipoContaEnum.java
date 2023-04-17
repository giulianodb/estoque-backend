package br.org.demaosunidas.domain.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum TipoContaEnum {
	/**
	 * 
	 */
	CONTA_CORRENTE(0,"Conta corrente"),
	INVESTIMENTO(1,"Investimento"),
	POUPANCA(2,"Poupança");
	
	private String descricao;
	private int cod;
	
	private TipoContaEnum(int cod, String descricao) {
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
