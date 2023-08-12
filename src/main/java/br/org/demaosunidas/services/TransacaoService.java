package br.org.demaosunidas.services;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.org.demaosunidas.domain.Conta;
import br.org.demaosunidas.domain.Doador;
import br.org.demaosunidas.domain.Familia;
import br.org.demaosunidas.domain.Instituicao;
import br.org.demaosunidas.domain.Saldo;
import br.org.demaosunidas.domain.Transacao;
import br.org.demaosunidas.domain.enums.TipoTransacaoEnum;
import br.org.demaosunidas.dto.ContaDTO;
import br.org.demaosunidas.dto.FamiliaDTO;
import br.org.demaosunidas.dto.TransacaoDTO;
import br.org.demaosunidas.repository.TransacaoRepository;
import br.org.demaosunidas.services.exception.ObjectNotFoudException;

@Service
public class TransacaoService {
	
	@Autowired
	private TransacaoRepository repo;
	
	@Autowired
	private SaldoService saldoService;
	@Autowired
	private FamiliaService familiaService;
	@Autowired
	private DoadorService doadorService;
	@Autowired
	private InstituicaoService instituicaoService;
	
	public List<Transacao> listar() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}
	
	public List<Transacao> search (LocalDate dataInicio,LocalDate dataFim, Integer idConta) {
		
		PageRequest pageRequest = PageRequest.of(0, 150, Direction.ASC, "data");
				
		return repo.obterTransacoesPorData(dataInicio, dataFim, idConta, pageRequest).getContent();
	}
	
	public List<TransacaoDTO> listarTransacaoComSaldo (LocalDate dataInicio,LocalDate dataFim, Integer idConta) {
		
		Saldo saldo = saldoService.obterUltimoSaldo(idConta, dataInicio);
		
		List<Transacao> listaTransacao = this.search(dataInicio, dataFim, idConta);
		List<TransacaoDTO> listaTransacaoDTO = new ArrayList<>();
		
		BigDecimal valorASomar = saldo.getValor();
		
		for (Transacao x : listaTransacao) {
			TransacaoDTO dto = new TransacaoDTO(x,valorASomar,true);
			if (x.getFamilia() != null)
				dto.setIdFamilia(x.getFamilia().getId());
			if (x.getDoador() != null)
				dto.setIdDoador(x.getDoador().getId());
			if (x.getInstituicao() != null)	
				dto.setIdInstituicao(x.getInstituicao().getId());
			
			dto.setTipoParceiro(x.getTipoParceiroEnum());
			valorASomar = dto.getSaldo();
			
			listaTransacaoDTO.add(dto);
		}
        Collections.reverse(listaTransacaoDTO);
		return listaTransacaoDTO;
	}
	
	
	public Transacao findById(Integer id) {
		Optional<Transacao> obj = repo.findById(id);

		return obj. orElseThrow(() -> new ObjectNotFoudException("Objet non trouvé"));
	}
	
	@Transactional
	public TransacaoDTO insert(TransacaoDTO dto) {
		dto.setId(null);
		
		Transacao entity =  TransacaoService.dtoToEntity (dto);
		ajustarParceiro(entity);
		
		if (dto.getTipoTransacaoEnum().equals(TipoTransacaoEnum.PAGAMENTO) && dto.getValor().floatValue() > 0f) {
			entity.setValor(dto.getValor().negate());
		} else if(dto.getTipoTransacaoEnum().equals(TipoTransacaoEnum.RECEITA) && dto.getValor().floatValue() < 0f) {
			entity.setValor(dto.getValor().negate());
		}
		
		Saldo s = new Saldo();
		s.setConta((entity.getConta()));
		s.setData(entity.getData());
		s.setValor(entity.getValor());
		
		saldoService.insert(s);
		
		entity = repo.save(entity);
		
		return TransacaoService.entityToDto(entity);
	}
	
	private static Transacao dtoToEntity(TransacaoDTO dto) {
		Transacao entity = new Transacao();
		
		entity.setId(dto.getId());
		
		entity.setData(dto.getData());
		entity.setDescricao(dto.getDescricao());
		entity.setDoador( new Doador(dto.getIdDoador()));
		entity.setFamilia(new Familia(dto.getIdFamilia()));
		entity.setInstituicao( new Instituicao(dto.getIdInstituicao()));
		entity.setTipoTransacaoEnum(dto.getTipoTransacaoEnum());
		entity.setValor(dto.getValor());
		entity.setTipoParceiroEnum(dto.getTipoParceiro());
		Conta entityConta = new Conta(dto.getConta());
		entity.setConta(entityConta);
		
		return entity;
	}
	
	
	private static TransacaoDTO entityToDto(Transacao entity) {
		TransacaoDTO dto = new TransacaoDTO();
		
		dto.setId(entity.getId());
		
		dto.setData(entity.getData());
		dto.setDescricao(entity.getDescricao());
		dto.setDoador(  DoadorService.entityToDto(entity.getDoador()) );
		dto.setFamilia(new FamiliaDTO(entity.getFamilia()));
		
		dto.setInstituicao(InstituicaoService.entityToDto(entity.getInstituicao()));
		dto.setTipoTransacaoEnum(entity.getTipoTransacaoEnum());
		dto.setValor(entity.getValor());
		dto.setTipoParceiro(entity.getTipoParceiroEnum());

		
		ContaDTO contaDTO = new ContaDTO(entity.getConta());
		dto.setConta(contaDTO);
		
		return dto;
	}

	@Transactional
	public void update(TransacaoDTO dto) {
		
		Transacao obj = TransacaoService.dtoToEntity(dto);
		
		Transacao transacaoBanco = repo.findById(obj.getId()).get();
		
		//Verificar a necessidade de alterar os SALDOS
		
		if (obj.getValor().compareTo(transacaoBanco.getValor()) != 0  || 
				!obj.getData().equals(transacaoBanco.getData()) || 
				!obj.getConta().getId().equals(transacaoBanco.getConta().getId())) {
			
			saldoService.desfazerSaldosEmFrente(transacaoBanco);
			
			Saldo novoSaldo = new Saldo();
			novoSaldo.setConta(obj.getConta());
			novoSaldo.setData(obj.getData());
			novoSaldo.setValor(obj.getValor());
			
			saldoService.insert(novoSaldo);
		}
		
		// Data é a mesma
		// Valor é o mesmo
		
		// Conta é a mesma
				  /*
				   *  ---- Data não é a mesma?
				   *       
				   */
				
		
		
		//Verificar se a conta é a mesma
		
		
		/*
		  Conta não é a mesma
	 	  obter o saldo daquela dia,
		  diminuir o saldo com valor original (transacaoBanco)
		 * obter os saldos para frente
		 * diminuir dos saldos da frente o valor original
		 * criar objeto saldo e salvar o novo saldo com a conta nova
		 * -------
		 */
		
		transacaoBanco.setConta(obj.getConta());
		transacaoBanco.setData(obj.getData());
		transacaoBanco.setDescricao(obj.getDescricao());
		transacaoBanco.setTipoTransacaoEnum(obj.getTipoTransacaoEnum());
		transacaoBanco.setValor(obj.getValor());
		
		if (dto.getIdDoador() != null) {
			transacaoBanco.setDoador(obj.getDoador());
			transacaoBanco.setFamilia(null);
			transacaoBanco.setInstituicao(null);
		} else if (dto.getIdFamilia() != null) {
			transacaoBanco.setFamilia(obj.getFamilia());
			transacaoBanco.setDoador(null);
			transacaoBanco.setInstituicao(null);
		} else {
			transacaoBanco.setDoador(null);
			transacaoBanco.setFamilia(null);
			transacaoBanco.setInstituicao(obj.getInstituicao());
		}

		transacaoBanco.setTipoParceiroEnum(obj.getTipoParceiroEnum());
		
		repo.save(transacaoBanco);
	}
	
	
	@Transactional
	public void delete (Integer id) {
		Transacao transacaoBanco = repo.findById(id).get();
		saldoService.desfazerSaldosEmFrente(transacaoBanco);
		repo.delete(transacaoBanco);
	}
	
	public List<Transacao> buscarTransacaoPorConta(Integer codConta){
		Conta lote = new Conta(codConta);
		
		return repo.findByConta(lote);
	}
	
	
	private void ajustarParceiro(Transacao obj) {
	switch (obj.getTipoParceiroEnum()) {
	case DOADOR:
		obj.setFamilia(null);
		obj.setInstituicao(null);
		obj.setDoador(doadorService.findById(obj.getDoador().getId()));
		
		break;
	case FAMILIA:
		obj.setDoador(null);
		obj.setInstituicao(null);
		obj.setFamilia(familiaService.findById(obj.getFamilia().getId()));
		
		break;
	default:
		obj.setDoador(null);
		obj.setFamilia(null);
		obj.setInstituicao(instituicaoService.findById(obj.getInstituicao().getId()));
		break;
	}
}
	
}
