package br.org.demaosunidas.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.org.demaosunidas.domain.enums.Status;

@Entity
@Table(name="instituicao",schema="estoque")
public class Instituicao implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@SequenceGenerator(name = "INSTITUICAO_ID", sequenceName = "id_instituicao_seq", schema="estoque",allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "INSTITUICAO_ID")
	@Column(name = "id")
	private Integer id;
	
	private String nome;
	
	@Column(unique=true)
	private String cnpj;
	
	@Column(name = "nomecontato")
	private String nomeContato;
	
	private String email;
	
	private String telefone;
	
	@Enumerated
	@Column(name="status")
	private Status status;
	
	private Boolean cliente;
	
	private Boolean fornecedor;
	
	private Boolean estoque;
	
	public Instituicao() {
	
	}
	
	public Instituicao(Integer id) {
		super();
		this.id = id;
	}

	public Instituicao(Integer id, String nome, String cnpj, String nomeContato, String email, String telefone,
			Status status) {
		super();
		this.id = id;
		this.nome = nome;
		this.cnpj = cnpj;
		this.nomeContato = nomeContato;
		this.email = email;
		this.telefone = telefone;
		this.status = status;
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

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getNomeContato() {
		return nomeContato;
	}

	public void setNomeContato(String nomeContato) {
		this.nomeContato = nomeContato;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Instituicao other = (Instituicao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Boolean getCliente() {
		return cliente;
	}

	public void setCliente(Boolean cliente) {
		this.cliente = cliente;
	}

	public Boolean getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Boolean fornecedor) {
		this.fornecedor = fornecedor;
	}

	public Boolean getEstoque() {
		return estoque;
	}

	public void setEstoque(Boolean estoque) {
		this.estoque = estoque;
	}
	
}
