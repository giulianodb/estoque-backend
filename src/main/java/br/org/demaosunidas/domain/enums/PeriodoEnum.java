package br.org.demaosunidas.domain.enums;

public enum PeriodoEnum {

	MANHA("Manhã"),
	TARDE("Tarde");
	
	private String descricao;
	
	private PeriodoEnum(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}	
	
}