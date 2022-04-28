package br.org.demaosunidas.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.org.demaosunidas.domain.Produto;
import br.org.demaosunidas.domain.enums.Status;
import br.org.demaosunidas.dto.ProdutoGetDTO;
import br.org.demaosunidas.dto.ProdutoInsertDTO;
import br.org.demaosunidas.repository.ProdutoRepository;
import br.org.demaosunidas.services.exception.ObjectNotFoudException;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository repo;
	
	public List<Produto> listar() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}
	
	public Page<Produto> search (String nome, Integer page,Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
				
		
		return repo. searchQuery(nome,pageRequest);
	}
	
	public Produto findById(Integer id) {
		Optional<Produto> obj = repo.findById(id);

		return obj. orElseThrow(() -> new ObjectNotFoudException("Objet non trouvé"));
	}
	
	public Produto insert(Produto obj) {
		obj.setId(null);
		repo.save(obj);
		
		return obj;
	}
	
	public Produto update(Produto objAlterado) {
		Produto objBanco = findById(objAlterado.getId());
		updateData(objBanco,objAlterado);
		return repo.save(objBanco);
	}
	
	public void deletar (Integer id) {
		Produto obj = findById(id);
		obj.setStatus(Status.INATIVO);
		repo.save(obj);
	}
	
	private void updateData(Produto objBanco, Produto objAnterado) {
		objBanco.setDescricao(objAnterado.getDescricao());
		objBanco.setNome(objAnterado.getNome());
		objBanco.setNomeSemAcento(objAnterado.getNomeSemAcento());
		objBanco.setQuantidadeEstoque(objAnterado.getQuantidadeEstoque());
		objBanco.setQuantidadeHistoricaTotal(objAnterado.getQuantidadeHistoricaTotal());
		objBanco.setSaldoEstoque(objAnterado.getSaldoEstoque());
		objBanco.setTipoMedida(objAnterado.getTipoMedida());
		objBanco.setTipoProduto(objAnterado.getTipoProduto());
		objBanco.setValorHistoricoTotal(objAnterado.getValorHistoricoTotal());
		
		objBanco.setStatus(objAnterado.getStatus());
		
	}

	public Produto fromDTO(ProdutoInsertDTO produtoInsertDto) {
		
		Produto produto = new Produto( null,produtoInsertDto.getNome(),null,
				produtoInsertDto.getTipoProduto(),produtoInsertDto.getTipoMedida(),
				null,null,null,null,produtoInsertDto.getDescricao(),null );		
		
		return produto;
	}
	
	public Produto fromDTO(ProdutoGetDTO produtoGetDto) {
		
		Produto produto = new Produto( produtoGetDto.getId(),produtoGetDto.getNome(),null,
				produtoGetDto.getTipoProduto(),produtoGetDto.getTipoMedida(),
				produtoGetDto.getQuantidadeEstoque(),produtoGetDto.getSaldoEstoque(),
				produtoGetDto.getQuantidadeHistoricaTotal(),
				produtoGetDto.getValorHistoricoTotal(),produtoGetDto.getDescricao(),
				produtoGetDto.getStatus() );		
		
		return produto;
	}
	
	public ProdutoGetDTO toDTO(Produto produto) {
		
		ProdutoGetDTO produtoGetDto = new ProdutoGetDTO( produto.getId(),produto.getNome(),produto.getTipoProduto(),
				produto.getTipoMedida(),
				produto.getQuantidadeEstoque(),produto.getSaldoEstoque(),
				produto.getQuantidadeHistoricaTotal(),
				produto.getValorHistoricoTotal(),produto.getDescricao(),
				produto.getStatus() );		
		
		return produtoGetDto;
	}

}
