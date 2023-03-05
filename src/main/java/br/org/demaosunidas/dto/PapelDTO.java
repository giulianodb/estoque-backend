package br.org.demaosunidas.dto;

import java.io.Serializable;

public class PapelDTO implements Serializable{
	private static final long serialVersionUID = 8996467323447003284L;

	private Integer idPapel;
	
	private String descricaoPapel;
	
	private String nomePapel;
	
	public String getNomePapel() {
		return nomePapel;
	}
	public void setNomePapel(String nomePapel) {
		this.nomePapel = nomePapel;
	}
	public Integer getIdPapel() {
		return idPapel;
	}
	public void setIdPapel(Integer idPapel) {
		this.idPapel = idPapel;
	}
	public String getDescricaoPapel() {
		return descricaoPapel;
	}
	public void setDescricaoPapel(String descricaoPapel) {
		this.descricaoPapel = descricaoPapel;
	}
}