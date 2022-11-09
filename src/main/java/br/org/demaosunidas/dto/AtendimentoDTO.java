package br.org.demaosunidas.dto;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;

public class AtendimentoDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String registro;
	
	private Integer id;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private Date dataAtendimento;
	
	private CriancaDTO crianca;

	public String getRegistro() {
		return registro;
	}

	public void setRegistro(String registro) {
		this.registro = registro;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDataAtendimento() {
		return dataAtendimento;
	}

	public void setDataAtendimento(Date dataAtendimento) {
		this.dataAtendimento = dataAtendimento;
	}

	public CriancaDTO getCrianca() {
		return crianca;
	}

	public void setCrianca(CriancaDTO crianca) {
		this.crianca = crianca;
	}
}
