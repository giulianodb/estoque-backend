package br.org.demaosunidas.dto;

import java.io.Serializable;

import br.org.demaosunidas.domain.Crianca;

public class CriancaDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private String nome;

	public CriancaDTO(Crianca crianca) {
		this.id = crianca.getId();
		this.nome = crianca.getNome();
	}
	
	public CriancaDTO() {
		super();	
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
}
