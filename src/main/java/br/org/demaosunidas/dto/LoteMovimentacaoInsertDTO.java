package br.org.demaosunidas.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.org.demaosunidas.domain.enums.TipoMovimentacaoEnum;
public class LoteMovimentacaoInsertDTO implements Serializable{
	private static final long serialVersionUID = 8996466788745786863L;
	
	private String numeroEntrada;
	
	private Integer idCampanha;
	
	private Integer idInstituicao;
	
	private Integer idDoador;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private LocalDateTime data;
	
	private Integer idFamilia;
	
	private TipoMovimentacaoEnum tipoMovimentacaoEnum;
	
	private List<MovimentacaoInsertDTO> listMovimentacao;

	public String getNumeroEntrada() {
		return numeroEntrada;
	}

	public void setNumeroEntrada(String numeroEntrada) {
		this.numeroEntrada = numeroEntrada;
	}

	public Integer getIdCampanha() {
		return idCampanha;
	}

	public void setIdCampanha(Integer idCampanha) {
		this.idCampanha = idCampanha;
	}

	public Integer getIdInstituicao() {
		return idInstituicao;
	}

	public void setIdInstituicao(Integer idInstituicao) {
		this.idInstituicao = idInstituicao;
	}

	public Integer getIdDoador() {
		return idDoador;
	}

	public void setIdDoador(Integer idDoador) {
		this.idDoador = idDoador;
	}


	public Integer getIdFamilia() {
		return idFamilia;
	}

	public void setIdFamilia(Integer idFamilia) {
		this.idFamilia = idFamilia;
	}

	public TipoMovimentacaoEnum getTipoMovimentacaoEnum() {
		return tipoMovimentacaoEnum;
	}

	public void setTipoMovimentacaoEnum(TipoMovimentacaoEnum tipoMovimentacaoEnum) {
		this.tipoMovimentacaoEnum = tipoMovimentacaoEnum;
	}

	public List<MovimentacaoInsertDTO> getListMovimentacao() {
		return listMovimentacao;
	}

	public void setListMovimentacao(List<MovimentacaoInsertDTO> listMovimentacao) {
		this.listMovimentacao = listMovimentacao;
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}
	
	
}
