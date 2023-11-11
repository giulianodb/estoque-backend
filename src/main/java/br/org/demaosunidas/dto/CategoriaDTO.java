package br.org.demaosunidas.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Objects;

import br.org.demaosunidas.domain.enums.Status;

public class CategoriaDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private String nome;
	
	private Status status;
	
	private BigDecimal soma;
	
	private GrupoCategoriaDTO grupoCategoria;
	
	private List<TransacaoDTO> listaTransacao;
	
	public CategoriaDTO() {
	}
	
	public CategoriaDTO(Integer id) {
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

	public GrupoCategoriaDTO getGrupoCategoria() {
		return grupoCategoria;
	}

	public void setGrupoCategoria(GrupoCategoriaDTO grupoCategoria) {
		this.grupoCategoria = grupoCategoria;
	}

	public List<TransacaoDTO> getListaTransacao() {
		return listaTransacao;
	}

	public void setListaTransacao(List<TransacaoDTO> listaTransacao) {
		this.listaTransacao = listaTransacao;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CategoriaDTO other = (CategoriaDTO) obj;
		return Objects.equals(id, other.id);
	}

	public BigDecimal getSoma() {
		return soma;
	}

	public void setSoma(BigDecimal soma) {
		this.soma = soma;
	}

}
