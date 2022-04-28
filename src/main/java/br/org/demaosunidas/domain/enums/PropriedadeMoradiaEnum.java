package br.org.demaosunidas.domain.enums;


public enum PropriedadeMoradiaEnum {
	
	INSALUBRE("Insalubre"),
	IRREGULAR("Irregular"),
	LEGALIZADA("Legalizada");
	
	private String descricao;
	private PropriedadeMoradiaEnum(String descricao) {
		this.descricao = descricao;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
