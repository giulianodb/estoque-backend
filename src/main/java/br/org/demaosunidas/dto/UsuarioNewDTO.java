package br.org.demaosunidas.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.org.demaosunidas.domain.enums.Status;

public class UsuarioNewDTO implements Serializable {
	
	private Integer idUsuario;
	
	private String nome;
	
	private PapelDTO papel;
	
	private String loginUsuario;
	
	private String senha;
	
	private String senhaCripto;

	private String email;
	
	private Boolean participaExtra;
	
	private Float participacao;
	
	private String sobrenome;
	
	private Status statusUsuario;

	
	public String getSenhaCripto() {
		return senhaCripto;
	}

	public void setSenhaCripto(String senhaCripto) {
		this.senhaCripto = senhaCripto;
	}
	
	public Integer getIdUsuario() {
		return idUsuario;
	}
	
	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public PapelDTO getPapel() {
		return papel;
	}

	public void setPapel(PapelDTO papel) {
		this.papel = papel;
	}

	public String getLoginUsuario() {
		return loginUsuario;
	}

	public void setLoginUsuario(String loginUsuario) {
		this.loginUsuario = loginUsuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getParticipaExtra() {
		return participaExtra;
	}

	public void setParticipaExtra(Boolean participaExtra) {
		this.participaExtra = participaExtra;
	}

	public Float getParticipacao() {
		return participacao;
	}

	public void setParticipacao(Float participacao) {
		this.participacao = participacao;
	}

	public Status getStatusUsuario() {
		return statusUsuario;
	}

	public void setStatusUsuario(Status statusUsuario) {
		this.statusUsuario = statusUsuario;
	}
	
}