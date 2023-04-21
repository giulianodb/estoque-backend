package br.org.demaosunidas.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class InscricaoReportDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer totalInscritos = 0;
	
	private Integer totalEspera = 0;
	
	private Integer totalManha = 0;
	
	private Integer totalTarde = 0;
	
	private Integer totalManhaEspera = 0;
	
	private Integer totalTardeEspera = 0;
	
	private Integer totalFocar = 0;
	
	private Integer totalSCFV = 0;
	

	
	private List<InscricaoDTO> listInscricao = new ArrayList<>();

	public List<InscricaoDTO> getListInscricao() {
		return listInscricao;
	}

	public void setListInscricao(List<InscricaoDTO> listInscricao) {
		this.listInscricao = listInscricao;
	}

	public Integer getTotalInscritos() {
		return totalInscritos;
	}

	public void setTotalInscritos(Integer totalInscritos) {
		this.totalInscritos = totalInscritos;
	}

	public Integer getTotalManha() {
		return totalManha;
	}

	public void setTotalManha(Integer totalManha) {
		this.totalManha = totalManha;
	}

	public Integer getTotalTarde() {
		return totalTarde;
	}

	public void setTotalTarde(Integer totalTarde) {
		this.totalTarde = totalTarde;
	}

	public Integer getTotalFocar() {
		return totalFocar;
	}

	public void setTotalFocar(Integer totalFocar) {
		this.totalFocar = totalFocar;
	}

	public Integer getTotalSCFV() {
		return totalSCFV;
	}

	public void setTotalSCFV(Integer totalSCFV) {
		this.totalSCFV = totalSCFV;
	}

	public Integer getTotalEspera() {
		return totalEspera;
	}

	public void setTotalEspera(Integer totalEspera) {
		this.totalEspera = totalEspera;
	}

	public Integer getTotalManhaEspera() {
		return totalManhaEspera;
	}

	public void setTotalManhaEspera(Integer totalManhaEspera) {
		this.totalManhaEspera = totalManhaEspera;
	}

	public Integer getTotalTardeEspera() {
		return totalTardeEspera;
	}

	public void setTotalTardeEspera(Integer totalTardeEspera) {
		this.totalTardeEspera = totalTardeEspera;
	}
	
	
	
}
