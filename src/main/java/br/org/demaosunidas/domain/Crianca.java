package br.org.demaosunidas.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.org.demaosunidas.domain.enums.ProjetoEnum;
import br.org.demaosunidas.domain.enums.Status;

@Entity
@Table(name="crianca",schema="estoque")
public class Crianca implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name = "CRIANCA_ID", sequenceName = "id_crianca_seq", schema="estoque",allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "CRIANCA_ID")
	@Column(name = "id")
	private Integer id;
	
	private String nome;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "pt_BR", timezone="America/Sao_Paulo")
	@Column(name="datanascimento")
	private Date dataNascimento;
	
	@Enumerated
	@Column(name="status")
	private Status status;
	
	@Enumerated
	private ProjetoEnum projeto;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Familia familia;
	
	private String sexo;
	
	private String escola;
	
	private String nomeConducao;
	
	private String telefoneConducao;
	
	private Boolean alergia;
	
	private String descricaoAlergia;
	
	private Boolean medicamento;
	
	private String descricaoMedicamento;
	
	private String religiao;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "pt_BR", timezone="America/Sao_Paulo")
	private Date dataInscricao;
	
	private Boolean listaEspera;
	
	private Boolean matriculado;
	
	@OneToMany(mappedBy = "crianca",fetch = FetchType.EAGER)
//	@OrderBy("nome asc")
//	@Cascade(CascadeType.ALL)
	private Set<AvaliacaoContextoResposta> listRespostaAvaliacaoContexto;
	
	public Crianca() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Set<AvaliacaoContextoResposta> getListRespostaAvaliacaoContexto() {
		return listRespostaAvaliacaoContexto;
	}

	public void setListRespostaAvaliacaoContexto(Set<AvaliacaoContextoResposta> listRespostaAvaliacaoContexto) {
		this.listRespostaAvaliacaoContexto = listRespostaAvaliacaoContexto;
	}

	public Crianca(Integer id) {
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

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public ProjetoEnum getProjeto() {
		return projeto;
	}

	public void setProjeto(ProjetoEnum projeto) {
		this.projeto = projeto;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getEscola() {
		return escola;
	}

	public void setEscola(String escola) {
		this.escola = escola;
	}

	public String getNomeConducao() {
		return nomeConducao;
	}

	public void setNomeConducao(String nomeConducao) {
		this.nomeConducao = nomeConducao;
	}

	public String getTelefoneConducao() {
		return telefoneConducao;
	}

	public void setTelefoneConducao(String telefoneConducao) {
		this.telefoneConducao = telefoneConducao;
	}

	public Boolean getAlergia() {
		return alergia;
	}

	public void setAlergia(Boolean alergia) {
		this.alergia = alergia;
	}

	public String getDescricaoAlergia() {
		return descricaoAlergia;
	}

	public void setDescricaoAlergia(String descricaoAlergia) {
		this.descricaoAlergia = descricaoAlergia;
	}

	public Boolean getMedicamento() {
		return medicamento;
	}

	public void setMedicamento(Boolean medicamento) {
		this.medicamento = medicamento;
	}

	public String getDescricaoMedicamento() {
		return descricaoMedicamento;
	}

	public void setDescricaoMedicamento(String descricaoMedicamento) {
		this.descricaoMedicamento = descricaoMedicamento;
	}

	public String getReligiao() {
		return religiao;
	}

	public void setReligiao(String religiao) {
		this.religiao = religiao;
	}

	public Date getDataInscricao() {
		return dataInscricao;
	}

	public void setDataInscricao(Date dataInscricao) {
		this.dataInscricao = dataInscricao;
	}

	public Boolean getListaEspera() {
		return listaEspera;
	}

	public void setListaEspera(Boolean listaEspera) {
		this.listaEspera = listaEspera;
	}

	public Boolean getMatriculado() {
		return matriculado;
	}

	public void setMatriculado(Boolean matriculado) {
		this.matriculado = matriculado;
	}

	public Familia getFamilia() {
		return familia;
	}

	public void setFamilia(Familia familia) {
		this.familia = familia;
	}


	

	
}
