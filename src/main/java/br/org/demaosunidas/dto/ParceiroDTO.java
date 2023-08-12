package br.org.demaosunidas.dto;

import java.io.Serializable;
import java.util.List;


public class ParceiroDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private List<FamiliaDTO> listaFamilia;
	
	private List<DoadorDTO> listaDoador;
	
	private List<InstituicaoDTO> listaInstituicaoDTOs;

	public List<FamiliaDTO> getListaFamilia() {
		return listaFamilia;
	}

	public void setListaFamilia(List<FamiliaDTO> listaFamilia) {
		this.listaFamilia = listaFamilia;
	}

	public List<DoadorDTO> getListaDoador() {
		return listaDoador;
	}

	public void setListaDoador(List<DoadorDTO> listaDoador) {
		this.listaDoador = listaDoador;
	}

	public List<InstituicaoDTO> getListaInstituicaoDTOs() {
		return listaInstituicaoDTOs;
	}

	public void setListaInstituicaoDTOs(List<InstituicaoDTO> listaInstituicaoDTOs) {
		this.listaInstituicaoDTOs = listaInstituicaoDTOs;
	}
}
