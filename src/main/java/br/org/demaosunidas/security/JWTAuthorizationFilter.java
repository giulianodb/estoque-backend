package br.org.demaosunidas.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter{
	
	private JWTUtil jwtUtil;
	
	private UserDetailsService userDetailsService;
	
	public JWTAuthorizationFilter (AuthenticationManager authenticationManager, JWTUtil jwtUtil, UserDetailsService userDetailsService) {
		super (authenticationManager);
		
		this.jwtUtil = jwtUtil;
		this.userDetailsService = userDetailsService;
	}
	
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		String header = request.getHeader("Authorization");
		
		if (header != null && header.startsWith("Bearer ")) {
			UsernamePasswordAuthenticationToken auth = getAuthentication(request,header.substring(7));
			if (auth != null) {
				SecurityContextHolder.getContext().setAuthentication(auth);
			}
		}
		
		chain.doFilter(request, response);
		
	}

	private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request, String token) {
		if (jwtUtil.tokenValido(token)) {
			String username = jwtUtil.getUsername(token);
//			UserDetails user = userDetailsService.loadUserByUsername(username);
			
			List<String> listaRoles = (List<String>) jwtUtil.getClaims(token).get("groups");
			String nome = (String) jwtUtil.getClaims(token).get("nome");
			
			
			
			List<GrantedAuthority> t = new ArrayList<>();
			
			for (String role : listaRoles) {
				t.add(new SimpleGrantedAuthority(role));
			}
			
			UserDetails user = new UserSS(null, token, username, nome, null);
			
			
			return new UsernamePasswordAuthenticationToken(user,null, t);
		}
		
		return null;
	}
	
}	
