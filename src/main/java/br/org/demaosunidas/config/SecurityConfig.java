package br.org.demaosunidas.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import br.org.demaosunidas.security.JWTAuthentcationFilter;
import br.org.demaosunidas.security.JWTAuthorizationFilter;
import br.org.demaosunidas.security.JWTUtil;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private JWTUtil jwtUtil;
	
	//Liberar todos as urls e verbos
	private static final String[] PUBLIC_MATCHERS = {
		"/h2-console/**",
//		"/contas/**",
//		"/transacoes/**",
		"/usuarios/**"
	};
	
	//Libeera somente gets
	private static final String[] PUBLIC_MATCHERS_GET = {
			"/h2-console/**",
			"/produtos/**",
		};
		
	
	/**
	 * Configuarando as 
	 */
	@Override
	protected void configure (HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable();
		
		http.authorizeRequests().antMatchers(PUBLIC_MATCHERS).permitAll()
								 .antMatchers(HttpMethod.GET, PUBLIC_MATCHERS_GET).permitAll()
								.anyRequest().authenticated();
		http.addFilter(new JWTAuthentcationFilter(authenticationManager(), jwtUtil));
		http.addFilter(new JWTAuthorizationFilter(authenticationManager(), jwtUtil, userDetailsService));
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}
	
	@Override
	public void configure (AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
	}
	
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		
		CorsConfiguration c = new CorsConfiguration().applyPermitDefaultValues();
		c.addAllowedHeader("Authorization");
		c.addExposedHeader("Authorization");
		source.registerCorsConfiguration("/**", c);
//		source.registerCorsConfiguration("/**", new CorsConfiguration().addAllowedHeader("Authorization"));
		return source;
	}
	
	@Bean public BCryptPasswordEncoder bCryptPasswordEncoder() {
	    return new BCryptPasswordEncoder(); 
	}

	
	
}
