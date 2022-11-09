package br.org.demaosunidas.domain.enums;


public enum TipoMoradiaEnum {
	
	APARTAMENTO("Apartamento"),
	CASA("Casa"),
	SOBRADO("Sobrado");
	
	private String descricao;
	private String abreviatura;
	private TipoMoradiaEnum(String descricao) {
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
