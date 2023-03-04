package br.org.demaosunidas.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="programas",schema="estoque")
public class ProgramasSociais implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name = "PROGRAMAS_SOCIAIS_ID", sequenceName = "id_prog_sociais_seq", schema="estoque",allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "PROGRAMAS_SOCIAIS_ID")
	@Column(name = "id")
	private Integer id;
	
	private Boolean bpc;
	
	private Boolean auxilioDoenca;
	
	private Boolean scfv;
	
	private Boolean bolsaFamilia;
	
	private Boolean minhaCasaMinhaVida;
	
	private Boolean tarifaSocial;
	
	private Boolean urbs;
	
	private Boolean adolescenteAprendiz;
	
	private Boolean armazemFamilia;
	
	private String cras;
	
	private String nis;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "pt_BR", timezone="America/Sao_Paulo")
	private Date dataValidadeNis;
	
	private String beneficioAssistencial;
	
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
		ProgramasSociais other = (ProgramasSociais) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Boolean getBpc() {
		return bpc;
	}

	public void setBpc(Boolean bpc) {
		this.bpc = bpc;
	}

	public Boolean getAuxilioDoenca() {
		return auxilioDoenca;
	}

	public void setAuxilioDoenca(Boolean auxilioDoenca) {
		this.auxilioDoenca = auxilioDoenca;
	}

	public Boolean getScfv() {
		return scfv;
	}

	public void setScfv(Boolean scfv) {
		this.scfv = scfv;
	}

	public Boolean getBolsaFamilia() {
		return bolsaFamilia;
	}

	public void setBolsaFamilia(Boolean bolsaFamilia) {
		this.bolsaFamilia = bolsaFamilia;
	}

	public Boolean getMinhaCasaMinhaVida() {
		return minhaCasaMinhaVida;
	}

	public void setMinhaCasaMinhaVida(Boolean minhaCasaMinhaVida) {
		this.minhaCasaMinhaVida = minhaCasaMinhaVida;
	}

	public Boolean getTarifaSocial() {
		return tarifaSocial;
	}

	public void setTarifaSocial(Boolean tarifaSocial) {
		this.tarifaSocial = tarifaSocial;
	}

	public Boolean getUrbs() {
		return urbs;
	}

	public void setUrbs(Boolean urbs) {
		this.urbs = urbs;
	}

	public Boolean getAdolescenteAprendiz() {
		return adolescenteAprendiz;
	}

	public void setAdolescenteAprendiz(Boolean adolescenteAprendiz) {
		this.adolescenteAprendiz = adolescenteAprendiz;
	}

	public Boolean getArmazemFamilia() {
		return armazemFamilia;
	}

	public void setArmazemFamilia(Boolean armazemFamilia) {
		this.armazemFamilia = armazemFamilia;
	}

	public String getCras() {
		return cras;
	}

	public void setCras(String cras) {
		this.cras = cras;
	}

	public String getNis() {
		return nis;
	}

	public void setNis(String nis) {
		this.nis = nis;
	}

	public String getBeneficioAssistencial() {
		return beneficioAssistencial;
	}

	public void setBeneficioAssistencial(String beneficioAssistencial) {
		this.beneficioAssistencial = beneficioAssistencial;
	}
	
}
