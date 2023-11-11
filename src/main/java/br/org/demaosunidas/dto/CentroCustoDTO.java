package br.org.demaosunidas.dto;

import java.io.Serializable;

import javax.persistence.Enumerated;

import br.org.demaosunidas.domain.enums.Status;

public class CentroCustoDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private String nome;
	
	private String descricao;
	
	@Enumerated
	private Status status;
	
	public CentroCustoDTO() {
		// TODO Auto-generated constructor stub
	}
	
	
	public CentroCustoDTO(Integer id) {
		super();
		this.id = id;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getDescricao() {
		return descricao;
	}


	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	public Status getStatus() {
		return status;
	}


	public void setStatus(Status status) {
		this.status = status;
	}

}
