package br.org.demaosunidas.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.org.demaosunidas.domain.AvaliacaoContextoResposta;
import br.org.demaosunidas.domain.Crianca;
import br.org.demaosunidas.domain.enums.ProjetoEnum;
import br.org.demaosunidas.domain.enums.Status;
import br.org.demaosunidas.domain.enums.TipoAvaliacaoContextoEnum;

public class CriancaGetDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String nome;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "pt_BR", timezone="America/Sao_Paulo")
	private Date dataNascimento;
	private Status status;
	
	private ProjetoEnum projeto;
	private FamiliaDTO familia;
	
	private Boolean listaEspera;
	private Boolean matriculado;
	
	private Integer totalAfirmacao;
	private Integer totalSituacao;
	
	public CriancaGetDTO () {
		
	}
	
	public CriancaGetDTO (Crianca obj) {
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.dataNascimento = obj.getDataNascimento();
		
		this.status = obj.getStatus();
		
//		this.projeto = obj.getProjeto();
		
		this.familia = new FamiliaDTO(obj.getFamilia());
//		this.listaEspera = obj.getListaEspera();
//		this.matriculado = obj.getMatriculado();
		
		int somaAfirmacao = 0;
		int somaSituacao = 0;
		
		for (AvaliacaoContextoResposta r : obj.getListRespostaAvaliacaoContexto()) {
			if (r.getAvaliacaoContexto().getTipoAvaliacao().equals(TipoAvaliacaoContextoEnum.AFIRMACAO)) {
				somaAfirmacao = somaAfirmacao + r.getResposta();
				
			}
			else {
				somaSituacao = somaSituacao + r.getResposta(); 
			}
		}
		
		this.totalAfirmacao = somaAfirmacao;
		this.totalSituacao = somaSituacao;
		
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
	public Date getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
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
	public FamiliaDTO getFamilia() {
		return familia;
	}
	public void setFamilia(FamiliaDTO familia) {
		this.familia = familia;
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
	public Integer getTotalAfirmacao() {
		return totalAfirmacao;
	}
	public void setTotalAfirmacao(Integer totalAfirmacao) {
		this.totalAfirmacao = totalAfirmacao;
	}
	public Integer getTotalSituacao() {
		return totalSituacao;
	}
	public void setTotalSituacao(Integer totalSituacao) {
		this.totalSituacao = totalSituacao;
	}
	
}
