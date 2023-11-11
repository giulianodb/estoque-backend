package br.org.demaosunidas.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.org.demaosunidas.domain.enums.Status;

@Entity
@Table(name="categoria",schema="estoque")
public class Categoria implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name = "CATEGORIA_ID", sequenceName = "id_categoria_seq", schema="estoque",allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "CATEGORIA_ID")
	@Column(name = "id")
	private Integer id;
	
	private String nome;
	
	@Enumerated
	private Status status;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private GrupoCategoria grupoCategoria;
	
//	@OneToMany(mappedBy = "id",fetch = FetchType.LAZY)
//	private List<Transacao> listTransacao;
	
	public Categoria() {
		// TODO Auto-generated constructor stub
	}
	
	
	public Categoria(Integer id) {
		super();
		this.id = id;
	}


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


	public Status getStatus() {
		return status;
	}


	public void setStatus(Status status) {
		this.status = status;
	}


	public GrupoCategoria getGrupoCategoria() {
		return grupoCategoria;
	}


	public void setGrupoCategoria(GrupoCategoria grupoCategoria) {
		this.grupoCategoria = grupoCategoria;
	}


//	public List<Transacao> getListTransacao() {
//		return listTransacao;
//	}
//
//
//	public void setListTransacao(List<Transacao> listTransacao) {
//		this.listTransacao = listTransacao;
//	}




}
