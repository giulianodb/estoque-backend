package br.org.demaosunidas.domain.enums;


public enum TipoAvaliacaoContextoEnum {
	
	SITUACAO("Situacao","S"),
	AFIRMACAO("Afirmação","A");
	
	private String descricao;
	private String abreviatura;
	private TipoAvaliacaoContextoEnum(String descricao, String abreviatura) {
		this.descricao = descricao;
		this.abreviatura = abreviatura;
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
