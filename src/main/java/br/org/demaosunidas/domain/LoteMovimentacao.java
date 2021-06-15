package br.org.demaosunidas.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="lotemovimentacao",schema="estoque")
public class LoteMovimentacao implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8996466788745786863L;
	
	@SequenceGenerator(name = "LOTEMOVIMENTACAO_ID", sequenceName = "id_lotemovimentacao_seq", schema="estoque",allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "LOTEMOVIMENTACAO_ID")
	@Id
	@Column(name = "codigo")
	private Integer codigo;
	
	@Column(name = "numeroentrada")
	private String numeroEntrada;
	
	//Esses atribtutos serão populados de acordo com o tipo de doador
	@ManyToOne(fetch=FetchType.EAGER)
	private Campanha campanha;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Instituicao instituicao;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Doador doador;
	
//	@Temporal(value=TemporalType.TIMESTAMP )
	@Column(name="dataentrada")
	private LocalDateTime data;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Familia familia;
	
	//Caso a saída for de campanha essa é a família que irá receber a saída de estqoue
//	@ManyToOne(fetch=FetchType.EAGER)
//	private Familia familiaCampanha;
	
//	@ManyToOne(fetch=FetchType.EAGER)
//	private Doador doadorCampanha;
	
//	@ManyToOne(fetch=FetchType.LAZY)
//	private Usuario usuarioFezCadastro;
	
	@Temporal(value=TemporalType.TIMESTAMP )
	@Column(name="dataacao")
	private Date dataAcao;
	
	
//	@ManyToOne(fetch=FetchType.EAGER)
//	private Instituicao instituicaoCampanha;
	
	@OneToMany(mappedBy = "loteMovimentacao",fetch = FetchType.LAZY)
	private List<Movimentacao> listMovimentacao;
	
	@Enumerated
	@Column(name="tipomovimentacaoenum")
	private TipoMovimentacaoEnum tipoMovimentacaoEnum;
	
	
	public LoteMovimentacao(Integer codigo, String numeroEntrada, Campanha campanha, Instituicao instituicao, Doador doador,
		LocalDateTime data, Familia familia, Date dataAcao, TipoMovimentacaoEnum tipoMovimentacaoEnum, List<Movimentacao> listaMovimentacao) {
	super();
	this.codigo = codigo;
	this.numeroEntrada = numeroEntrada;
	this.campanha = campanha;
	this.instituicao = instituicao;
	this.doador = doador;
	this.data = data;
	this.familia = familia;
	this.dataAcao = dataAcao;
	this.tipoMovimentacaoEnum = tipoMovimentacaoEnum;
	this.listMovimentacao = listaMovimentacao;
	
}
	
	

	public LoteMovimentacao() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getNumeroEntrada() {
		return numeroEntrada;
	}

	public void setNumeroEntrada(String numeroEntrada) {
		this.numeroEntrada = numeroEntrada;
	}

	public Campanha getCampanha() {
		return campanha;
	}

	public void setCampanha(Campanha campanha) {
		this.campanha = campanha;
	}

	public Instituicao getInstituicao() {
		return instituicao;
	}

	public void setInstituicao(Instituicao instituicao) {
		this.instituicao = instituicao;
	}

	public Doador getDoador() {
		return doador;
	}

	public void setDoador(Doador doador) {
		this.doador = doador;
	}

	public Familia getFamilia() {
		return familia;
	}

	public void setFamilia(Familia familia) {
		this.familia = familia;
	}

	public Date getDataAcao() {
		return dataAcao;
	}

	public void setDataAcao(Date dataAcao) {
		this.dataAcao = dataAcao;
	}

	public TipoMovimentacaoEnum getTipoMovimentacaoEnum() {
		return tipoMovimentacaoEnum;
	}

	public void setTipoMovimentacaoEnum(TipoMovimentacaoEnum tipoMovimentacaoEnum) {
		this.tipoMovimentacaoEnum = tipoMovimentacaoEnum;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
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
		LoteMovimentacao other = (LoteMovimentacao) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}



	public List<Movimentacao> getListMovimentacao() {
		return listMovimentacao;
	}



	public void setListMovimentacao(List<Movimentacao> listMovimentacao) {
		this.listMovimentacao = listMovimentacao;
	}



	public LocalDateTime getData() {
		return data;
	}



	public void setData(LocalDateTime data) {
		this.data = data;
	}
	
}
