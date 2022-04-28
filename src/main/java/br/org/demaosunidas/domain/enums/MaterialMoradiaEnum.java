package br.org.demaosunidas.domain.enums;


public enum MaterialMoradiaEnum {
	
	MADEIRA("Madeira"),
	ALVENARIA("Alvenaria"),
	MISTA("Mista");
	
	private String descricao;
	private MaterialMoradiaEnum(String descricao) {
		this.descricao = descricao;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
