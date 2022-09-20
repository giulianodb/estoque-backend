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
@Table(name="saude",schema="estoque")
public class Saude implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name = "SAUDE_ID", sequenceName = "id_saude_seq", schema="estoque",allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SAUDE_ID")
	@Column(name = "id")
	private Integer id;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Crianca crianca;
	
	private String us;
	
	private Boolean recebeMedicamentosSUS;
	
	private String informacoes;
	
	private Boolean restricaoAlimentar;
	
	private String descricaoRestricaoAlimentar;
	
	public Saude() {
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

	public String getUs() {
		return us;
	}

	public void setUs(String us) {
		this.us = us;
	}

	public Boolean getRecebeMedicamentosSUS() {
		return recebeMedicamentosSUS;
	}

	public void setRecebeMedicamentosSUS(Boolean recebeMedicamentosSUS) {
		this.recebeMedicamentosSUS = recebeMedicamentosSUS;
	}

	public String getInformacoes() {
		return informacoes;
	}

	public void setInformacoes(String informacoes) {
		this.informacoes = informacoes;
	}

	public Boolean getRestricaoAlimentar() {
		return restricaoAlimentar;
	}

	public void setRestricaoAlimentar(Boolean restricaoAlimentar) {
		this.restricaoAlimentar = restricaoAlimentar;
	}

	public String getDescricaoRestricaoAlimentar() {
		return descricaoRestricaoAlimentar;
	}

	public void setDescricaoRestricaoAlimentar(String descricaoRestricaoAlimentar) {
		this.descricaoRestricaoAlimentar = descricaoRestricaoAlimentar;
	}

}
