package br.org.demaosunidas.services;

import org.springframework.security.core.context.SecurityContextHolder;

import br.org.demaosunidas.security.UserSS;

public class UsuarioAutenticadoService {
	
	public static UserSS authenticated() {
		try {
			return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			
		} catch (Exception e) {
			
			return null;
		}
		
	}
}
