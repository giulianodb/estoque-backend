package br.org.demaosunidas.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import br.org.demaosunidas.domain.LoteMovimentacao;
import br.org.demaosunidas.domain.enums.TipoMovimentacaoEnum;

public class LoteMovimentacaoGetDTO implements Serializable{
	private static final long serialVersionUID = 8996466788745786863L;
	
	private Integer codigo;
	
	private String numeroEntrada;
	
	private String nomeCampanha;
	
	private String nomeInstituicao;
	
	private String nomeDoador;
	
	private LocalDateTime data;
	
	private String familia;
	
	private TipoMovimentacaoEnum tipoMovimentacaoEnum;
	

	public LoteMovimentacaoGetDTO(LoteMovimentacao lote) {
		
		this.codigo = lote.getCodigo();
		this.data = lote.getData();
		if (lote.getCampanha() != null) {
			this.nomeCampanha = lote.getCampanha().getNome();
		}
		if (lote.getDoador() != null) {
			this.nomeDoador = lote.getDoador().getNome();
		}
		if (lote.getInstituicao() != null) {
			this.nomeInstituicao = lote.getInstituicao().getNome();
		}
		this.numeroEntrada = lote.getNumeroEntrada();
		this.tipoMovimentacaoEnum = lote.getTipoMovimentacaoEnum();
		
		// TODO Auto-generated constructor stub
	}


	public LoteMovimentacaoGetDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getNumeroEntrada() {
		return numeroEntrada;
	}

	public void setNumeroEntrada(String numeroEntrada) {
		this.numeroEntrada = numeroEntrada;
	}

	public String getNomeCampanha() {
		return nomeCampanha;
	}

	public void setNomeCampanha(String nomeCampanha) {
		this.nomeCampanha = nomeCampanha;
	}

	public String getNomeInstituicao() {
		return nomeInstituicao;
	}

	public void setNomeInstituicao(String nomeInstituicao) {
		this.nomeInstituicao = nomeInstituicao;
	}

	public String getNomeDoador() {
		return nomeDoador;
	}

	public void setNomeDoador(String nomeDoador) {
		this.nomeDoador = nomeDoador;
	}



	public String getFamilia() {
		return familia;
	}

	public void setFamilia(String familia) {
		this.familia = familia;
	}

	public TipoMovimentacaoEnum getTipoMovimentacaoEnum() {
		return tipoMovimentacaoEnum;
	}

	public void setTipoMovimentacaoEnum(TipoMovimentacaoEnum tipoMovimentacaoEnum) {
		this.tipoMovimentacaoEnum = tipoMovimentacaoEnum;
	}


	public LocalDateTime getData() {
		return data;
	}


	public void setData(LocalDateTime data) {
		this.data = data;
	}

	
}
