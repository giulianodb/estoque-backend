package br.org.demaosunidas.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.org.demaosunidas.domain.enums.MaterialMoradiaEnum;
import br.org.demaosunidas.domain.enums.PropriedadeMoradiaEnum;
import br.org.demaosunidas.domain.enums.SituacaoMoradiaEnum;
import br.org.demaosunidas.domain.enums.TipoMoradiaEnum;

@Entity
@Table(name="moradia",schema="estoque")
public class Moradia implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name = "MORADIA_ID", sequenceName = "id_prog_moradia_seq", schema="estoque",allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "MORADIA_ID")
	@Column(name = "id")
	private Integer id;
	
	private Boolean aguaEncanada;
	
	private Boolean redeEsgoto;
	
	private Boolean fossa;
	
	private Boolean luz;
	
	private Boolean internet;
	
	private Boolean coletaLixo;
	
	private Boolean areasLazer;
	
	private Boolean veiculoProprio;
	
	@Enumerated
	private TipoMoradiaEnum tipoMoradia;
	
	private Integer quantidadePecas;
	
	@Enumerated
	private MaterialMoradiaEnum materialMoradia;
	
	@Enumerated
	private PropriedadeMoradiaEnum propriedadeMoradia;
	
	@Enumerated
	private SituacaoMoradiaEnum situacaoMoradia;
	
	private Integer tempoResidencia;

	public Integer getId() {
		return id;
	}

	
	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Moradia other = (Moradia) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Boolean getAguaEncanada() {
		return aguaEncanada;
	}


	public void setAguaEncanada(Boolean aguaEncanada) {
		this.aguaEncanada = aguaEncanada;
	}


	public Boolean getRedeEsgoto() {
		return redeEsgoto;
	}


	public void setRedeEsgoto(Boolean redeEsgoto) {
		this.redeEsgoto = redeEsgoto;
	}


	public Boolean getFossa() {
		return fossa;
	}


	public void setFossa(Boolean fossa) {
		this.fossa = fossa;
	}


	public Boolean getLuz() {
		return luz;
	}


	public void setLuz(Boolean luz) {
		this.luz = luz;
	}


	public Boolean getInternet() {
		return internet;
	}


	public void setInternet(Boolean internet) {
		this.internet = internet;
	}


	public Boolean getColetaLixo() {
		return coletaLixo;
	}


	public void setColetaLixo(Boolean coletaLixo) {
		this.coletaLixo = coletaLixo;
	}


	public Boolean getAreasLazer() {
		return areasLazer;
	}


	public void setAreasLazer(Boolean areasLazer) {
		this.areasLazer = areasLazer;
	}


	public Boolean getVeiculoProprio() {
		return veiculoProprio;
	}


	public void setVeiculoProprio(Boolean veiculoProprio) {
		this.veiculoProprio = veiculoProprio;
	}


	public TipoMoradiaEnum getTipoMoradia() {
		return tipoMoradia;
	}


	public void setTipoMoradia(TipoMoradiaEnum tipoMoradia) {
		this.tipoMoradia = tipoMoradia;
	}


	public Integer getQuantidadePecas() {
		return quantidadePecas;
	}


	public void setQuantidadePecas(Integer quantidadePecas) {
		this.quantidadePecas = quantidadePecas;
	}


	public MaterialMoradiaEnum getMaterialMoradia() {
		return materialMoradia;
	}


	public void setMaterialMoradia(MaterialMoradiaEnum materialMoradia) {
		this.materialMoradia = materialMoradia;
	}


	public PropriedadeMoradiaEnum getPropriedadeMoradia() {
		return propriedadeMoradia;
	}


	public void setPropriedadeMoradia(PropriedadeMoradiaEnum propriedadeMoradia) {
		this.propriedadeMoradia = propriedadeMoradia;
	}


	public SituacaoMoradiaEnum getSituacaoMoradia() {
		return situacaoMoradia;
	}


	public void setSituacaoMoradia(SituacaoMoradiaEnum situacaoMoradia) {
		this.situacaoMoradia = situacaoMoradia;
	}


	public Integer getTempoResidencia() {
		return tempoResidencia;
	}


	public void setTempoResidencia(Integer tempoResidencia) {
		this.tempoResidencia = tempoResidencia;
	}

	
}
