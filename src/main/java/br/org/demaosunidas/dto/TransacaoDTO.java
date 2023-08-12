package br.org.demaosunidas.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Enumerated;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.org.demaosunidas.domain.Transacao;
import br.org.demaosunidas.domain.enums.TipoParceiroEnum;
import br.org.demaosunidas.domain.enums.TipoTransacaoEnum;

public class TransacaoDTO implements Serializable{
	
	private static final long serialVersionUID = 8996467323445786863L;
	private Integer id;
	
	private String descricao;
	
	private ContaDTO conta;
	
	private BigDecimal valor;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "pt_BR", timezone="America/Sao_Paulo")
//	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE,pattern = "dd/MM/yyyy")
	private LocalDate data;
	
	private TipoTransacaoEnum tipoTransacaoEnum;
	
	private FamiliaDTO familia;
	
	private DoadorDTO doador;
	
	private InstituicaoDTO instituicao;
	
	@Enumerated
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private TipoParceiroEnum tipoParceiro;
	
	private BigDecimal saldo;
	
	private Integer idDoador;
	
	private Integer idFamilia;
	
	private Integer idInstituicao;
	
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
		this.descricao = obj.getDescricao();
	}
	
	public TransacaoDTO(Transacao obj,BigDecimal saldoAnterior, boolean addConta) {
		this.id = obj.getId();
		if (addConta)
			this.conta = new ContaDTO(obj.getConta());
		this.valor = obj.getValor();
//		this.data = Date.from(obj.getData().atZone(ZoneId.systemDefault()).toInstant());
		this.data = obj.getData();
		this.descricao = obj.getDescricao();
		this.tipoTransacaoEnum = obj.getTipoTransacaoEnum();
		if (saldoAnterior == null) {
			saldoAnterior = new BigDecimal(0);
		}
		this.saldo = obj.getValor().add(saldoAnterior);
	}
	
	public void definirObjetos() {
		if (tipoParceiro.equals(TipoParceiroEnum.FAMILIA)) {
			FamiliaDTO f = new FamiliaDTO();
			f.setId(this.idFamilia);
			
			idDoador = null;
			idInstituicao = null;
			this.familia = f;
		} else if (tipoParceiro.equals(TipoParceiroEnum.DOADOR)) {
			DoadorDTO d = new DoadorDTO();
			d.setId(idDoador);
			idInstituicao = null;
			idFamilia = null;
			this.doador = d;
		} else {
			InstituicaoDTO i = new InstituicaoDTO();
			i.setId(idInstituicao);
			this.instituicao = i;
			idFamilia = null;
			idDoador = null;
		}
		
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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public FamiliaDTO getFamilia() {
		return familia;
	}

	public void setFamilia(FamiliaDTO familia) {
		this.familia = familia;
	}

	public DoadorDTO getDoador() {
		return doador;
	}

	public void setDoador(DoadorDTO doador) {
		this.doador = doador;
	}

	public InstituicaoDTO getInstituicao() {
		return instituicao;
	}

	public void setInstituicao(InstituicaoDTO instituicao) {
		this.instituicao = instituicao;
	}

	public TipoParceiroEnum getTipoParceiro() {
		return tipoParceiro;
	}

	public void setTipoParceiro(TipoParceiroEnum tipoParceiro) {
		this.tipoParceiro = tipoParceiro;
	}


	public Integer getIdFamilia() {
		return idFamilia;
	}

	public void setIdFamilia(Integer idFamilia) {
		this.idFamilia = idFamilia;
	}

	public Integer getIdInstituicao() {
		return idInstituicao;
	}

	public void setIdInstituicao(Integer idInstituicao) {
		this.idInstituicao = idInstituicao;
	}

	public Integer getIdDoador() {
		return idDoador;
	}

	public void setIdDoador(Integer idDoador) {
		this.idDoador = idDoador;
	}

	
}
