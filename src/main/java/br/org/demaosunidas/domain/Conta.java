package br.org.demaosunidas.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.org.demaosunidas.domain.enums.Status;
import br.org.demaosunidas.domain.enums.TipoContaEnum;
import br.org.demaosunidas.dto.ContaDTO;

@Entity
@Table(name="conta",schema="estoque")
public class Conta implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name = "CONTA_ID", sequenceName = "id_conta_seq", schema="estoque",allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "CONTA_ID")
	@Column(name = "id")
	private Integer id;
	
	private String nomeConta;
	
	private String numeroConta;
	
	private String agenciaConta;
	
	@Enumerated
	private TipoContaEnum tipoConta;
	
	@Enumerated
	private Status status;
	
	@OneToMany(mappedBy = "conta",fetch = FetchType.LAZY)
	@OrderBy("data DESC")
	private Set<Transacao> listTransacao;
	
	@OneToMany(mappedBy = "conta",fetch = FetchType.LAZY)
	@OrderBy("data")
	private Set<Saldo> listSaldo;
	
	public Conta() {
		// TODO Auto-generated constructor stub
	}
	
	public Conta(ContaDTO x) {
		super();
		this.id = x.getId();
		this.nomeConta = x.getNomeConta();
		this.numeroConta = x.getNumeroConta();
		this.agenciaConta = x.getAgenciaConta();
		this.tipoConta = x.getTipoConta();
		this.status = x.getStatus();
	}

	
	public Conta(Integer id) {
		super();
		this.id = id;
	}

	public Conta(Integer id, String nomeConta, String numeroConta, String agenciaConta, TipoContaEnum tipoConta,
			Status status) {
		super();
		this.id = id;
		this.nomeConta = nomeConta;
		this.numeroConta = numeroConta;
		this.agenciaConta = agenciaConta;
		this.tipoConta = tipoConta;
		this.status = status;
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

	public Set<Transacao> getListTransacao() {
		return listTransacao;
	}

	public void setListTransacao(Set<Transacao> listTransacao) {
		this.listTransacao = listTransacao;
	}

	public Set<Saldo> getListSaldo() {
		return listSaldo;
	}

	public void setListSaldo(Set<Saldo> listSaldo) {
		this.listSaldo = listSaldo;
	}
	
}
