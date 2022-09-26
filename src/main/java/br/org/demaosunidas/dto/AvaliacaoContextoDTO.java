package br.org.demaosunidas.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class AvaliacaoContextoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<AvaliacaoContextoRespostaDTO> listAvaliacaoContextoResposta;
	private Integer somaSim;
	private Integer somaNao;
	private Integer somaS;
	private Integer somaP;
	private Integer somaN;
	private Integer totalAfirmacao;
	private Integer totalSituacao;
	private Date data;
	private CriancaDTO crianca;
	private Boolean novo = false;

	public AvaliacaoContextoDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getSomaSim() {
		return somaSim;
	}

	public void setSomaSim(Integer somaSim) {
		this.somaSim = somaSim;
	}

	public Integer getSomaNao() {
		return somaNao;
	}

	public void setSomaNao(Integer somaNao) {
		this.somaNao = somaNao;
	}

	public Integer getSomaS() {
		return somaS;
	}

	public void setSomaS(Integer somaS) {
		this.somaS = somaS;
	}

	public Integer getSomaP() {
		return somaP;
	}

	public void setSomaP(Integer somaP) {
		this.somaP = somaP;
	}

	public Integer getSomaN() {
		return somaN;
	}

	public void setSomaN(Integer somaN) {
		this.somaN = somaN;
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

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public List<AvaliacaoContextoRespostaDTO> getListAvaliacaoContextoResposta() {
		return listAvaliacaoContextoResposta;
	}

	public void setListAvaliacaoContextoResposta(List<AvaliacaoContextoRespostaDTO> listAvaliacaoContextoResposta) {
		this.listAvaliacaoContextoResposta = listAvaliacaoContextoResposta;
	}

	public CriancaDTO getCrianca() {
		return crianca;
	}

	public void setCrianca(CriancaDTO crianca) {
		this.crianca = crianca;
	}

	public Boolean getNovo() {
		return novo;
	}

	public void setNovo(Boolean novo) {
		this.novo = novo;
	}

}
