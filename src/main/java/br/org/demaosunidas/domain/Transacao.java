package br.org.demaosunidas.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.org.demaosunidas.domain.enums.TipoParceiroEnum;
import br.org.demaosunidas.domain.enums.TipoTransacaoEnum;
import br.org.demaosunidas.dto.TransacaoDTO;

@Entity
@Table(name="transacao",schema="estoque")
public class Transacao implements Serializable{
	
	private static final long serialVersionUID = 8996467323445786863L;
	@Id
	@SequenceGenerator(name = "TRANSACAO_ID", sequenceName = "id_transacao_seq", schema="estoque",allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "TRANSACAO_ID")
	@Column(name = "id")
	private Integer id;
	
	private String descricao;
	
	private BigDecimal valor;
	
//	@Temporal(value=TemporalType.TIMESTAMP )
	@Column(name="data")
	private LocalDate data;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Familia familia;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Instituicao instituicao;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Doador doador;
	
	@Enumerated
	private TipoTransacaoEnum tipoTransacaoEnum;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Conta conta;
	
	@Enumerated
	private TipoParceiroEnum tipoParceiroEnum;
	
	public Transacao() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public BigDecimal getValor() {
		return valor;
	}


	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}


	public LocalDate getData() {
		return data;
	}


	public void setData(LocalDate data) {
		this.data = data;
	}


	public TipoTransacaoEnum getTipoTransacaoEnum() {
		return tipoTransacaoEnum;
	}


	public void setTipoTransacaoEnum(TipoTransacaoEnum tipoTransacaoEnum) {
		this.tipoTransacaoEnum = tipoTransacaoEnum;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Familia getFamilia() {
		return familia;
	}

	public void setFamilia(Familia familia) {
		this.familia = familia;
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


	public TipoParceiroEnum getTipoParceiroEnum() {
		return tipoParceiroEnum;
	}


	public void setTipoParceiroEnum(TipoParceiroEnum tipoParceiroEnum) {
		this.tipoParceiroEnum = tipoParceiroEnum;
	}


	
}
