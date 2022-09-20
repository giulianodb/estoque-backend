package br.org.demaosunidas.dto;

import java.io.Serializable;
import java.util.Date;

import br.org.demaosunidas.domain.AvaliacaoContextoResposta;

public class AvaliacaoContextoRespostaDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private AvaliacaoContextoPerguntasDTO avaliacaoContextoPergunta;
	
	private Integer resposta;
	
	private Date data;
	
	private String observacao;
	
	public AvaliacaoContextoRespostaDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public AvaliacaoContextoRespostaDTO(AvaliacaoContextoResposta obj) {
		this.setAvaliacaoContextoPergunta(new AvaliacaoContextoPerguntasDTO(obj.getAvaliacaoContexto()));
		this.setData(obj.getData());
		this.setId(obj.getId());
		this.setObservacao(obj.getObservacao());
		this.setResposta(obj.getResposta());
	}

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public AvaliacaoContextoPerguntasDTO getAvaliacaoContextoPergunta() {
		return avaliacaoContextoPergunta;
	}

	public void setAvaliacaoContextoPergunta(AvaliacaoContextoPerguntasDTO avaliacaoContextoPergunta) {
		this.avaliacaoContextoPergunta = avaliacaoContextoPergunta;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}


}
