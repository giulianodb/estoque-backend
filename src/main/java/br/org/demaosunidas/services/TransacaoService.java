package br.org.demaosunidas.services;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;

import br.org.demaosunidas.domain.Categoria;
import br.org.demaosunidas.domain.CentroCusto;
import br.org.demaosunidas.domain.Conta;
import br.org.demaosunidas.domain.Doador;
import br.org.demaosunidas.domain.Familia;
import br.org.demaosunidas.domain.Instituicao;
import br.org.demaosunidas.domain.Saldo;
import br.org.demaosunidas.domain.Transacao;
import br.org.demaosunidas.domain.enums.TipoTransacaoEnum;
import br.org.demaosunidas.dto.CategoriaDTO;
import br.org.demaosunidas.dto.CentroCustoDTO;
import br.org.demaosunidas.dto.ContaDTO;
import br.org.demaosunidas.dto.FamiliaDTO;
import br.org.demaosunidas.dto.GrupoCategoriaDTO;
import br.org.demaosunidas.dto.MesesFinanceiroGrafico;
import br.org.demaosunidas.dto.RelatorioRazaoDTO;
import br.org.demaosunidas.dto.TransacaoDTO;
import br.org.demaosunidas.repository.GrupoCategoriaRepository;
import br.org.demaosunidas.repository.TransacaoRepository;
import br.org.demaosunidas.services.exception.ObjectNotFoudException;
import br.org.demaosunidas.util.DateUtil;
import br.org.demaosunidas.util.FileUtil;
import br.org.demaosunidas.util.NumeroUtil;

@Service
public class TransacaoService {
	
	@Autowired
	private TransacaoRepository repo;
	
	@Autowired
	private GrupoCategoriaRepository repoGC;
	
	@Autowired
	private SaldoService saldoService;
	@Autowired
	private FamiliaService familiaService;
	@Autowired
	private DoadorService doadorService;
	@Autowired
	private InstituicaoService instituicaoService;
	
	@Value("${caminho.html.razao}")
	private String caminhoDiretorioReport;
	
	@Value("${caminho.html.extrato}")
	private String caminhoDiretorioExtrato;
	
	@Value("${caminho.html.recibo}")
	private String caminhoDiretorioReportRecibo;
	
	@Value("${url.logo}")
	private String caminhoLogo;
	
	public List<Transacao> listar() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}
	
	public List<Transacao> search (LocalDate dataInicio,LocalDate dataFim, Integer idConta) {
		
		PageRequest pageRequest = PageRequest.of(0, 150, Direction.ASC, "data");
				
		return repo.obterTransacoesPorDataEConta(dataInicio, dataFim, idConta, pageRequest).getContent();
	}
	
	public List<TransacaoDTO> listarTransacaoComSaldo (LocalDate dataInicio,LocalDate dataFim, Integer idConta) {
		
		Saldo saldo = saldoService.obterUltimoSaldo(idConta, dataInicio);
		
//		//TODO TESTE retirar
//		try {
//			relatorioRazao(dataInicio,dataFim);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
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
			
			dto.setCategoria(new CategoriaDTO(x.getCategoria().getId()));
			dto.setCentroCusto(new CentroCustoDTO(x.getCentroCusto().getId()));
			
			dto.setTipoParceiro(x.getTipoParceiroEnum());
			valorASomar = dto.getSaldo();
			
			listaTransacaoDTO.add(dto);
		}
        Collections.reverse(listaTransacaoDTO);
		return listaTransacaoDTO;
	}
	
	public void relatorioRazaoTeste(LocalDate dataInicio,LocalDate dataFim) {
//		List<GrupoCategoria> c = repoGC.buscarCategoriaTeste();
		
		System.out.println("opi");
		
	}
	
	public void relatorioRazao(LocalDate dataInicio,LocalDate dataFim) {
		List<Transacao> listaTransacao = repo.obterTransacoesPorData(dataInicio, dataFim);
		
		RelatorioRazaoDTO razao = new RelatorioRazaoDTO();
		razao.setGrupoCategoriaDespesa(new ArrayList<>());
		razao.setGrupoCategoriaReceita(new ArrayList<>());
		
		razao.setTotalDespesa(new BigDecimal(0));
		razao.setTotalReceita(new BigDecimal(0));
//		razao.setSaldoTotalString("R$ 5.000,00");
		
		
		for(Transacao t : listaTransacao) {
			if (razao.getList(t.getTipoTransacaoEnum()).size() > 0 && razao.getList(t.getTipoTransacaoEnum()).contains(CategoriaService.entityToDtoGrupo(t.getCategoria().getGrupoCategoria()))) {
				if (t.getTipoTransacaoEnum().equals(TipoTransacaoEnum.RECEITA)) {
					razao.setTotalReceita(razao.getTotalReceita().add(t.getValor()));
				} else {
					razao.setTotalDespesa(razao.getTotalDespesa().add(t.getValor()));
				}
				
				GrupoCategoriaDTO grupo = null;
						
						for(GrupoCategoriaDTO g : razao.getList(t.getTipoTransacaoEnum())) {
							if (g.equals(CategoriaService.entityToDtoGrupo(t.getCategoria().getGrupoCategoria()))) {
								grupo = g;
								break;
							}
						}
							
							grupo.setValorTotal(grupo.getValorTotal().add(t.getValor()));
							
							if (grupo.getListaCategoria() == null) {
								grupo.setListaCategoria(new ArrayList<>());
							}
							if (grupo.getListaCategoria().contains(CategoriaService.entityToDto(t.getCategoria()))) {
								for(CategoriaDTO catDTO : grupo.getListaCategoria()) {
									if (catDTO.getId().equals(t.getCategoria().getId())) {
										
										catDTO.getListaTransacao().add(TransacaoService.entityToDto(t));
										catDTO.setSoma(catDTO.getSoma().add(t.getValor()));
									}
								}
							} else {
								CategoriaDTO catDTO = CategoriaService.entityToDto(t.getCategoria());
								catDTO.setSoma(catDTO.getSoma().add(t.getValor()));
								List<TransacaoDTO> tt = new ArrayList<>();
								tt.add(TransacaoService.entityToDto(t));
								catDTO.setListaTransacao(tt);
								grupo.getListaCategoria().add(catDTO);
							}
							
			} else {
				GrupoCategoriaDTO grupoDTO =  CategoriaService.entityToDtoGrupo(t.getCategoria().getGrupoCategoria());
				grupoDTO.setValorTotal(t.getValor());
				List<CategoriaDTO> listaCategoria = new ArrayList<>();
				List<TransacaoDTO> transacaoLista = new ArrayList<>();
				transacaoLista.add(TransacaoService.entityToDto(t));
				CategoriaDTO categoriaDTO = CategoriaService.entityToDto(t.getCategoria());
				categoriaDTO.setListaTransacao(transacaoLista);
				categoriaDTO.setSoma(t.getValor());
				listaCategoria.add(categoriaDTO);
				grupoDTO.setListaCategoria(listaCategoria);
				
				List<GrupoCategoriaDTO> grupoLista = new ArrayList<>();
				grupoLista.add(grupoDTO);
				if (t.getTipoTransacaoEnum().equals(TipoTransacaoEnum.RECEITA)) {
					razao.getGrupoCategoriaReceita().addAll(grupoLista);
					razao.setTotalReceita(razao.getTotalReceita().add(t.getValor()));
				} else {
					razao.getGrupoCategoriaDespesa().addAll(grupoLista);
					razao.setTotalDespesa(razao.getTotalDespesa().add(t.getValor()));
				}
			}
		}
		
		try {
			
			gerarRelatorio(razao, dataInicio, dataFim);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Transactional
	public Transacao findById(Integer id) {
		Optional<Transacao> obj = repo.findById(id);
		
		if (obj.get().getDoador() != null && obj.get().getDoador().getId() != null) {
			obj.get().setDoador(doadorService.findById(obj.get().getDoador().getId()));
		}
		
		if (obj.get().getInstituicao() != null && obj.get().getInstituicao().getId() != null) {
			obj.get().setInstituicao  (instituicaoService.findById(obj.get().getInstituicao().getId()));
		}
		
		obj.get().getInstituicao();
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
		
		CentroCusto cc = new CentroCusto(dto.getCentroCusto().getId());
		entity.setCentroCusto(cc);
		
		if (dto.getCategoria() != null) {
			Categoria categoria = new Categoria(dto.getCategoria().getId());
			entity.setCategoria(categoria);
		} else {
			System.out.println("NULL");
		}
		
		return entity;
	}
	
	
	public static TransacaoDTO entityToDto(Transacao entity) {
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
		
		CategoriaDTO categoriaDto = new CategoriaDTO();
		categoriaDto.setId(entity.getCategoria().getId());
		categoriaDto.setNome(entity.getCategoria().getNome());
		dto.setCategoria(categoriaDto);
		
		CentroCustoDTO centroCustoDTO = new CentroCustoDTO();
		centroCustoDTO.setId(entity.getCentroCusto().getId());
		centroCustoDTO.setNome(entity.getCentroCusto().getNome());
		
		dto.setCentroCusto(CentroCustoService.entityToDto(entity.getCentroCusto()));
		
		return dto;
	}

	@Transactional
	public TransacaoDTO update(TransacaoDTO dto) {
		
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
		
		return TransacaoService.entityToDto(transacaoBanco);
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
	
	case CPF:
		obj.setFamilia(null);
		obj.setInstituicao(null);
		obj.setAnonimo(false);
		obj.setDoador(doadorService.findById(obj.getDoador().getId()));
		
		break;
	case CPJ:
		obj.setFamilia(null);
		obj.setInstituicao(instituicaoService.findById(obj.getInstituicao().getId()));
		obj.setAnonimo(false);
		obj.setDoador(null);
		
		break;
	
	case FPF:
		obj.setFamilia(null);
		obj.setInstituicao(null);
		obj.setAnonimo(false);
		obj.setDoador(doadorService.findById(obj.getDoador().getId()));
		
		break;
	
	case FPJ:
		obj.setFamilia(null);
		obj.setInstituicao(instituicaoService.findById(obj.getInstituicao().getId()));
		obj.setAnonimo(false);
		obj.setDoador(null);
		
		break;
	
	case ANONIMO:
		obj.setDoador(null);
		obj.setFamilia(null);
		obj.setInstituicao(null);
		obj.setAnonimo(true);
		break;
	default:
		break;
	}
}
	
	
	
    
    public void gerarRelatorio (RelatorioRazaoDTO razao, LocalDate dataInicio, LocalDate dataFim) throws Exception {
	String comeco = FileUtil.readFile(caminhoDiretorioReport + "comeco.txt");
	comeco = comeco.replace("${dataEmissao}", DateUtil.dataToString(new Date()));
	comeco = comeco.replace("${nomeRelatorio}", "Relatório Razão");
	comeco = comeco.replace("${periodo}", "de " + DateUtil.dataToString(dataInicio)+ " a " + DateUtil.dataToString(dataFim));
	
	for(int z = 0; z<= 1; z++) {
    	List<GrupoCategoriaDTO> listaTipo = new ArrayList<>();
    	String valorTotalTipo = "";
    	String tipo = "";
		Boolean somarReceita = true;
		BigDecimal totalReceita = new BigDecimal(0);
		BigDecimal totalDespesa = new BigDecimal(0);
		
    	if (z==0) {
    		tipo = "Receita";
    		listaTipo = razao.getGrupoCategoriaReceita();
    		valorTotalTipo = razao.getTotalReceita().toString();
    	} else {
    		tipo = "Despesa";
    		listaTipo = razao.getGrupoCategoriaDespesa();
    		valorTotalTipo = razao.getTotalDespesa().toString();
    		somarReceita = false;
    	}
    	
    	
    	String abrirTipoReceita = FileUtil.readFile(caminhoDiretorioReport + "abrir-tipo.txt");
    	abrirTipoReceita = abrirTipoReceita.replace("${tipo}", tipo);
    	if (tipo.equals("Despesa")) {
    		abrirTipoReceita = abrirTipoReceita.replace("${totalTipo}", valorTotalTipo);
    		abrirTipoReceita = abrirTipoReceita.replace("${cor}", "red");
    	} else {
    		abrirTipoReceita = abrirTipoReceita.replace("${totalTipo}", valorTotalTipo);
    	}
    	
    	comeco += abrirTipoReceita;
    	
    	String grupoCategoriaAll = "";
    	
    	//grupoCategoria
    	int i = 0;
    	for (GrupoCategoriaDTO grupo  : listaTipo) {
    		String abrirGrupoCategoria = FileUtil.readFile(caminhoDiretorioReport + "abrir-grupo-categoria.txt");
    		abrirGrupoCategoria = abrirGrupoCategoria.replace( "${color}", tipo.equals("Despesa") ? "red" : "" );
    		abrirGrupoCategoria = abrirGrupoCategoria.replace("${id}", Integer.toString(i))
    				.replace("${nomeGrupoCategoria}", grupo.getNome())
    				.replace("${valorTotalGrupoCategoria}", grupo.getValorTotal().toString());
    		
    		//categoria
    		
    		String allCategorias = "";
    		
    		for ( CategoriaDTO categoria : grupo.getListaCategoria()) {
    			String abrirCategoria = FileUtil.readFile(caminhoDiretorioReport + "abrir-categoria.txt");
    			abrirCategoria = abrirCategoria.replace("${nomeCategoria}", categoria.getNome());
    			
    			String abrirMovimento = FileUtil.readFile(caminhoDiretorioReport + "abrir-tabela-movimentos.txt");
    			
    			for (TransacaoDTO t : categoria.getListaTransacao()) {
    				String movimento = FileUtil.readFile(caminhoDiretorioReport + "movimento.txt");
    				movimento = movimento.replace("${data}", DateUtil.dataToString(t.getData()))
    						.replace("${descricao}", t.getDescricao())
    						.replace("${centroCuso}", t.getCentroCusto().getNome())
    						.replace("${conta}", t.getConta().getNomeConta())
    						.replace("${pessoa}", t.getNomeParceiro())
    						.replace("${valor}", t.getValor().toString());
    				
    				abrirMovimento += movimento;
    			}
    			
    			
    			String fecharMovimento = FileUtil.readFile(caminhoDiretorioReport + "fechar-tabela-movimentos.txt");
    			fecharMovimento = fecharMovimento.replace("${totalCategoria}", categoria.getSoma().toString());
    			
    			abrirMovimento += fecharMovimento;
    			
    			abrirCategoria += abrirMovimento;
    			allCategorias += abrirCategoria;
    		}
    		
    		abrirGrupoCategoria += allCategorias;
    		
    		abrirGrupoCategoria += FileUtil.readFile(caminhoDiretorioReport + "fechar-grupo-categoria.txt");
    		
    		grupoCategoriaAll += abrirGrupoCategoria;
    	}
    	
    	comeco += grupoCategoriaAll;
    	String fecharTipo = FileUtil.readFile(caminhoDiretorioReport + "fechar-tipo.txt.txt");
		comeco += fecharTipo;
	
	
	}
	
	BigDecimal valorFinal = new BigDecimal(0);
	valorFinal = razao.getTotalReceita().add(razao.getTotalDespesa());
	String corResultadoFinal = "red";
	if (valorFinal.doubleValue() > 0) {
		corResultadoFinal = "";	
	}
	
	String fecharTipo = FileUtil.readFile(caminhoDiretorioReport + "fechar-tipo.txt.txt");
	
	String resultadoFinal = "<div class=\"report-section\"> <h1 class=\"section-title\">${tipo} <span style=\"color:${cor}\"> (${totalTipo}) </span></h1> "
			+ "<div class=\"flex-container\"> ";
	resultadoFinal += fecharTipo;
	
	resultadoFinal = resultadoFinal.replace("${tipo}", "Resultado Final");
	resultadoFinal = resultadoFinal.replace("${cor}", corResultadoFinal);
	resultadoFinal = resultadoFinal.replace("${totalTipo}", valorFinal.toString());
	
	comeco += resultadoFinal;
	
	String fim = FileUtil.readFile(caminhoDiretorioReport + "fim.txt");
	comeco += fim;
	
	
	
	/*
    <div class="report-section">
    <h1 class="section-title">${tipo} <span style="color:${cor}"> (${totalTipo}) </span></h1>  
    <div class="flex-container">
	
	*/
	System.out.println(comeco);
	
    try (OutputStream os = new FileOutputStream("/tmp/out.pdf")) {
        PdfRendererBuilder builder = new PdfRendererBuilder();
        builder.useFastMode();
//        builder.withHtmlContent( readFile("/home/giuliano/teste3.html"), null);
//        builder.withHtmlContent( readFile("/home/giuliano/teste2-2-0.html"), null);
        builder.withHtmlContent( comeco, null);
        
        builder.toStream(os);
        builder.run();
    }
}
    
    public void gerarRecibo (TransacaoDTO dto) throws Exception {
    	
    	String recibo = FileUtil.readFile(caminhoDiretorioReportRecibo + "recibo.txt");
    	recibo = recibo.replace("${urlLogo}", caminhoLogo);
    	recibo = recibo.replace("${numeroRecibo}", dto.getId().toString());
    	recibo = recibo.replace("${valorRecibo}", NumeroUtil.formatarMoeda(dto.getValor()));
    	recibo = recibo.replace("${nomeDoador}", dto.getNomeParceiro());
    	recibo = recibo.replace("${valorReciboExtenso}", NumeroUtil.converterValorPorExtenso(dto.getValor()));
    	recibo = recibo.replace("${referente}", dto.getDescricao());
    	recibo = recibo.replace("${data}", "Curitiba, "+ dto.getData().getDayOfMonth()+ " de "+DateUtil.obterMes(Integer.toString(dto.getData().getMonthValue())) +" de " + dto.getData().getYear());
    	
    	System.out.println(recibo);
    	
        try (OutputStream os = new FileOutputStream("/tmp/recibo.pdf")) {
            PdfRendererBuilder builder = new PdfRendererBuilder();
            builder.useFastMode();
            builder.withHtmlContent( recibo, null);
            
            builder.toStream(os);
            builder.run();
        }
    } 
    
    public MesesFinanceiroGrafico obterInfosGrafico() {
    	List<String> meses = new ArrayList<>();
    	List<Double> receitas = new ArrayList<>();
    	List<Double> despesas = new ArrayList<>();
    	
    	LocalDate dataAtual = LocalDate.now();
    	
        // Gera os objetos de data para os últimos 9 meses, incluindo o mês atual
        for (int i = 0; i < 12; i++) {
            LocalDate primeiroDiaDoMes = dataAtual.withDayOfMonth(1).minusMonths(i);
            LocalDate ultimoDiaDoMes = primeiroDiaDoMes.with(TemporalAdjusters.lastDayOfMonth());
            
            List<Transacao> listT = repo.obterTransacoesPorData(primeiroDiaDoMes, ultimoDiaDoMes);
            
            BigDecimal receita = BigDecimal.ZERO;
            BigDecimal despesa = BigDecimal.ZERO;
            
            for (Transacao t : listT) {
				if (t.getTipoTransacaoEnum().equals(TipoTransacaoEnum.RECEITA)) {
					receita = receita.add(t.getValor());
				} else {
					despesa = despesa.add(t.getValor());
				}
			}
            
            despesa = despesa.multiply(new BigDecimal(-1));
            meses.add(DateUtil.obterMes(primeiroDiaDoMes.getMonthValue()).substring(0, 3) );
            
            receita = receita.setScale(2);
            despesa = despesa.setScale(2);
            
            receitas.add(receita.doubleValue());
            despesas.add(despesa.doubleValue());
            
        }
        
        
        
        MesesFinanceiroGrafico g = new MesesFinanceiroGrafico();
        g.setDespesa(despesas);
        g.setMes(meses);
        g.setReceita(receitas);
        
        return g;
    	
    }
}
