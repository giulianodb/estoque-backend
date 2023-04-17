package br.org.demaosunidas.dto;

import java.io.Serializable;

import javax.persistence.Enumerated;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.org.demaosunidas.domain.Conta;
import br.org.demaosunidas.domain.enums.Status;
import br.org.demaosunidas.domain.enums.TipoContaEnum;

public class ContaDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private String nomeConta;
	
	private String numeroConta;
	
	private String agenciaConta;
	
	@Enumerated
	@JsonFormat(shape = JsonFormat.Shape.OBJECT)
	private TipoContaEnum tipoConta;
	
	@Enumerated
	private Status status;
	
	private SaldoDTO saldo;
	
	public ContaDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public ContaDTO(Integer id, String nomeConta, String numeroConta, String agenciaConta, TipoContaEnum tipoConta,
			Status status) {
		super();
		this.id = id;
		this.nomeConta = nomeConta;
		this.numeroConta = numeroConta;
		this.agenciaConta = agenciaConta;
		this.tipoConta = tipoConta;
		this.status = status;
	}
	
	public ContaDTO(Conta x) {
		super();
		this.id = x.getId();
		this.nomeConta = x.getNomeConta();
		this.numeroConta = x.getNumeroConta();
		this.agenciaConta = x.getAgenciaConta();
		this.tipoConta = x.getTipoConta();
		this.status = x.getStatus();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNomeConta() {
		return nomeConta;
	}

	public void setNomeConta(String nomeConta) {
		this.nomeConta = nomeConta;
	}

	public String getNumeroConta() {
		return numeroConta;
	}

	public void setNumeroConta(String numeroConta) {
		this.numeroConta = numeroConta;
	}

	public String getAgenciaConta() {
		return agenciaConta;
	}

	public void setAgenciaConta(String agenciaConta) {
		this.agenciaConta = agenciaConta;
	}

	public TipoContaEnum getTipoConta() {
		return tipoConta;
	}

	public void setTipoConta(TipoContaEnum tipoConta) {
		this.tipoConta = tipoConta;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public SaldoDTO getSaldo() {
		return saldo;
	}

	public void setSaldo(SaldoDTO saldo) {
		this.saldo = saldo;
	}
	
}
