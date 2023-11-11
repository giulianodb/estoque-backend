package br.org.demaosunidas.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.demaosunidas.domain.Categoria;
import br.org.demaosunidas.domain.GrupoCategoria;
import br.org.demaosunidas.domain.enums.Status;
import br.org.demaosunidas.dto.CategoriaDTO;
import br.org.demaosunidas.dto.GrupoCategoriaDTO;
import br.org.demaosunidas.dto.ListaCategoriasDTO;
import br.org.demaosunidas.repository.CategoriaRepository;
import br.org.demaosunidas.repository.GrupoCategoriaRepository;
import br.org.demaosunidas.services.exception.ObjectNotFoudException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repo;
	
	@Autowired
	private GrupoCategoriaRepository grupoRepo;
	
	  @PersistenceContext
	  private EntityManager entityManager;
	
	public List<Categoria> listarCategorias() {
		return repo.findAll() ;
	}
	
	public List<Categoria> buscarCategoriasDespesas(){
		return repo.buscarCategoriaDespesas();
	}
	
	public List<Categoria> buscarCategoriasReceitas(){
		return repo.buscarCategoriaReceitas();
	}
	
	public ListaCategoriasDTO obterCategorias() {
		
		List<GrupoCategoria> grupoCategoriaDespesa = grupoRepo.buscarCategoriaDespesas();
		List<GrupoCategoria> grupoCategoriaReceita = grupoRepo.buscarCategoriaReceitas();
		
		ListaCategoriasDTO dto = new ListaCategoriasDTO();
		dto.setDespesas(CategoriaService.entityToDTO(grupoCategoriaDespesa) );
		dto.setReceitas(CategoriaService.entityToDTO(grupoCategoriaReceita) );
		
		return dto;
		
	}
	
	private static List<GrupoCategoriaDTO> entityToDTO(List<GrupoCategoria> grupoCategoriaDespesa) {
		List<GrupoCategoriaDTO> retorno = new ArrayList<>();
		
		for(GrupoCategoria gc : grupoCategoriaDespesa ) {
			GrupoCategoriaDTO dto = new GrupoCategoriaDTO();
			dto.setId(gc.getId());
			dto.setNome(gc.getNome());
			dto.setStatus(gc.getStatus());
			dto.setTipoTransacao(gc.getTipoTransacao());
			dto.setListaCategoria(CategoriaService.entityToDTOCategoria(gc.getListCategoria()));
			
			retorno.add(dto);
		}
		
		return retorno;
	}

	private static List<CategoriaDTO> entityToDTOCategoria(List<Categoria> listCategoria) {
		List<CategoriaDTO> listaDTO = new ArrayList<>();
		
		for (Categoria c : listCategoria) {
			CategoriaDTO dto = new CategoriaDTO();
			dto.setId(c.getId());
			dto.setNome(c.getNome());
			dto.setStatus(c.getStatus());
			
			listaDTO.add(dto);
		}
		
		return listaDTO;
	}

	public Categoria findById(Integer id) {
		Optional<Categoria> obj = repo.findById(id);

		return obj.orElseThrow(() -> new ObjectNotFoudException("Objet non trouvé"));
	}

	public static CategoriaDTO entityToDto(Categoria x) {
		
		CategoriaDTO dto = new CategoriaDTO();
		
		dto.setId(x.getId());
		dto.setNome(x.getNome());
		dto.setStatus(x.getStatus());
		dto.setSoma(new BigDecimal(0));
		
		return dto;
	}
	
	public void alterar() {
//		Categoria d = new Categoria();
//		d.setId(999);
//		d.setNome("ALTERANDO");
		
		/*
		entityManager.createQuery("UPDATE Categoria c set c.nome =:nome WHERE id= :id")
        .setParameter("id", 999)
        .setParameter("nome", "ALTERANDO2222")
        .executeUpdate();
		*/
		
		repo.updatePhone(999, "ZABUMBAAA!");
	}
	
	public static GrupoCategoriaDTO entityToDtoGrupo(GrupoCategoria entity) {
		GrupoCategoriaDTO dto = new GrupoCategoriaDTO();
		
		dto.setId(entity.getId());
		dto.setNome(entity.getNome());
		dto.setStatus(entity.getStatus());
		dto.setTipoTransacao(entity.getTipoTransacao());
		
		return dto;
	}
	
}
