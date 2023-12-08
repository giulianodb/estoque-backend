package br.org.demaosunidas.dto;

import java.util.List;

public class FamiliasAtingidasGrafico {
	
//	public double totalReceita;
//	public double totalDespesa;
//	public String mes;
	private List<String> mes;
	private List<Long> quantidade;
	public List<String> getMes() {
		return mes;
	}
	public void setMes(List<String> mes) {
		this.mes = mes;
	}
	public List<Long> getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(List<Long> quantidade) {
		this.quantidade = quantidade;
	}
	
}
