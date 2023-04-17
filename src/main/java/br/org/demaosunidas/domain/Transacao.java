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
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Conta conta;
	
	private BigDecimal valor;
	
//	@Temporal(value=TemporalType.TIMESTAMP )
	@Column(name="data")
	private LocalDate data;
	
	@Enumerated
	private TipoTransacaoEnum tipoTransacaoEnum;
	

	public Transacao() {
		super();
	}
	
	public Transacao(TransacaoDTO obj) {
		this.id = obj.getId();
		this.conta = new Conta(obj.getConta());
		this.valor = obj.getValor();
		this.data = obj.getData();
		this.descricao = obj.getDescricao();	      
		
		this.tipoTransacaoEnum = obj.getTipoTransacaoEnum();
		
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


	
}
