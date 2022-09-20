package br.org.demaosunidas.dto;

import java.io.Serializable;

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

import org.aspectj.lang.reflect.PerClauseKind;

import br.org.demaosunidas.domain.AvaliacaoContextoPerguntas;
import br.org.demaosunidas.domain.enums.TipoAvaliacaoContextoEnum;

public class AvaliacaoContextoPerguntasDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private String descricao;
	
	private TipoAvaliacaoContextoEnum tipoAvaliacao;
	
	public AvaliacaoContextoPerguntasDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public AvaliacaoContextoPerguntasDTO(AvaliacaoContextoPerguntas perguntas ) {
		this.descricao = perguntas.getDescricao();
		this.id = perguntas.getId();
		this.tipoAvaliacao = perguntas.getTipoAvaliacao();
	}

	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getDescricao() {
		return descricao;
	}


	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	public TipoAvaliacaoContextoEnum getTipoAvaliacao() {
		return tipoAvaliacao;
	}


	public void setTipoAvaliacao(TipoAvaliacaoContextoEnum tipoAvaliacao) {
		this.tipoAvaliacao = tipoAvaliacao;
	}

}
