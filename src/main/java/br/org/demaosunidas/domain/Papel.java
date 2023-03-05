package br.org.demaosunidas.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.org.demaosunidas.dto.PapelDTO;

@Entity
@Table(name="papel",schema="estoque")
	//@NamedQueries({ @NamedQuery(name = "listarPapel", query = "SELECT p FROM Papel p") 	
	//})
public class Papel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8996467323447003284L;
	@Id
	@SequenceGenerator(name = "PAPEL_ID", sequenceName = "id_papel_seq", schema="estoque",allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "PAPEL_ID")
	@Column(name = "id_papel")
	private Integer idPapel;
	
	private String descricaoPapel;
	
	@Column(name="nome_papel")
	private String nomePapel;
	
	
	public Papel(PapelDTO papel) {
		this.descricaoPapel = papel.getDescricaoPapel();
		this.idPapel = papel.getIdPapel();
		this.nomePapel = papel.getNomePapel();
	}
	
	public Papel() {
		// TODO Auto-generated constructor stub
	}
	
	public String getNomePapel() {
		return nomePapel;
	}
	public void setNomePapel(String nomePapel) {
		this.nomePapel = nomePapel;
	}
	public Integer getIdPapel() {
		return idPapel;
	}
	public void setIdPapel(Integer idPapel) {
		this.idPapel = idPapel;
	}
	public String getDescricaoPapel() {
		return descricaoPapel;
	}
	public void setDescricaoPapel(String descricaoPapel) {
		this.descricaoPapel = descricaoPapel;
	}
}