package br.org.demaosunidas.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.transaction.TransactionScoped;

import br.org.demaosunidas.domain.enums.EstadoCivilEnum;
import br.org.demaosunidas.domain.enums.Status;

@Entity
@Table(name="familia",schema="estoque")
public class Familia implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name = "FAMILIA_ID", sequenceName = "id_familia_seq", schema="estoque",allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "FAMILIA_ID")
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "nomeresponsavel")
	private String nomeResponsavel;
	
	@Column(name = "cpfresponsavel")
	private String cpfResponsavel;
	
	@Column(name = "rgresponsavel")
	private String rgResponsavel;
	
	private String bairro;
	
	private String rua;
	
	private String cep;
	
	private String cidade;
	
	private String estado;
	
	private String telefone;
	
	private String celular;
	
	private String email;
	
	private String nacionalidade;
	
	private String profissao;
	
	private LocalDateTime dataCadastro;
	
	@Enumerated
	private EstadoCivilEnum estadoCivil;
	
	@Enumerated
	@Column(name="status")
	private Status status;
	
	@OneToOne (cascade = CascadeType.ALL)
	@JoinColumn(name = "motivo_id")
	private Motivo motivo;
	
	@OneToOne (cascade = CascadeType.ALL)
	@JoinColumn(name = "moradia_id")
	private Moradia moradia;
	
	@OneToOne (cascade = CascadeType.ALL)
	@JoinColumn(name = "programas_id")
	private ProgramasSociais programas;
	
	@OneToMany(mappedBy = "familia",fetch = FetchType.LAZY)
	@OrderBy("nome asc")
//	@Cascade(CascadeType.ALL)
	private Set<MembroFamilia> listMembroFamilia;
	
	@Transient
	private List<MembroFamilia> teste;

	public Familia() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Familia(Integer id) {
		super();
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNomeResponsavel() {
		return nomeResponsavel;
	}

	public void setNomeResponsavel(String nomeResponsavel) {
		this.nomeResponsavel = nomeResponsavel;
	}

	public String getCpfResponsavel() {
		return cpfResponsavel;
	}

	public void setCpfResponsavel(String cpfResponsavel) {
		this.cpfResponsavel = cpfResponsavel;
	}

	public String getRgResponsavel() {
		return rgResponsavel;
	}

	public void setRgResponsavel(String rgResponsavel) {
		this.rgResponsavel = rgResponsavel;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
		Familia other = (Familia) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public EstadoCivilEnum getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(EstadoCivilEnum estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getNacionalidade() {
		return nacionalidade;
	}

	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}

	public String getProfissao() {
		return profissao;
	}

	public void setProfissao(String profissao) {
		this.profissao = profissao;
	}

	public LocalDateTime getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDateTime dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Motivo getMotivo() {
		return motivo;
	}

	public void setMotivo(Motivo motivo) {
		this.motivo = motivo;
	}

	public Moradia getMoradia() {
		return moradia;
	}

	public void setMoradia(Moradia moradia) {
		this.moradia = moradia;
	}

	public Set<MembroFamilia> getListMembroFamilia() {
		return listMembroFamilia;
	}

	public void setListMembroFamilia(Set<MembroFamilia> listMembroFamilia) {
		this.listMembroFamilia = listMembroFamilia;
	}

	public ProgramasSociais getProgramas() {
		return programas;
	}

	public void setProgramas(ProgramasSociais programas) {
		this.programas = programas;
	}

	public List<MembroFamilia> getTeste() {
		return teste;
	}

	public void setTeste(List<MembroFamilia> teste) {
		this.teste = teste;
	}


	
}
