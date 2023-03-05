package br.org.demaosunidas.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.org.demaosunidas.domain.Papel;
import br.org.demaosunidas.domain.Usuario;
import br.org.demaosunidas.repository.UsuarioRepository;
import br.org.demaosunidas.security.UserSS;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	private UsuarioRepository userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		
		Usuario user = userRepo.findByLoginUsuario(login);
		
		if (user == null) {
			throw new UsernameNotFoundException(login);
		}
		
		Set<Papel> papels = new HashSet<>();
		papels.add(user.getPapel());
		
		return new UserSS(user.getIdUsuario(),user.getLoginUsuario(),user.getSenhaCripto(),user.getNome(),papels);
	}

}
