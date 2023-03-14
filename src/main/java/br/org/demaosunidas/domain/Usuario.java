package br.org.demaosunidas.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.org.demaosunidas.domain.enums.Status;
import br.org.demaosunidas.dto.PapelDTO;
import br.org.demaosunidas.dto.UsuarioNewDTO;

@Entity
@Table(name="usuario",schema="estoque")
//@NamedQueries({ @NamedQuery(name = "obterPorLogin", query = "SELECT u FROM Usuario u WHERE u.loginUsuario = :login"),
//	@NamedQuery(name = "listarUsuario", query = "SELECT u FROM Usuario u ")
//})
public class Usuario implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3795035789464662511L;
	
	@Id
	@SequenceGenerator(name = "USUARIO_ID", sequenceName = "id_usuario_seq", schema="estoque",allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "USUARIO_ID")
	@Column(name = "id_usuario")
	private Integer idUsuario;
	
	private String nome;
	
	@ManyToOne 
	private Papel papel;
	
	@Column(unique=true,name = "loginusuario")
	private String loginUsuario;
	
	@JsonIgnore
	private String senha;
	
	@JsonIgnore
	private String senhaCripto;
	
	public Usuario(UsuarioNewDTO obj) {
		// TODO Auto-generated constructor stub
		this.email = obj.getEmail();
		this.idUsuario = obj.getIdUsuario();
		this.loginUsuario = obj.getLoginUsuario();
		this.nome = obj.getNome();
		this.papel = new Papel(obj.getPapel());
		this.senha = obj.getSenha();
		this.senhaCripto = obj.getSenhaCripto();
		this.sobrenome = obj.getSobrenome();
		this.statusUsuario = obj.getStatusUsuario();
		
	}

	public Usuario() {
		// TODO Auto-generated constructor stub
	}

	public Usuario(Integer idUsuario2, String email2, String loginUsuario2, String nome2, PapelDTO papel2,
			String senha2, String sobrenome2, Status statusUsuario2, String encode) {
		// TODO Auto-generated constructor stub
		
		this.idUsuario = idUsuario2;
		this.email = email2;
		this.loginUsuario = loginUsuario2;
		this.nome = nome2;
		this.papel = new Papel(papel2);
		this.senha = senha2;
		this.sobrenome = sobrenome2;
		this.statusUsuario = statusUsuario2;
		this.senhaCripto = encode;
		
		
	}

	public String getSenhaCripto() {
		return senhaCripto;
	}

	public void setSenhaCripto(String senhaCripto) {
		this.senhaCripto = senhaCripto;
	}

	private String email;
	
	
	private String sobrenome;
	
	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	@Enumerated
	@Column(name="status")
	private Status statusUsuario;

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Papel getPapel() {
		return papel;
	}

	public void setPapel(Papel papel) {
		this.papel = papel;
	}

	public String getLoginUsuario() {
		return loginUsuario;
	}

	public void setLoginUsuario(String loginUsuario) {
		this.loginUsuario = loginUsuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Status getStatusUsuario() {
		return statusUsuario;
	}

	public void setStatusUsuario(Status statusUsuario) {
		this.statusUsuario = statusUsuario;
	}
	
}