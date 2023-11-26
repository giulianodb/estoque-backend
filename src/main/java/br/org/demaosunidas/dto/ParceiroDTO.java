package br.org.demaosunidas.dto;

import java.io.Serializable;
import java.util.List;


public class ParceiroDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private List<FamiliaDTO> listaFamilia;
	
	private List<DoadorDTO> listaDoador;
	
	private List<InstituicaoDTO> listaInstituicaoDTOs;
	
	private List<DoadorDTO> listaDoadorCliente;
	
	private List<InstituicaoDTO> listaInstituicaoCliente;
	
	private List<DoadorDTO> listaDoadorFornecedor;
	
	private List<InstituicaoDTO> listaInstituicaoFornecedor;
	
	private List<DoadorDTO> listaDoadorEstoque;
	
	private List<InstituicaoDTO> listaInstituicaoEstoque;

	public List<FamiliaDTO> getListaFamilia() {
		return listaFamilia;
	}

	public void setListaFamilia(List<FamiliaDTO> listaFamilia) {
		this.listaFamilia = listaFamilia;
	}

	public List<DoadorDTO> getListaDoador() {
		return listaDoador;
	}

	public void setListaDoador(List<DoadorDTO> listaDoador) {
		this.listaDoador = listaDoador;
	}

	public List<InstituicaoDTO> getListaInstituicaoDTOs() {
		return listaInstituicaoDTOs;
	}

	public void setListaInstituicaoDTOs(List<InstituicaoDTO> listaInstituicaoDTOs) {
		this.listaInstituicaoDTOs = listaInstituicaoDTOs;
	}

	public List<DoadorDTO> getListaDoadorCliente() {
		return listaDoadorCliente;
	}

	public void setListaDoadorCliente(List<DoadorDTO> listaDoadorCliente) {
		this.listaDoadorCliente = listaDoadorCliente;
	}

	public List<InstituicaoDTO> getListaInstituicaoCliente() {
		return listaInstituicaoCliente;
	}

	public void setListaInstituicaoCliente(List<InstituicaoDTO> listaInstituicaoCliente) {
		this.listaInstituicaoCliente = listaInstituicaoCliente;
	}

	public List<DoadorDTO> getListaDoadorFornecedor() {
		return listaDoadorFornecedor;
	}

	public void setListaDoadorFornecedor(List<DoadorDTO> listaDoadorFornecedor) {
		this.listaDoadorFornecedor = listaDoadorFornecedor;
	}

	public List<InstituicaoDTO> getListaInstituicaoFornecedor() {
		return listaInstituicaoFornecedor;
	}

	public void setListaInstituicaoFornecedor(List<InstituicaoDTO> listaInstituicaoFornecedor) {
		this.listaInstituicaoFornecedor = listaInstituicaoFornecedor;
	}

	public List<DoadorDTO> getListaDoadorEstoque() {
		return listaDoadorEstoque;
	}

	public void setListaDoadorEstoque(List<DoadorDTO> listaDoadorEstoque) {
		this.listaDoadorEstoque = listaDoadorEstoque;
	}

	public List<InstituicaoDTO> getListaInstituicaoEstoque() {
		return listaInstituicaoEstoque;
	}

	public void setListaInstituicaoEstoque(List<InstituicaoDTO> listaInstituicaoEstoque) {
		this.listaInstituicaoEstoque = listaInstituicaoEstoque;
	}
}
