package br.org.demaosunidas.dto;

import java.io.Serializable;

import br.org.demaosunidas.domain.Familia;

public class FamiliaDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private String nomeResponsavel;

	public FamiliaDTO(Familia familia) {
		if (familia != null) {
			this.id = familia.getId();
			this.nomeResponsavel = familia.getNomeResponsavel();
		}
	}
	
	public FamiliaDTO() {
		super();	
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNomeResponsavel() {
		return nomeResponsavel;
	}

	public void setNomeResponsavel(String nomeResponsavel) {
		this.nomeResponsavel = nomeResponsavel;
	}

}
