package br.org.demaosunidas.domain.enums;


public enum SituacaoMoradiaEnum {
	
	INSALUBRE("Insalubre"),
	IRREGULAR("Irregular"),
	LEGALIZADA("Legalizada");
	
	private String descricao;
	private SituacaoMoradiaEnum(String descricao) {
		this.descricao = descricao;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
