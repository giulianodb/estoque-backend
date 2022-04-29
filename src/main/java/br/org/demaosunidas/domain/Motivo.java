package br.org.demaosunidas.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="motivo",schema="estoque")
public class Motivo implements Serializable{

	private static final long serialVersionUID = 3139197625517005269L;
	
	@Id
	@SequenceGenerator(name = "MOTIVO_ID", sequenceName = "id_motivo_seq", schema="estoque",allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "MOTIVO_ID")
	@Column(name = "id")
	private Integer id;
	
	private Boolean vagaSCFV;
	
	private Boolean solicitacaoDoacoes;
	
	private Boolean orientacaoTecnica;
	
	private String outros;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Boolean getVagaSCFV() {
		return vagaSCFV;
	}

	public void setVagaSCFV(Boolean vagaSCFV) {
		this.vagaSCFV = vagaSCFV;
	}

	public Boolean getSolicitacaoDoacoes() {
		return solicitacaoDoacoes;
	}

	public void setSolicitacaoDoacoes(Boolean solicitacaoDoacoes) {
		this.solicitacaoDoacoes = solicitacaoDoacoes;
	}

	public Boolean getOrientacaoTecnica() {
		return orientacaoTecnica;
	}

	public void setOrientacaoTecnica(Boolean orientacaoTecnica) {
		this.orientacaoTecnica = orientacaoTecnica;
	}

	public String getOutros() {
		return outros;
	}

	public void setOutros(String outros) {
		this.outros = outros;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Motivo other = (Motivo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
