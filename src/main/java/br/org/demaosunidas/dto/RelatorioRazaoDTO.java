package br.org.demaosunidas.dto;

import java.math.BigDecimal;
import java.util.List;

import br.org.demaosunidas.domain.enums.TipoTransacaoEnum;

public class RelatorioRazaoDTO {
	
	private List<GrupoCategoriaDTO> grupoCategoriaReceita;
	
	private List<GrupoCategoriaDTO> grupoCategoriaDespesa;
	
	private BigDecimal totalReceita;
	
	private BigDecimal totalDespesa;
	
	private BigDecimal saldoTotal;
	
	private String saldoTotalString;
	
	private String nomeRelatorio;

	public List<GrupoCategoriaDTO> getGrupoCategoriaReceita() {
		return grupoCategoriaReceita;
	}

	public void setGrupoCategoriaReceita(List<GrupoCategoriaDTO> grupoCategoriaReceita) {
		this.grupoCategoriaReceita = grupoCategoriaReceita;
	}

	public List<GrupoCategoriaDTO> getGrupoCategoriaDespesa() {
		return grupoCategoriaDespesa;
	}

	public void setGrupoCategoriaDespesa(List<GrupoCategoriaDTO> grupoCategoriaDespesa) {
		this.grupoCategoriaDespesa = grupoCategoriaDespesa;
	}
	
	public List<GrupoCategoriaDTO> getList(TipoTransacaoEnum tipo){
		if (tipo.equals(TipoTransacaoEnum.RECEITA)) {
			return grupoCategoriaReceita;
		} else {
			return grupoCategoriaDespesa;
		}
	}

	public BigDecimal getTotalReceita() {
		return totalReceita;
	}

	public void setTotalReceita(BigDecimal totalReceita) {
		this.totalReceita = totalReceita;
	}

	public BigDecimal getTotalDespesa() {
		return totalDespesa;
	}

	public void setTotalDespesa(BigDecimal totalDespesa) {
		this.totalDespesa = totalDespesa;
	}

	public BigDecimal getSaldoTotal() {
		return saldoTotal;
	}

	public void setSaldoTotal(BigDecimal saldoTotal) {
		this.saldoTotal = saldoTotal;
	}

	public String getNomeRelatorio() {
		return nomeRelatorio;
	}

	public void setNomeRelatorio(String nomeRelatorio) {
		this.nomeRelatorio = nomeRelatorio;
	}

	public String getSaldoTotalString() {
		return saldoTotalString;
	}

	public void setSaldoTotalString(String saldoTotalString) {
		this.saldoTotalString = saldoTotalString;
	}
	
}
