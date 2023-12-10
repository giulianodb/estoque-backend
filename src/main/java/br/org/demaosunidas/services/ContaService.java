package br.org.demaosunidas.services;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.stereotype.Service;

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;

import br.org.demaosunidas.domain.Conta;
import br.org.demaosunidas.domain.Saldo;
import br.org.demaosunidas.domain.Transacao;
import br.org.demaosunidas.domain.enums.Status;
import br.org.demaosunidas.domain.enums.TipoContaEnum;
import br.org.demaosunidas.domain.enums.TipoTransacaoEnum;
import br.org.demaosunidas.dto.CategoriaDTO;
import br.org.demaosunidas.dto.CentroCustoDTO;
import br.org.demaosunidas.dto.ContaDTO;
import br.org.demaosunidas.dto.RelatorioExtratoDTO;
import br.org.demaosunidas.dto.TransacaoDTO;
import br.org.demaosunidas.repository.ContaRepository;
import br.org.demaosunidas.services.exception.ObjectNotFoudException;
import br.org.demaosunidas.util.DateUtil;
import br.org.demaosunidas.util.FileUtil;
import br.org.demaosunidas.util.NumeroUtil;

@Service
public class ContaService {
	
	@Autowired
	private ContaRepository repo;
	
	@Autowired
	private SaldoService saldoService;
	
	@Value("${caminho.html.extrato}")
	private String caminhoDiretorioExtrato;
	
	@Value("${url.logo}")
	private String caminhoLogo;
	
	public List<Conta> listar() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}
	
//	public Page<Campanha> search (String nome, Integer page,Integer linesPerPage, String orderBy, String direction) {
//		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
//				
//		
//		return repo. searchQuery(nome,pageRequest);
//	}
	
	public Conta findById(Integer id) {
		Optional<Conta> obj = repo.findById(id);

		return obj. orElseThrow(() -> new ObjectNotFoudException("Objet non trouvé"));
	}
	
	
	public ContaDTO insert(ContaDTO obj) {
		obj.setId(null);
		
				Conta entity = new Conta(obj);
		repo.save(entity);
		obj.setId(entity.getId());
		
		return obj;
	}

	
	public Conta update(ContaDTO objDTO) {

		Conta objAlterado = new Conta(objDTO);
		
		Conta objBanco = findById(objAlterado.getId());
		
		updateData(objBanco,objAlterado);
		return repo.save(objBanco);
	}
	
	public void delete (Integer id) {
		Conta obj = findById(id);
		obj.setStatus(Status.INATIVO);
		repo.save(obj);
	}
	
	private void updateData(Conta objBanco, Conta objAlterado) {
		objBanco.setAgenciaConta(objAlterado.getAgenciaConta());
		objBanco.setNomeConta(objAlterado.getNomeConta());
		objBanco.setNumeroConta(objAlterado.getNumeroConta());
		objBanco.setStatus(objAlterado.getStatus());
		objBanco.setStatus(objAlterado.getStatus());
		objBanco.setTipoConta(objAlterado.getTipoConta());
		
	}
	
public void gerarExtrato (RelatorioExtratoDTO extrato, LocalDate dataInicio, LocalDate dataFim) throws Exception {
    	
    	String comeco = FileUtil.readFile(caminhoDiretorioExtrato + "inicio.html");
    	comeco = comeco.replace("${dataEmissao}", extrato.getDataEmissao());
    	comeco = comeco.replace("${periodo}", extrato.getPeriodo());
    	comeco = comeco.replace("${urlLogo}", this.caminhoLogo);
    	
    	
    	for (ContaDTO conta : extrato.getListConta()) {
    		String abrirConta = FileUtil.readFile(caminhoDiretorioExtrato + "abrir_conta.html");
    		abrirConta = abrirConta.replace("${nomeConta}", conta.getNomeConta());
    		
    		for (TransacaoDTO t : conta.getListTransacao()) {
    			String transacao = FileUtil.readFile(caminhoDiretorioExtrato + "transacao.html");
    			transacao = transacao.replace("${data}", DateUtil.dataToString(t.getData()));
    			transacao = transacao.replace("${descricao}", t.getDescricao());
    			
    			if (t.getId() != null) {
    				transacao = transacao.replace("${numero}", t.getId().toString());
    			} else {
    				transacao = transacao.replace("${numero}", " ");
    			}
    			
    			transacao = transacao.replace("${fornecedor}", t.getNomeParceiro());
    			
    			if (t.getCategoria()!= null && t.getCategoria().getNome() != null) {
    				transacao = transacao.replace("${categoria}", t.getCategoria().getNome());
    			} else {
    				transacao = transacao.replace("${categoria}", "");	
    			}
    			
    			if (t.getCentroCusto().getDescricao() != null) {
    				transacao = transacao.replace("${centroCusto}", t.getCentroCusto().getDescricao());
    			} else {
    				transacao = transacao.replace("${centroCusto}", "");
    			}
    			
    			transacao = transacao.replace("${valor}",  NumeroUtil.formatarMoeda(t.getValor()).substring(3) );
    			
    			if (t.getValor().longValue() < 0l) {
    				transacao = transacao.replace("${color}", "red");
    				
    				
    			}
    			
    			String conteudoSaldo = NumeroUtil.formatarMoeda(t.getSaldo()).substring(3);
    			
    			if (t.getSaldo().longValue() < 0) {
    				conteudoSaldo = "<strong style=\"color:red\">" + conteudoSaldo + "</strong>";
    			}
    			
    			
	    			if (t.getSaldo().longValue() < 0) {
	        			transacao = transacao.replace("${saldo}", conteudoSaldo+ " <strong style=\"color:red\"> D </strong> " );
	    			} else {
	    				transacao = transacao.replace("${saldo}", conteudoSaldo + " C ");
	    			}
    			
    			
    			
    			abrirConta = abrirConta+transacao;
			}
    		
    		String fecharConta = FileUtil.readFile(caminhoDiretorioExtrato + "fechar_conta.html");
    		fecharConta = fecharConta.replace("${nomeConta}", conta.getNomeConta());
    		fecharConta = fecharConta.replace("${totalLancamento}", conta.getTotalLancamentos().toString());
    		fecharConta = fecharConta.replace("${totalEntradas}", NumeroUtil.formatarMoeda(conta.getTotalEntradas()));
    		fecharConta = fecharConta.replace("${totalSaidas}",  "<strong style=\"color:red\">" +NumeroUtil.formatarMoeda(conta.getTotalSaidas()) + "</strong>");
    		
    		String conteudoSaldoFinal = NumeroUtil.formatarMoeda(conta.getSaldoFinal());
    		if (conta.getSaldoFinal().longValue() < 0) {
    			conteudoSaldoFinal = "<strong style=\"color:red\">" + conteudoSaldoFinal + "</strong>";
    		}
    		
    		fecharConta = fecharConta.replace("${saldoFinal}", conteudoSaldoFinal);
    		
    		comeco = comeco+abrirConta+fecharConta;
		}
    	
    	String fim = FileUtil.readFile(caminhoDiretorioExtrato + "fim.html");
    	fim = fim.replace("${totalLancamentos}", extrato.getTotalLancamentos().toString());
    	fim = fim.replace("${totalEntradas}", NumeroUtil.formatarMoeda(extrato.getTotalEntradas()));
		fim = fim.replace("${totalSaidas}", "<strong style=\"color:red\">" + NumeroUtil.formatarMoeda(extrato.getTotalSaidas()) + "</strong>");
		
		String conteudoSaldoFinal = NumeroUtil.formatarMoeda(extrato.getSaldoFinal());
		if (extrato.getSaldoFinal().longValue() < 0) {
			conteudoSaldoFinal = "<strong style=\"color:red\">" + conteudoSaldoFinal + "</strong>";
		}
		
		fim = fim.replace("${saldoFinal}", conteudoSaldoFinal);
    	
		
		String relatorio = comeco + fim;
		
    	System.out.println(relatorio);
    	
        try (OutputStream os = new FileOutputStream("/tmp/extrato.pdf")) {
            PdfRendererBuilder builder = new PdfRendererBuilder();
            builder.useDefaultPageSize(210, 297, PdfRendererBuilder.PageSizeUnits.MM);
//             builder.useD useDefaultPageSizeAndMargins( PdfRendererBuilder.PAGE_SIZE_LETTER_HEIGHT, PdfRendererBuilder.PageSizeUnits.MM, PdfRendererBuilder.PAGE_SIZE_LETTER_WIDTH);
            
            builder.useFastMode();
            builder.withHtmlContent( relatorio, null);
            
            builder.toStream(os);
            builder.run();
        }
    }


public void relatorioExtrato(LocalDate dataInicio,LocalDate dataFim) {
	
	System.out.println("SUCESSO");
	
	List<Conta> c = repo.getContasComSaldoAndMovimentosPorData(dataInicio,dataFim);
	List<ContaDTO> listaContaDto = new ArrayList<>();
	
	BigDecimal saldoTotalFinal = BigDecimal.ZERO;
	BigDecimal totalEntrdasFinal = BigDecimal.ZERO;
	BigDecimal totalSaidasFinal = BigDecimal.ZERO;
	Integer totalLancamentos = 0;
	
	for (Conta conta : c) {
		
		BigDecimal saldoFinalConta = BigDecimal.ZERO;
		BigDecimal totalEntrdasConta = BigDecimal.ZERO;
		BigDecimal totalSaidasConta = BigDecimal.ZERO;
		
		ContaDTO dto = entityToDto(conta);
		
		Saldo ultimoSaldo = saldoService.obterUltimoSaldo(conta.getId(), dataInicio);
		
		dto.setSaldo(SaldoService.entityToDto(ultimoSaldo));
		
		saldoFinalConta = saldoFinalConta.add(dto.getSaldo().getValor());
		
		TransacaoDTO tInicial = new TransacaoDTO();
		tInicial.setData(dataInicio);
		tInicial.setDescricao("Saldo em "+DateUtil.dataToString(dataInicio));
		tInicial.setValor(dto.getSaldo().getValor());
		tInicial.setSaldo(dto.getSaldo().getValor());
		tInicial.setCategoria(new CategoriaDTO());
		tInicial.setCentroCusto(new CentroCustoDTO());
		
		dto.getListTransacao().add(tInicial);
		
		for (Transacao t : conta.getListTransacao()) {
			TransacaoDTO transacaoDto = TransacaoService.entityToDto(t);
			saldoFinalConta = saldoFinalConta.add(t.getValor());
			
			if (transacaoDto.getTipoTransacaoEnum().equals(TipoTransacaoEnum.RECEITA)) {
				totalEntrdasConta = totalEntrdasConta.add(transacaoDto.getValor());
			} else {
				totalSaidasConta = totalSaidasConta.add(transacaoDto.getValor());
			}
			
			transacaoDto.setSaldo(saldoFinalConta);
			
			dto.getListTransacao().add(transacaoDto);
		}
		
		dto.setTotalLancamentos(dto.getListTransacao().size());
		dto.setTotalEntradas(totalEntrdasConta);
		dto.setTotalSaidas(totalSaidasConta);
		dto.setSaldoFinal(saldoFinalConta);
		
		saldoTotalFinal = saldoTotalFinal.add(saldoFinalConta);
		totalEntrdasFinal = totalEntrdasFinal.add(totalEntrdasConta);
		totalSaidasFinal = totalSaidasFinal.add(totalSaidasConta);
		totalLancamentos += dto.getTotalLancamentos();
		
		listaContaDto.add(dto);
	}
	
	RelatorioExtratoDTO extrato = new RelatorioExtratoDTO();
	
	extrato.setListConta(listaContaDto);
	extrato.setDataEmissao(DateUtil.dataToString(LocalDate.now()));
	extrato.setPeriodo(DateUtil.dataToString(dataInicio) +" - "+ DateUtil.dataToString(dataFim));
	extrato.setSaldoFinal(saldoTotalFinal);
	extrato.setTotalEntradas(totalEntrdasFinal);
	extrato.setTotalLancamentos(totalLancamentos);
	extrato.setTotalSaidas(totalSaidasFinal);
	
	
	try {
		gerarExtrato(extrato, dataInicio, dataFim);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	
}
    

public static ContaDTO entityToDto(Conta entity) {
	ContaDTO dto = new ContaDTO();
	
	dto.setAgenciaConta(entity.getAgenciaConta());
	dto.setId(entity.getId());
	dto.setNomeConta(entity.getNomeConta());
	dto.setNumeroConta(entity.getNumeroConta());
	dto.setTipoConta(entity.getTipoConta());
	dto.setStatus(entity.getStatus());
	dto.setListTransacao(new ArrayList<>());
	
	return dto;
}
	
}

