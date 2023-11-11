package br.org.demaosunidas.dto;

import java.io.Serializable;
import java.util.List;

public class ListaCategoriasDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private List<GrupoCategoriaDTO> receitas;
	
	private List<GrupoCategoriaDTO>  despesas;

	public List<GrupoCategoriaDTO> getReceitas() {
		return receitas;
	}

	public void setReceitas(List<GrupoCategoriaDTO> receitas) {
		this.receitas = receitas;
	}

	public List<GrupoCategoriaDTO> getDespesas() {
		return despesas;
	}

	public void setDespesas(List<GrupoCategoriaDTO> despesas) {
		this.despesas = despesas;
	}

	
}
