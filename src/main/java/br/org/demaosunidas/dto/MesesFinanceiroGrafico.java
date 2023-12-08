package br.org.demaosunidas.dto;

import java.util.List;

public class MesesFinanceiroGrafico {
	
//	public double totalReceita;
//	public double totalDespesa;
//	public String mes;
	
	
	private List<String> mes;
	private List<Double> receita;
	private List<Double> despesa;
	public List<String> getMes() {
		return mes;
	}
	public void setMes(List<String> mes) {
		this.mes = mes;
	}
	public List<Double> getReceita() {
		return receita;
	}
	public void setReceita(List<Double> receita) {
		this.receita = receita;
	}
	public List<Double> getDespesa() {
		return despesa;
	}
	public void setDespesa(List<Double> despesa) {
		this.despesa = despesa;
	}
	
	
	
}
