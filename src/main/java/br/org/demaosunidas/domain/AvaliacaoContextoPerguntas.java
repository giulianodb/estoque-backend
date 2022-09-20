package br.org.demaosunidas.domain;

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

import br.org.demaosunidas.domain.enums.TipoAvaliacaoContextoEnum;

@Entity
@Table(name="avaliacao_contexto_perguntas",schema="estoque")
public class AvaliacaoContextoPerguntas implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name = "AVALIACAO_CONTEXTO_PERGUNTAS_ID", sequenceName = "id_avaliacao_contexto_perguntas_seq", schema="estoque",allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "AVALIACAO_CONTEXTO_PERGUNTAS_ID")
	@Column(name = "id")
	private Integer id;
	
	private String descricao;
	
	@Enumerated
	private TipoAvaliacaoContextoEnum tipoAvaliacao;
	
	
	public AvaliacaoContextoPerguntas() {
		super();
		// TODO Auto-generated constructor stub
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
