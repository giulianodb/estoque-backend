package br.org.demaosunidas.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.org.demaosunidas.domain.Inscricao;
import br.org.demaosunidas.domain.enums.PeriodoEnum;
import br.org.demaosunidas.domain.enums.ProjetoEnum;
import br.org.demaosunidas.domain.enums.Status;

public class InscricaoDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private Status status;
	
	private ProjetoEnum projeto;
	
	private CriancaGetDTO crianca;
	
	private Integer ano;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "pt_BR", timezone="America/Sao_Paulo")
	private Date dataInscricao;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "pt_BR", timezone="America/Sao_Paulo")
	private Date dataDesligamento;
	
	private boolean listaEspera;
	
	private Boolean matriculado;
	
	private PeriodoEnum periodo;
	public InscricaoDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public InscricaoDTO(Inscricao obj) {
		this.ano = obj.getAno();
		this.crianca = new CriancaGetDTO (obj.getCrianca());
		this.dataInscricao = obj.getDataInscricao();
		this.id = obj.getId();
		this.listaEspera = obj.getListaEspera();
		
		this.matriculado = !obj.getListaEspera() && obj.getDataDesligamento() == null;
		
		this.projeto = obj.getProjeto();
		this.status = obj.getStatus();
		this.periodo = obj.getPeriodo();
		this.dataDesligamento = obj.getDataDesligamento();
	
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public ProjetoEnum getProjeto() {
		return projeto;
	}

	public void setProjeto(ProjetoEnum projeto) {
		this.projeto = projeto;
	}

	public CriancaGetDTO getCrianca() {
		return crianca;
	}

	public void setCrianca(CriancaGetDTO crianca) {
		this.crianca = crianca;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public Date getDataInscricao() {
		return dataInscricao;
	}

	public void setDataInscricao(Date dataInscricao) {
		this.dataInscricao = dataInscricao;
	}

	public Boolean getListaEspera() {
		return listaEspera;
	}

	public void setListaEspera(Boolean listaEspera) {
		this.listaEspera = listaEspera;
	}

	public Boolean getMatriculado() {
		return matriculado;
	}

	public void setMatriculado(Boolean matriculado) {
		this.matriculado = matriculado;
	}

	public PeriodoEnum getPeriodo() {
		return periodo;
	}

	public void setPeriodo(PeriodoEnum periodo) {
		this.periodo = periodo;
	}

	public void setListaEspera(boolean listaEspera) {
		this.listaEspera = listaEspera;
	}

	public Date getDataDesligamento() {
		return dataDesligamento;
	}

	public void setDataDesligamento(Date dataDesligamento) {
		this.dataDesligamento = dataDesligamento;
	}

	
}
