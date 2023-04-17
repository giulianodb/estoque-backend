package br.org.demaosunidas.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.org.demaosunidas.domain.Transacao;
import br.org.demaosunidas.domain.enums.TipoTransacaoEnum;

public class TransacaoDTO implements Serializable{
	
	private static final long serialVersionUID = 8996467323445786863L;
	private Integer id;
	
	private ContaDTO conta;
	
	private BigDecimal valor;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "pt_BR", timezone="America/Sao_Paulo")
//	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE,pattern = "yyyy/MM/dd")
	private LocalDate data;
	
	private TipoTransacaoEnum tipoTransacaoEnum;
	
	private BigDecimal saldo;
	
	public TransacaoDTO() {
		super();
	}

	public TransacaoDTO(Transacao obj) {
		this.id = obj.getId();
		this.conta = new ContaDTO(obj.getConta());
		this.valor = obj.getValor();
//		this.data = Date.from(obj.getData().atZone(ZoneId.systemDefault()).toInstant());
		this.data = obj.getData();
		this.tipoTransacaoEnum = obj.getTipoTransacaoEnum();
	}
	
	public TransacaoDTO(Transacao obj,BigDecimal saldoAnterior, boolean addConta) {
		this.id = obj.getId();
		if (addConta)
			this.conta = new ContaDTO(obj.getConta());
		this.valor = obj.getValor();
//		this.data = Date.from(obj.getData().atZone(ZoneId.systemDefault()).toInstant());
		this.data = obj.getData();
		this.tipoTransacaoEnum = obj.getTipoTransacaoEnum();
		if (saldoAnterior == null) {
			saldoAnterior = new BigDecimal(0);
		}
		this.saldo = obj.getValor().add(saldoAnterior);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ContaDTO getConta() {
		return conta;
	}

	public void setConta(ContaDTO conta) {
		this.conta = conta;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public TipoTransacaoEnum getTipoTransacaoEnum() {
		return tipoTransacaoEnum;
	}

	public void setTipoTransacaoEnum(TipoTransacaoEnum tipoTransacaoEnum) {
		this.tipoTransacaoEnum = tipoTransacaoEnum;
	}


	public BigDecimal getSaldo() {
		return saldo;
	}

	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	
}
