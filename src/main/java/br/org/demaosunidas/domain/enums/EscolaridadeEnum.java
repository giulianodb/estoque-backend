package br.org.demaosunidas.domain.enums;


public enum EscolaridadeEnum {
	
	PRIMEIRO_IMCOMPLERO("Primeiro grau imcompleto"),
	PRIMEIRO_COMPLERO("Primeiro grau completo"),
	SEGUNDO_IMCOMPLERO("Segundo grau imcompleto"),
	SEGUNDO_COMPLERO("Segundo grau completo"),
	SUPERIOR_IMCOMPLERO("Ensino superior imcompleto"),
	SUPERIOR_COMPLERO("Ensino superior completo");
	
	private String descricao;
	private EscolaridadeEnum(String descricao) {
		this.descricao = descricao;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
