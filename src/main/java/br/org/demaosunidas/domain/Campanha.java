package br.org.demaosunidas.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.org.demaosunidas.util.DateUtil.MesesEnum;

@Entity
@Table(name="campanha",schema="estoque")
public class Campanha implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "CAMPANHA_ID", sequenceName = "id_campanha_seq", schema="estoque",allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "CAMPANHA_ID")
	@Column(name = "id")
	private Integer id;
	
	private String nome;
	
	private String descricao;
	
	@Enumerated
	@Column(name = "mesinicio")
	private MesesEnum mesInicio;
	
	@Enumerated
	@Column(name = "mesfim")
	private MesesEnum mesFim;
	
	@Enumerated
	@Column(name="status")
	private Status status;

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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public MesesEnum getMesInicio() {
		return mesInicio;
	}

	public void setMesInicio(MesesEnum mesInicio) {
		this.mesInicio = mesInicio;
	}

	public MesesEnum getMesFim() {
		return mesFim;
	}

	public void setMesFim(MesesEnum mesFim) {
		this.mesFim = mesFim;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
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
		Campanha other = (Campanha) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	


}
