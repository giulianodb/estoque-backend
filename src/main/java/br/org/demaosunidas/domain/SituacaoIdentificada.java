package br.org.demaosunidas.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.org.demaosunidas.dto.FamiliaDTO;
import br.org.demaosunidas.dto.PlanoFamiliarDTO;
import br.org.demaosunidas.dto.SituacaoIdentificadaDTO;

@Entity
@Table(name="situacao_identificada",schema="estoque")
public class SituacaoIdentificada implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name = "SITUACAO_IDENTIFICADA_ID", sequenceName = "id_situacao_identificada_seq", schema="estoque",allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SITUACAO_IDENTIFICADA_ID")
	@Column(name = "id")
	private Integer id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Familia familia;
	
	private String acao;
	
	private Integer prazo;
	
	private String resultadosEsperados;
	
	private String resultadosObtidos;
	
	private Boolean responsabilidadeTecnica;
	
	private Boolean responsabilidadeFamilia;
	
	
	public SituacaoIdentificada() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public SituacaoIdentificada(SituacaoIdentificadaDTO dto) {
		this.id = dto.getId();

		this.acao = dto.getAcao();
		
		this.prazo = dto.getPrazo();
		this.resultadosEsperados = dto.getResultadosEsperados();
		this.resultadosObtidos = dto.getResultadosObtidos();
		this.responsabilidadeFamilia = dto.getResponsabilidadeFamilia();
		this.responsabilidadeTecnica = dto.getResponsabilidadeTecnica();
		
		this.familia = new Familia(dto.getFamilia());
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Familia getFamilia() {
		return familia;
	}

	public void setFamilia(Familia familia) {
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
