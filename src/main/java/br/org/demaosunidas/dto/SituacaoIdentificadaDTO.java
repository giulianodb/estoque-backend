package br.org.demaosunidas.dto;

import java.io.Serializable;

import br.org.demaosunidas.domain.SituacaoIdentificada;

public class SituacaoIdentificadaDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private FamiliaDTO familia;
	
	private String acao;
	
	private Integer prazo;
	
	private String resultadosEsperados;
	
	private String resultadosObtidos;
	
	private Boolean responsabilidadeTecnica;
	
	private Boolean responsabilidadeFamilia;
	
	
	public SituacaoIdentificadaDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public SituacaoIdentificadaDTO(SituacaoIdentificada dto) {
		this.id = dto.getId();

		this.acao = dto.getAcao();
		
		this.prazo = dto.getPrazo();
		this.resultadosEsperados = dto.getResultadosEsperados();
		this.resultadosObtidos = dto.getResultadosObtidos();
		this.responsabilidadeFamilia = dto.getResponsabilidadeFamilia();
		this.responsabilidadeTecnica = dto.getResponsabilidadeTecnica();
		
		this.familia = new FamiliaDTO(dto.getFamilia());
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public FamiliaDTO getFamilia() {
		return familia;
	}

	public void setFamilia(FamiliaDTO familia) {
		this.familia = familia;
	}

	public String getAcao() {
		return acao;
	}

	public void setAcao(String acao) {
		this.acao = acao;
	}

	public Integer getPrazo() {
		return prazo;
	}

	public void setPrazo(Integer prazo) {
		this.prazo = prazo;
	}

	public String getResultadosEsperados() {
		return resultadosEsperados;
	}

	public void setResultadosEsperados(String resultadosEsperados) {
		this.resultadosEsperados = resultadosEsperados;
	}

	public String getResultadosObtidos() {
		return resultadosObtidos;
	}

	public void setResultadosObtidos(String resultadosObtidos) {
		this.resultadosObtidos = resultadosObtidos;
	}

	public Boolean getResponsabilidadeTecnica() {
		return responsabilidadeTecnica;
	}

	public void setResponsabilidadeTecnica(Boolean responsabilidadeTecnica) {
		this.responsabilidadeTecnica = responsabilidadeTecnica;
	}

	public Boolean getResponsabilidadeFamilia() {
		return responsabilidadeFamilia;
	}

	public void setResponsabilidadeFamilia(Boolean responsabilidadeFamilia) {
		this.responsabilidadeFamilia = responsabilidadeFamilia;
	}

	


}
