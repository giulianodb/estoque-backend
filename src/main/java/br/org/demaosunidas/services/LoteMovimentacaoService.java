package br.org.demaosunidas.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.org.demaosunidas.domain.Campanha;
import br.org.demaosunidas.domain.Doador;
import br.org.demaosunidas.domain.Familia;
import br.org.demaosunidas.domain.Instituicao;
import br.org.demaosunidas.domain.LoteMovimentacao;
import br.org.demaosunidas.domain.Movimentacao;
import br.org.demaosunidas.domain.Produto;
import br.org.demaosunidas.domain.Transacao;
import br.org.demaosunidas.domain.enums.TipoMovimentacaoEnum;
import br.org.demaosunidas.domain.enums.TipoTransacaoEnum;
import br.org.demaosunidas.dto.FamiliasAtingidasGrafico;
import br.org.demaosunidas.dto.LoteMovimentacaoGetDTO;
import br.org.demaosunidas.dto.LoteMovimentacaoInsertDTO;
import br.org.demaosunidas.dto.MesesFinanceiroGrafico;
import br.org.demaosunidas.repository.LoteMovimentacaoRepository;
import br.org.demaosunidas.services.exception.ObjectNotFoudException;
import br.org.demaosunidas.util.DateUtil;

@Service
public class LoteMovimentacaoService {
	
	@Autowired
	private LoteMovimentacaoRepository repo;
	@Autowired
	private MovimentacaoService movimentacaoService;
	
	public List<LoteMovimentacao> listar() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}
	
	public Page<LoteMovimentacao> search (Integer page,Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		
		return repo. searchQuery(pageRequest);
	}
	
	public LoteMovimentacao findById(Integer id) {
		Optional<LoteMovimentacao> obj = repo.findById(id);

		return obj. orElseThrow(() -> new ObjectNotFoudException("Objet non trouvé"));
	}
	@Transactional
	public LoteMovimentacao insert(LoteMovimentacao obj) throws Exception {
		for (Movimentacao mov : obj.getListMovimentacao()) {
			if (!movimentacaoService.insert(mov)) {
				throw new Exception("Negativo");
			}
		}
		
		obj.setCodigo(null);
		repo.saveAndFlush(obj);
		
		return obj;
	}
	

	public LoteMovimentacao update(LoteMovimentacao objAlterado) throws Exception {
		LoteMovimentacao objBanco = findById(objAlterado.getCodigo());
		
		objAlterado.setTipoMovimentacaoEnum(objBanco.getTipoMovimentacaoEnum());
		
		//Define qual é a data mais antiga par aobter a lista de 
		LocalDateTime dataMaisAntiga = null;
		Boolean alterarTodasPosteriores = false;
		Boolean desfazerTodosSaldosLista= false;
		Boolean usarDataMovimentacao = true;
		
		if (objAlterado.getData().isBefore(objBanco.getData())) {
			usarDataMovimentacao = false;
			dataMaisAntiga = objAlterado.getData();
			desfazerTodosSaldosLista = false;
			alterarTodasPosteriores = true;
			
		} else if(objAlterado.getData().isAfter(objBanco.getData())) {
			dataMaisAntiga = objBanco.getData();
			usarDataMovimentacao = true;
			alterarTodasPosteriores = false;
			desfazerTodosSaldosLista = true;
		} 
		
		for (Movimentacao mov : objAlterado.getListMovimentacao()) {
			movimentacaoService.update(mov,dataMaisAntiga,alterarTodasPosteriores,desfazerTodosSaldosLista,usarDataMovimentacao);
		}
		
//		if (dataAlterada || valorAlterado || quantidadeAlterada) {
//			List<Movimentacao> movimentacoesPosteriores = repo.obterMovimentacoesPosteriores (objBanco.getData(),objBanco.getProduto().getId());
//		}
		
		
		updateData(objBanco,objAlterado);
		return repo.save(objBanco);
	}
	
	private void updateData(LoteMovimentacao objBanco, LoteMovimentacao objAnterado) {
		//		objBanco.setStatus(objAnterado.getStatus());
		
		objBanco.setData(objAnterado.getData());
		objBanco.setCampanha(objAnterado.getCampanha());
		objBanco.setDoador(objAnterado.getDoador());
		objBanco.setFamilia(objAnterado.getFamilia());
		objBanco.setInstituicao(objAnterado.getInstituicao());
		
		
	}

//	public LoteMovimentacao fromDTO(LoteMovimentacaoGetDTO loteDTO) {
//		
//		LoteMovimentacao lote = new LoteMovimentacao( null,loteDTO.getNome(),null,
//				loteDTO.getTipoLoteMovimentacao(),loteDTO.getTipoMedida(),
//				null,null,null,null,loteDTO.getDescricao(),null );		
//		
//		return lote;
//	}
	
	public LoteMovimentacao fromDTO(LoteMovimentacaoGetDTO produtoGetDto) {
		
		LoteMovimentacao produto = new LoteMovimentacao();
		return produto;
	}

	public LoteMovimentacaoGetDTO toDTO(LoteMovimentacao lote) {
		// TODO Auto-generated method stub
		
		LoteMovimentacaoGetDTO loteDTO = new LoteMovimentacaoGetDTO(lote);
		
		
		return loteDTO;
	}

	public LoteMovimentacao fromInsertDTO(LoteMovimentacaoInsertDTO o) {
//		lista.map(obj -> new ProdutoGetDTO(obj));
//		perfis.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());
		Campanha campanha = null;
		Doador doador = null;
		Instituicao instituicao = null;
		Familia familia = null;
		
		if (o.getIdCampanha() != null)
			campanha = new Campanha(o.getIdCampanha());
		
		if (o.getIdDoador() != null)
			doador = new Doador(o.getIdDoador());
		
		if (o.getIdFamilia() != null)
			familia = new Familia(o.getIdFamilia());
		
		if (o.getIdInstituicao() != null )
			instituicao = new Instituicao(o.getIdInstituicao());
			
		
		LoteMovimentacao lote = new LoteMovimentacao(null, o.getNumeroEntrada(), campanha, 
				instituicao, doador, o.getData(), 
				familia, null, o.getTipoMovimentacaoEnum(),null);
	
		List<Movimentacao> listaMovimentacao = o.getListMovimentacao().stream().map
				(x -> new Movimentacao(x.getId(),new Produto(x.getIdProduto()),lote,x.getQuantidade(),x.getValor(),x.getNumeroNF(),x.getDescricaoNota(),o.getData(),null )).
				collect(Collectors.toList());
		
		
		lote.setListMovimentacao(listaMovimentacao);
		
		return lote;
	}
	
	public void deletar (Integer id) {
		LoteMovimentacao obj = findById(id);
		repo.save(obj);
	}
	
    
    public FamiliasAtingidasGrafico obterInfosGrafico() {
    	List<String> meses = new ArrayList<>();
    	List<Long> quantidade = new ArrayList<>();
    	
    	LocalDate dataAtual = LocalDate.now();
    	
        // Gera os objetos de data para os últimos 9 meses, incluindo o mês atual
        for (int i = 0; i < 12; i++) {
            LocalDate primeiroDiaDoMes = dataAtual.withDayOfMonth(1).minusMonths(i);
            LocalDate ultimoDiaDoMes = primeiroDiaDoMes.with(TemporalAdjusters.lastDayOfMonth());
            
            Long l = repo.countDistinctFamilies(primeiroDiaDoMes, ultimoDiaDoMes);
            
            meses.add(DateUtil.obterMes(primeiroDiaDoMes.getMonthValue()).substring(0, 3) );
            quantidade.add(l);
            
        }
        FamiliasAtingidasGrafico f = new FamiliasAtingidasGrafico();
        f.setMes(meses);
        f.setQuantidade(quantidade);
        
        return f;
    	
    }
	
}
