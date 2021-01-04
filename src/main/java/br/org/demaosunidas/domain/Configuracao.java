package br.org.demaosunidas.domain;

import java.io.Serializable;

public class Configuracao implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Integer mes;
	private Integer ano;
	
	public Configuracao() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Configuracao(Integer id, Integer mes, Integer ano) {
		super();
		this.id = id;
		this.mes = mes;
		this.ano = ano;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getMes() {
		return mes;
	}
	public void setMes(Integer mes) {
		this.mes = mes;
	}
	public Integer getAno() {
		return ano;
	}
	public void setAno(Integer ano) {
		this.ano = ano;
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
		Configuracao other = (Configuracao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
