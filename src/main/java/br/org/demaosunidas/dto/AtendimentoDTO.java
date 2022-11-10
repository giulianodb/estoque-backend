package br.org.demaosunidas.dto;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.org.demaosunidas.domain.Atendimento;

public class AtendimentoDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String registro;
	
	private Integer id;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "pt_BR", timezone="America/Sao_Paulo")
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private Date dataAtendimento;
	
	private CriancaDTO crianca;
	
	
	public AtendimentoDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public AtendimentoDTO(Atendimento obj) {
		this.id = obj.getId();
		this.dataAtendimento = obj.getDataAtendimento();
		this.registro = obj.getRegistroAtendimento();
		this.crianca = new CriancaDTO(obj.getCrianca());
	}
	
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
