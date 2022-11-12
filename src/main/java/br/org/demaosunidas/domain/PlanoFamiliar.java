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

@Entity
@Table(name="plano_familiar",schema="estoque")
public class PlanoFamiliar implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name = "PLANO_FAMILIAR_ID", sequenceName = "id_plano_familiar_seq", schema="estoque",allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "PLANO_FAMILIAR_ID")
	@Column(name = "id")
	private Integer id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Familia familia;
	
	private String nome;
	
	private String vinculo;
	
	private String meta;
	
	
	public PlanoFamiliar() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public PlanoFamiliar(PlanoFamiliarDTO dto) {
		this.id = dto.getId();
		this.nome = dto.getNome();
		this.vinculo = dto.getVinculo();
		this.meta = dto.getMeta();
		
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


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getVinculo() {
		return vinculo;
	}


	public void setVinculo(String vinculo) {
		this.vinculo = vinculo;
	}


	public String getMeta() {
		return meta;
	}


	public void setMeta(String meta) {
		this.meta = meta;
	}

}
