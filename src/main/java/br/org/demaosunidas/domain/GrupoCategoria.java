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
import br.org.demaosunidas.domain.enums.TipoTransacaoEnum;

@Entity
@Table(name="grupo_categoria",schema="estoque")
public class GrupoCategoria implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name = "GRUPO_CATEGORIA_ID", sequenceName = "id_centro_custo_seq", schema="estoque",allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "GRUPO_CATEGORIA_ID")
	@Column(name = "id")
	private Integer id;
	
	private String nome;
	
	private TipoTransacaoEnum tipoTransacao;
	
	@Enumerated
	private Status status;
	
	@OneToMany(mappedBy = "grupoCategoria",fetch = FetchType.EAGER)
	private List<Categoria> listCategoria;
	
	public GrupoCategoria() {
		// TODO Auto-generated constructor stub
	}
	
	public GrupoCategoria(Integer id) {
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

	public TipoTransacaoEnum getTipoTransacao() {
		return tipoTransacao;
	}

	public void setTipoTransacao(TipoTransacaoEnum tipoTransacao) {
		this.tipoTransacao = tipoTransacao;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public List<Categoria> getListCategoria() {
		return listCategoria;
	}

	public void setListCategoria(List<Categoria> listCategoria) {
		this.listCategoria = listCategoria;
	}

}
