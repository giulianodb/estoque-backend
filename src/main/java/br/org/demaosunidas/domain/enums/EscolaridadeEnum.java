package br.org.demaosunidas.domain.enums;


public enum EscolaridadeEnum {
	
	PRIMEIRO_IMCOMPLETO("Primeiro grau imcompleto"),
	PRIMEIRO_COMPLETO("Primeiro grau completo"),
	SEGUNDO_IMCOMPLETO("Segundo grau imcompleto"),
	SEGUNDO_COMPLETO("Segundo grau completo"),
	SUPERIOR_IMCOMPLETO("Ensino superior imcompleto"),
	SUPERIOR_COMPLETO("Ensino superior completo");
	
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
