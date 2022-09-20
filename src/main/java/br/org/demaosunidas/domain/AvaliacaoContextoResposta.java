package br.org.demaosunidas.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.org.demaosunidas.dto.AvaliacaoContextoRespostaDTO;

@Entity
@Table(name="avaliacao_contexto_resposta",schema="estoque")
public class AvaliacaoContextoResposta implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name = "AVALIACAO_CONTEXTO_RESPOSTA_ID", sequenceName = "id_avaliacao_contexto_resposta_seq", schema="estoque",allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "AVALIACAO_CONTEXTO_RESPOSTA_ID")
	@Column(name = "id")
	private Integer id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Crianca crianca;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private AvaliacaoContextoPerguntas avaliacaoContexto;
	
	private Integer resposta;
	
	private String observacao;
	
	private Date data;
	
	public AvaliacaoContextoResposta() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public AvaliacaoContextoResposta(AvaliacaoContextoRespostaDTO acrDto,Crianca crianca, AvaliacaoContextoPerguntas pergunta) {
		this.avaliacaoContexto = pergunta;
		this.crianca = crianca;
		this.data = acrDto.getData();
		this.observacao = acrDto.getObservacao();
		this.resposta = acrDto.getResposta();
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Crianca getCrianca() {
		return crianca;
	}

	public void setCrianca(Crianca crianca) {
		this.crianca = crianca;
	}

	public AvaliacaoContextoPerguntas getAvaliacaoContexto() {
		return avaliacaoContexto;
	}

	public void setAvaliacaoContexto(AvaliacaoContextoPerguntas avaliacaoContexto) {
		this.avaliacaoContexto = avaliacaoContexto;
	}

	public Integer getResposta() {
		return resposta;
	}

	public void setResposta(Integer resposta) {
		this.resposta = resposta;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}


}
