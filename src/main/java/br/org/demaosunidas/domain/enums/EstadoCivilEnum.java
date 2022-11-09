package br.org.demaosunidas.domain.enums;


public enum EstadoCivilEnum {
	
	SOLTEIRA("Solteira"),
	CASADA("Casada"),
	DIVORCIADA("Divorciada"),
	VIUVA("Viúva");	
	
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
