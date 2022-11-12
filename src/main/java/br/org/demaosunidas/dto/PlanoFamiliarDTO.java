package br.org.demaosunidas.dto;

import java.io.Serializable;

import br.org.demaosunidas.domain.PlanoFamiliar;

public class PlanoFamiliarDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private FamiliaDTO familia;
	
	private String nome;
	
	private String vinculo;
	
	private String meta;
	
	public PlanoFamiliarDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public PlanoFamiliarDTO(PlanoFamiliar obj) {
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.vinculo = obj.getVinculo();
		this.meta = obj.getMeta();
		
		this.familia = new FamiliaDTO(obj.getFamilia());
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
