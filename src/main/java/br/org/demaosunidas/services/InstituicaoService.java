package br.org.demaosunidas.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.demaosunidas.domain.Instituicao;
import br.org.demaosunidas.repository.InstituicaoRepository;

@Service
public class InstituicaoService {
	
	@Autowired
	private InstituicaoRepository repositoy;
	
	public List<Instituicao> listar() {
		// TODO Auto-generated method stub
		return repositoy.findAll();
	}
	
	
}
