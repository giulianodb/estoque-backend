package br.org.demaosunidas.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.org.demaosunidas.domain.Aluno;
import br.org.demaosunidas.domain.Usuario;
import br.org.demaosunidas.dto.UsuarioNewDTO;
import br.org.demaosunidas.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository repo;
	
	@Autowired
	private BCryptPasswordEncoder encode;
	
	public List<Usuario> listar() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}
	
//	public Aluno findById(Integer id) {
//		Optional<Aluno> obj = repo.findById(id);
//
//		return obj. orElseThrow(() -> new ObjectNotFoudException("Objet non trouvé"));
//	}
	

	public void insert(Usuario obj) {
		obj.setIdUsuario(null);
		repo.save(obj);
	}
	

	
	private void updateData(Aluno objBanco, Aluno objAnterado) {
		objBanco.setBairro(objAnterado.getBairro());
		objBanco.setCidade(objAnterado.getCidade());
		objBanco.setDataNascimento(objAnterado.getDataNascimento());
		objBanco.setEstado(objAnterado.getEstado());
		objBanco.setNome(objAnterado.getNome());
		objBanco.setRua(objAnterado.getRua());
		objBanco.setStatus(objAnterado.getStatus());
		
	}

	public Usuario fromDTO (UsuarioNewDTO objDTO) {
		Usuario u = new Usuario(objDTO.getIdUsuario(), objDTO.getEmail(),objDTO.getLoginUsuario(),objDTO.getNome(),objDTO.getPapel(),objDTO.getSenha(),
				objDTO.getSobrenome(), objDTO.getStatusUsuario(),encode.encode(objDTO.getSenhaCripto()));
		
		return u;
		
	}
	
}
