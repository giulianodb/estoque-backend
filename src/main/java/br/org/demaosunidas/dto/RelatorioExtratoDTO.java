package br.org.demaosunidas.dto;

import java.math.BigDecimal;
import java.util.List;

public class RelatorioExtratoDTO {
	
	
	private List<ContaDTO> listConta;
	
	private Integer totalLancamentos;
	
	private BigDecimal totalEntradas;
	
	private BigDecimal totalSaidas;
	
	private BigDecimal saldoFinal;
	
	private String periodo;
	
	private String dataEmissao;

	public List<ContaDTO> getListConta() {
		return listConta;
	}

	public void setListConta(List<ContaDTO> listConta) {
		this.listConta = listConta;
	}

	public Integer getTotalLancamentos() {
		return totalLancamentos;
	}

	public void setTotalLancamentos(Integer totalLancamentos) {
		this.totalLancamentos = totalLancamentos;
	}

	public BigDecimal getTotalEntradas() {
		return totalEntradas;
	}

	public void setTotalEntradas(BigDecimal totalEntradas) {
		this.totalEntradas = totalEntradas;
	}

	public BigDecimal getTotalSaidas() {
		return totalSaidas;
	}

	public void setTotalSaidas(BigDecimal totalSaidas) {
		this.totalSaidas = totalSaidas;
	}

	public BigDecimal getSaldoFinal() {
		return saldoFinal;
	}

	public void setSaldoFinal(BigDecimal saldoFinal) {
		this.saldoFinal = saldoFinal;
	}

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	public String getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(String dataEmissao) {
		this.dataEmissao = dataEmissao;
	}
	
	

}
