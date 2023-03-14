package br.org.demaosunidas.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTUtil {
	
	@Value("${jwt.secret}")
	private String secret;

	@Value("${jwt.expiration}")
	private Long expiration;
	
	public String generateToken(String nome, String login, Collection<? extends GrantedAuthority> collection) {
		
		List<? extends GrantedAuthority> strings = collection.stream().toList();
		
		Object[] teste = strings.toArray();
		
		List<String> lista = new ArrayList<>();
		
		for (Object object : teste) {
			System.out.println(object);
			lista.add(object.toString());
		}
		
		return Jwts.builder()
				.setSubject(login)
				.setExpiration(new Date(System.currentTimeMillis() + expiration))
				.signWith(SignatureAlgorithm.HS512, secret.getBytes())
				.claim("groups", lista)
				.claim("nome", nome)
				.compact();
	}

	public boolean tokenValido(String token) {
		Claims claims = getClaims (token);
		
		if (claims != null) {
			String username = claims.getSubject();
			Date expirationDate = claims.getExpiration();
			Date now = new Date(System.currentTimeMillis());
			if (username != null && expirationDate != null && now.before(expirationDate)) {
				return true;
			}
		}
		
		return false;
	}

	public Claims getClaims(String token) {
		try {
			return Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody();
			
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}

	public String getUsername(String token) {

		Claims claims = getClaims (token);
		
		if (claims != null) {
			return claims.getSubject();
		
		}
	
		return null;
	}	
}
