package br.org.demaosunidas.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;

import br.org.demaosunidas.domain.Saldo;

public class SaldoDTO implements Serializable{
	
	private static final long serialVersionUID = 8996467323445786863L;

	private Integer id;
	
	private ContaDTO conta;
	
	private BigDecimal valor;
	
	@Column(name="data")
	private LocalDateTime data;

	public SaldoDTO() {
		super();
	}
	
	public SaldoDTO(Saldo obj) {
		this.id = obj.getId();
		this.conta = new ContaDTO(obj.getConta());
		this.valor = obj.getValor();
		this.data = obj.getData();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ContaDTO getConta() {
		return conta;
	}

	public void setConta(ContaDTO conta) {
		this.conta = conta;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}



	
}
