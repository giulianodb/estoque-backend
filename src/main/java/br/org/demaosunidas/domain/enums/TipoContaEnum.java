package br.org.demaosunidas.domain.enums;


public enum TipoContaEnum {
	/**
	 * 
	 */
	CONTA_CORRENTE("Conta corrente"),
	INVESTIMENTO("Investimento"),
//	INVESTIMENTO("Investimento"),
	POUPANCA("Poupança");
	
	private String descricao;
	
	private TipoContaEnum(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	

	
	
}
