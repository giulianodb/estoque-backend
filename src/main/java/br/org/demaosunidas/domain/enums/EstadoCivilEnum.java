package br.org.demaosunidas.domain.enums;


public enum EstadoCivilEnum {
	
	NFI("NFI"),
	FOCAR("Focar");
	
	private String descricao;
	private String abreviatura;
	private EstadoCivilEnum(String descricao) {
		this.descricao = descricao;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getAbreviatura() {
		return abreviatura;
	}
	public void setAbreviatura(String abreviatura) {
		this.abreviatura = abreviatura;
	}
}
