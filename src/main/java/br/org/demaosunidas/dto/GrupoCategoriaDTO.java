package br.org.demaosunidas.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

import br.org.demaosunidas.domain.enums.Status;
import br.org.demaosunidas.domain.enums.TipoTransacaoEnum;

public class GrupoCategoriaDTO {
	
	private Integer id;
	
	private String nome;
	
	private TipoTransacaoEnum tipoTransacao;
	
	private Status status;
	
	private List<CategoriaDTO> listaCategoria;
	
	private BigDecimal valorTotal;
	
	private String valorTotalString;
	
	public GrupoCategoriaDTO() {
		super();
		// TODO Auto-generated constructor stub
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

	public List<CategoriaDTO> getListaCategoria() {
		return listaCategoria;
	}

	public void setListaCategoria(List<CategoriaDTO> listaCategoria) {
		this.listaCategoria = listaCategoria;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public String getValorTotalString() {
		return valorTotalString;
	}

	public void setValorTotalString(String valorTotalString) {
		this.valorTotalString = valorTotalString;
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
		GrupoCategoriaDTO other = (GrupoCategoriaDTO) obj;
		return Objects.equals(id, other.id);
	}
	
	
	
}
