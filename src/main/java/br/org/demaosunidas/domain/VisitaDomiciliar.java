package br.org.demaosunidas.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="visita_domiciliar",schema="estoque")
public class VisitaDomiciliar implements Serializable{

	private static final long serialVersionUID = 3139197625517005269L;
	
	@Id
	@SequenceGenerator(name = "VISITA_ID", sequenceName = "id_visita_seq", schema="estoque",allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "VISITA_ID")
	private Integer id;
	
	private Boolean segunda;
	
	private Boolean terca;
	
	private Boolean quarta;
	
	private Boolean quinta;
	
	private Boolean sexta;
	
	private Boolean sabado;
	
	private Boolean manha;
	
	private Boolean tarde;
	
	private String observacoes;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Boolean getSegunda() {
		return segunda;
	}

	public void setSegunda(Boolean segunda) {
		this.segunda = segunda;
	}

	public Boolean getTerca() {
		return terca;
	}

	public void setTerca(Boolean terca) {
		this.terca = terca;
	}

	public Boolean getQuarta() {
		return quarta;
	}

	public void setQuarta(Boolean quarta) {
		this.quarta = quarta;
	}

	public Boolean getQuinta() {
		return quinta;
	}

	public void setQuinta(Boolean quinta) {
		this.quinta = quinta;
	}

	public Boolean getSexta() {
		return sexta;
	}

	public void setSexta(Boolean sexta) {
		this.sexta = sexta;
	}

	public Boolean getManha() {
		return manha;
	}

	public void setManha(Boolean manha) {
		this.manha = manha;
	}

	public Boolean getTarde() {
		return tarde;
	}

	public void setTarde(Boolean tarde) {
		this.tarde = tarde;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public Boolean getSabado() {
		return sabado;
	}

	public void setSabado(Boolean sabado) {
		this.sabado = sabado;
	}

	
}
