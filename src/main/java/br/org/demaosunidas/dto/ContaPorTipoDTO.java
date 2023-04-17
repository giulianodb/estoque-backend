package br.org.demaosunidas.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Enumerated;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.org.demaosunidas.domain.enums.TipoContaEnum;

public class ContaPorTipoDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Enumerated
	@JsonFormat(shape = JsonFormat.Shape.OBJECT)
	private TipoContaEnum tipoConta;
	
	private BigDecimal saldoContas;
	
	private List<ContaDTO> contas;

	public TipoContaEnum getTipoConta() {
		return tipoConta;
	}

	public void setTipoConta(TipoContaEnum tipoConta) {
		this.tipoConta = tipoConta;
	}

	public BigDecimal getSaldoContas() {
		return saldoContas;
	}

	public void setSaldoContas(BigDecimal saldoContas) {
		this.saldoContas = saldoContas;
	}

	public List<ContaDTO> getContas() {
		return contas;
	}

	public void setContas(List<ContaDTO> contas) {
		this.contas = contas;
	}
	
}
