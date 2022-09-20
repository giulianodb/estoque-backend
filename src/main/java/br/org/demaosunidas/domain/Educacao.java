package br.org.demaosunidas.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="educacao",schema="estoque")
public class Educacao implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name = "EDUCACAO_ID", sequenceName = "id_educacao_seq", schema="estoque",allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "EDUCACAO_ID")
	@Column(name = "id")
	private Integer id;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Crianca crianca;
	
	private String nomeInstituicao;
	
	private String turno;
	
	private String ano;
	
	private String reforcoEscolcar;
	
	private String evasaoEscolar;
	
	private String retepencia;
	
	private String observacoes;
	
	public Educacao() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Crianca getCrianca() {
		return crianca;
	}

	public void setCrianca(Crianca crianca) {
		this.crianca = crianca;
	}

	public String getNomeInstituicao() {
		return nomeInstituicao;
	}

	public void setNomeInstituicao(String nomeInstituicao) {
		this.nomeInstituicao = nomeInstituicao;
	}

	public String getTurno() {
		return turno;
	}

	public void setTurno(String turno) {
		this.turno = turno;
	}

	public String getAno() {
		return ano;
	}

	public void setAno(String ano) {
		this.ano = ano;
	}

	public String getReforcoEscolcar() {
		return reforcoEscolcar;
	}

	public void setReforcoEscolcar(String reforcoEscolcar) {
		this.reforcoEscolcar = reforcoEscolcar;
	}

	public String getEvasaoEscolar() {
		return evasaoEscolar;
	}

	public void setEvasaoEscolar(String evasaoEscolar) {
		this.evasaoEscolar = evasaoEscolar;
	}

	public String getRetepencia() {
		return retepencia;
	}

	public void setRetepencia(String retepencia) {
		this.retepencia = retepencia;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

}
