package br.org.demaosunidas.security;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.logging.Log;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.org.demaosunidas.domain.Papel;

public class UserSS implements UserDetails{

	
	private Integer id;
	private String login;
	private String senha;
	private String nome;
	private Collection<? extends GrantedAuthority> authorities;
	
	public UserSS() {
		
	}
	
	
	public UserSS(Integer id, String login, String senha, String nome,
			Set<Papel> papels) {
		super();
		this.id = id;
		this.login = login;
		this.senha = senha;
		this.nome = nome;
		if (papels != null)
			this.authorities = papels.stream().map(x -> new SimpleGrantedAuthority("ROLE_"+x.getNomePapel())).collect(Collectors.toList());
			
	}



	public Integer gerId () {
		return id;
	}
	public String getNome() {
		return nome;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return senha;
	}

	@Override
	public String getUsername() {
		return login;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
}
