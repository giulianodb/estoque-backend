package br.org.demaosunidas.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.org.demaosunidas.domain.AvaliacaoContextoResposta;
import br.org.demaosunidas.domain.Crianca;
import br.org.demaosunidas.domain.enums.ProjetoEnum;
import br.org.demaosunidas.domain.enums.Status;
import br.org.demaosunidas.domain.enums.TipoAvaliacaoContextoEnum;

public class CriancaGetDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String nome;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "pt_BR", timezone="America/Sao_Paulo")
	private Date dataNascimento;
	private Status status;
	
	private ProjetoEnum projeto;
	private FamiliaDTO familia;
	
	private Boolean listaEspera;
	private Boolean matriculado;
	
	private Integer totalAfirmacao;
	private Integer totalSituacao;
	
	private String sexo;
	
	private String escola;
	
	private String nomeConducao;
	
	private String telefoneConducao;
	
	private Boolean alergia;
	
	private String descricaoAlergia;
	
	private Boolean medicamento;
	
	private String descricaoMedicamento;
	
	private String religiao;
	
	private String foto;
	
	private Integer idade;
	
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

	public CriancaGetDTO () {
		
	}
	
	public CriancaGetDTO (Crianca obj) {
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.dataNascimento = obj.getDataNascimento();
		this.status = obj.getStatus();
		this.familia = new FamiliaDTO(obj.getFamilia());
		this.sexo = obj.getSexo();
		this.escola = obj.getEscola();
		
		this.nomeConducao = obj.getNomeConducao();
		this.telefoneConducao = obj.getTelefoneConducao();
		this.alergia = obj.getAlergia();
		this.descricaoAlergia = obj.getDescricaoAlergia();
		this.medicamento = obj.getMedicamento();
		this.descricaoMedicamento = obj.getDescricaoMedicamento();
		this.religiao = obj.getReligiao();
		this.foto = obj.getFoto();
		
		if (obj.getListInscricao() != null) {
			obj.getListInscricao().forEach(x -> {
		            this.projeto = x.getProjeto();
		            return;
		        });
		}
			
		
		int somaAfirmacao = 0;
		int somaSituacao = 0;
		
		for (AvaliacaoContextoResposta r : obj.getListRespostaAvaliacaoContexto()) {
			if (r.getAvaliacaoContexto().getTipoAvaliacao().equals(TipoAvaliacaoContextoEnum.AFIRMACAO)) {
				somaAfirmacao = somaAfirmacao + r.getResposta();
				
			}
			else {
				somaSituacao = somaSituacao + r.getResposta(); 
			}
		}
		
		this.totalAfirmacao = somaAfirmacao;
		this.totalSituacao = somaSituacao;
//		this.listaEspera = obj.getListaEspera();
//		this.matriculado = obj.getMatriculado();
//		this.projeto = obj.getProjeto();
		
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
	public FamiliaDTO getFamilia() {
		return familia;
	}
	public void setFamilia(FamiliaDTO familia) {
		this.familia = familia;
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
	public Integer getTotalAfirmacao() {
		return totalAfirmacao;
	}
	public void setTotalAfirmacao(Integer totalAfirmacao) {
		this.totalAfirmacao = totalAfirmacao;
	}
	public Integer getTotalSituacao() {
		return totalSituacao;
	}
	public void setTotalSituacao(Integer totalSituacao) {
		this.totalSituacao = totalSituacao;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public Integer getIdade() {
		   // Data atual
        Date dataAtual = new Date();

        // Calcula a diferença em milissegundos
        long diferencaEmMillis = dataAtual.getTime() - dataNascimento.getTime();

        // Converte a diferença de milissegundos para anos
        long anos = diferencaEmMillis / (1000L * 60 * 60 * 24 * 365);
		
		return (int) anos;
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
	}
	
}
