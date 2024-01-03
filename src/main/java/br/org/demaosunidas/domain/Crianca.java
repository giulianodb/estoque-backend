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

import br.org.demaosunidas.domain.enums.Status;
import br.org.demaosunidas.dto.CriancaDTO;

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
	
//	@Enumerated
//	private ProjetoEnum projeto;
	
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
	 @Column(length = 10485760)
	private String foto;
	
//	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "pt_BR", timezone="America/Sao_Paulo")
//	private Date dataInscricao;
	
//	private Boolean listaEspera;
//	
//	private Boolean matriculado;
	
	@OneToMany(mappedBy = "crianca",fetch = FetchType.EAGER)
//	@OrderBy("nome asc")
//	@Cascade(CascadeType.ALL)
	private Set<AvaliacaoContextoResposta> listRespostaAvaliacaoContexto;
	
	@OneToMany(mappedBy = "crianca",fetch = FetchType.LAZY)
	@OrderBy( value="ano DESC")
	private Set<Inscricao> listInscricao;
	
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

	public Crianca(CriancaDTO crianca) {
		// TODO Auto-generated constructor stub
		this.id = crianca.getId();
		this.nome = crianca.getNome();
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


	public Familia getFamilia() {
		return familia;
	}

	public void setFamilia(Familia familia) {
		this.familia = familia;
	}

	public Set<Inscricao> getListInscricao() {
		return listInscricao;
	}

	public void setListInscricao(Set<Inscricao> listInscricao) {
		this.listInscricao = listInscricao;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}


	
}
