package br.org.demaosunidas.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.org.demaosunidas.domain.enums.PeriodoEnum;
import br.org.demaosunidas.domain.enums.ProjetoEnum;
import br.org.demaosunidas.domain.enums.Status;
import br.org.demaosunidas.dto.CriancaDTO;
import br.org.demaosunidas.dto.InscricaoDTO;

@Entity
@Table(name="inscricao",schema="estoque")
public class Inscricao implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name = "INSCRICAO_ID", sequenceName = "id_inscricao_seq", schema="estoque",allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "INSCRICAO_ID")
	@Column(name = "id")
	private Integer id;
	
	@Enumerated
	@Column(name="status")
	private Status status;
	
	@Enumerated
	private ProjetoEnum projeto;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Crianca crianca;
	
	private Integer ano;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "pt_BR", timezone="America/Sao_Paulo")
	private Date dataInscricao;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "pt_BR", timezone="America/Sao_Paulo")
	private Date dataDesligamento;
	
	private Boolean listaEspera;
	
	private Boolean matriculado;
	
	@Enumerated
	private PeriodoEnum periodo;
	
	public Inscricao() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Inscricao(InscricaoDTO obj) {
		
		this.ano = obj.getAno();
		this.crianca = new Crianca (obj.getCrianca().getId());
		this.dataInscricao = obj.getDataInscricao();
		this.id = obj.getId();
		this.listaEspera = obj.getListaEspera();
		this.matriculado = obj.getMatriculado();
		this.projeto = obj.getProjeto();
		this.status = obj.getStatus();
		this.periodo = obj.getPeriodo();
		
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


	public Crianca getCrianca() {
		return crianca;
	}

	public void setCrianca(Crianca crianca) {
		this.crianca = crianca;
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

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public PeriodoEnum getPeriodo() {
		return periodo;
	}

	public void setPeriodo(PeriodoEnum periodo) {
		this.periodo = periodo;
	}

	public Date getDataDesligamento() {
		return dataDesligamento;
	}

	public void setDataDesligamento(Date dataDesligamento) {
		this.dataDesligamento = dataDesligamento;
	}

	
}
