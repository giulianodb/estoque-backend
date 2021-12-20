package br.org.demaosunidas;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.org.demaosunidas.domain.Movimentacao;
import br.org.demaosunidas.domain.Produto;
import br.org.demaosunidas.domain.TipoMedidaEnum;
import br.org.demaosunidas.domain.TipoMovimentacaoEnum;
import br.org.demaosunidas.domain.TipoProdutoEnum;
import br.org.demaosunidas.dto.LoteMovimentacaoInsertDTO;
import br.org.demaosunidas.dto.MovimentacaoInsertDTO;
import br.org.demaosunidas.dto.ProdutoInsertDTO;
import br.org.demaosunidas.services.LoteMovimentacaoService;
import br.org.demaosunidas.services.MovimentacaoService;
import br.org.demaosunidas.services.ProdutoService;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class)
public class MovimentacaoRestTests {
	@Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ProdutoService produtoService;
    @Autowired
    private MovimentacaoService movimentacaoService;

    @Autowired
    private LoteMovimentacaoService loteService;
    
    List<Movimentacao> listaMov;
    
    Produto p;
    
    Integer codProduto;
    
//    @Test
    @Order(1)
    void case1() throws Exception {
    	ProdutoInsertDTO produtoInsert = new ProdutoInsertDTO("Teste", TipoProdutoEnum.ALIMENTO, TipoMedidaEnum.QUILOS, "Produto de testes");
    	
    	System.out.println("ZABUMBA");
    	String location = mockMvc.perform(post("/produtos")
    	        .contentType("application/json")
    	        .content(objectMapper.writeValueAsString(produtoInsert)))
    	        //.andExpect(status().isOk()).
    	        .andReturn().getResponse().getHeader("Location");
    	
    	System.out.println("==="+location);
    	codProduto = Integer.decode(location.split("produtos")[1].split("/")[1]);
    	System.out.println(codProduto);
    	
    	
    	List<LoteMovimentacaoInsertDTO> listaLote = new ArrayList<LoteMovimentacaoInsertDTO>();
    	
    	LoteMovimentacaoInsertDTO loteInsert1 = new LoteMovimentacaoInsertDTO();
    	loteInsert1.setTipoMovimentacaoEnum(TipoMovimentacaoEnum.ENTRADA);
    	LocalDateTime data1 = LocalDateTime.of(2021, 8, 1, 0, 0);
    	loteInsert1.setData(data1);
    	
    	MovimentacaoInsertDTO movimentacaoInsert1 = new MovimentacaoInsertDTO();
    	movimentacaoInsert1.setIdProduto(codProduto);
    	movimentacaoInsert1.setQuantidade(5f);
    	movimentacaoInsert1.setValor(5f);
    	List<MovimentacaoInsertDTO> movs1 = new ArrayList<MovimentacaoInsertDTO>();
    	movs1.add(movimentacaoInsert1);
    	loteInsert1.setListMovimentacao(movs1);
    	listaLote.add(loteInsert1);
    	
    	
    	LoteMovimentacaoInsertDTO loteInsert2 = new LoteMovimentacaoInsertDTO();
    	loteInsert2.setTipoMovimentacaoEnum(TipoMovimentacaoEnum.ENTRADA);
    	LocalDateTime data2 = LocalDateTime.of(2021, 8, 3, 0, 0);
    	loteInsert2.setData(data2);
    	
    	MovimentacaoInsertDTO movimentacaoInsert2 = new MovimentacaoInsertDTO();
    	movimentacaoInsert2.setIdProduto(codProduto);
    	movimentacaoInsert2.setQuantidade(3f);
    	movimentacaoInsert2.setValor(3f);
    	List<MovimentacaoInsertDTO> movs2 = new ArrayList<MovimentacaoInsertDTO>();
    	movs2.add(movimentacaoInsert2);
    	loteInsert2.setListMovimentacao(movs2);
    	listaLote.add(loteInsert2);
    	
    	
    	LoteMovimentacaoInsertDTO loteInsert3 = new LoteMovimentacaoInsertDTO();
    	loteInsert3.setTipoMovimentacaoEnum(TipoMovimentacaoEnum.SAIDA);
    	LocalDateTime data3 = LocalDateTime.of(2021, 8, 5, 0, 0);
    	loteInsert3.setData(data3);
    	
    	MovimentacaoInsertDTO movimentacaoInsert3 = new MovimentacaoInsertDTO();
    	movimentacaoInsert3.setIdProduto(codProduto);
    	movimentacaoInsert3.setQuantidade(2f);
    	movimentacaoInsert3.setValor(2f);
    	List<MovimentacaoInsertDTO> movs3 = new ArrayList<MovimentacaoInsertDTO>();
    	movs3.add(movimentacaoInsert3);
    	loteInsert3.setListMovimentacao(movs3);
    	
    	listaLote.add(loteInsert3);
    	
    	LoteMovimentacaoInsertDTO loteInsert4 = new LoteMovimentacaoInsertDTO();
    	loteInsert4.setTipoMovimentacaoEnum(TipoMovimentacaoEnum.SAIDA);
    	LocalDateTime data4 = LocalDateTime.of(2021, 8, 7, 0, 0);
    	loteInsert4.setData(data4);
    	
    	MovimentacaoInsertDTO movimentacaoInsert4 = new MovimentacaoInsertDTO();
    	movimentacaoInsert4.setIdProduto(codProduto);
    	movimentacaoInsert4.setQuantidade(5f);
    	movimentacaoInsert4.setValor(5f);
    	List<MovimentacaoInsertDTO> movs4 = new ArrayList<MovimentacaoInsertDTO>();
    	movs4.add(movimentacaoInsert4);
    	loteInsert4.setListMovimentacao(movs4);

    	listaLote.add(loteInsert4);
    	
    	List<Integer> idsLotes = new ArrayList<Integer>();
    	
    	for (LoteMovimentacaoInsertDTO lote : listaLote) {
        	String location1 = mockMvc.perform(post("/lotes")
        	        .contentType("application/json")
        	        .content(objectMapper.writeValueAsString(lote)))
        	        //.andExpect(status().isOk()).
        	        .andReturn().getResponse().getHeader("Location");
        	
        	System.out.println("==="+location1);
        	idsLotes.add(Integer.decode(location1.split("lotes")[1].split("/")[1]));
		}
    	
    	Map<Integer, Integer> mapLoteEMov = new HashMap<>();
    	
    	for (Integer integer : idsLotes) {
//    		LoteMovimentacao loteMov = loteService.findById(integer);
    		mapLoteEMov.put(integer, movimentacaoService.buscarMovimentacaoPorLote(integer).get(0).getId());
    		System.out.println(movimentacaoService.buscarMovimentacaoPorLote(integer).get(0).getId());
    		
		}
    	
    	this.listaMov = movimentacaoService.buscarMovimentacaoPorProduto(codProduto);
    	
		Assertions.assertEquals( 5,  this.listaMov.get(0).getQuantidade());
		Assertions.assertEquals( 5,  this.listaMov.get(0).getValor());
		Assertions.assertEquals( null,  this.listaMov.get(0).getQuantidadeUltimo());
		Assertions.assertEquals( null,  this.listaMov.get(0).getSaldoUltimo());
		
		
		Assertions.assertEquals( 3,  this.listaMov.get(1).getQuantidade());
		Assertions.assertEquals( 3,  this.listaMov.get(1).getValor());
		Assertions.assertEquals( 5,  this.listaMov.get(1).getQuantidadeUltimo());
		Assertions.assertEquals( 5,  this.listaMov.get(1).getSaldoUltimo());
		
		
		Assertions.assertEquals( 2,  this.listaMov.get(2).getQuantidade());
		Assertions.assertEquals( 2,  this.listaMov.get(2).getValor());
		Assertions.assertEquals( 8,  this.listaMov.get(2).getQuantidadeUltimo());
		Assertions.assertEquals( 8,  this.listaMov.get(2).getSaldoUltimo());
		
		
		Assertions.assertEquals( 5,  this.listaMov.get(3).getQuantidade());
		Assertions.assertEquals( 5,  this.listaMov.get(3).getValor());
		Assertions.assertEquals( 6,  this.listaMov.get(3).getQuantidadeUltimo());
		Assertions.assertEquals( 6,  this.listaMov.get(3).getSaldoUltimo());
		
    	this.p = produtoService.findById(codProduto);
    	
    	Assertions.assertEquals( 1,  p.getQuantidadeEstoque());
    	Assertions.assertEquals( 1,  p.getSaldoEstoque());
    	
    }
    

//    @Test //Alterar mas sem alterar valores
    @Order(2) 
    void alterar() {
    	try {
        	codProduto = 483;
        	Integer idLote1 = 14790;
        	Integer idMov1 = 48782;
        	//   DATA 				01/08						03/08					05/08					07/08
        	//   QTDE				  5							  3						  -2					  -5		
        	//   VALO                 5							  3						  -2					  -5
        	//   SaldoUL              0							  5						   8					   6		
        	//   QtdeUl				  0							  5						   8					   6
        	//   TIPO				  E							  E						   S					   S		
        	//   SALDO                5							  8						   6					   1	  
        	//   QUANTIDE			  5 						  8						   6					   1	
        	//    IDMOV				  48782						  48783                    48784                   48785                     
        	
        	LoteMovimentacaoInsertDTO loteInsert1 = new LoteMovimentacaoInsertDTO();
        	loteInsert1.setTipoMovimentacaoEnum(TipoMovimentacaoEnum.ENTRADA);
        	LocalDateTime data1 = LocalDateTime.of(2021, 8, 1, 0, 0);
        	loteInsert1.setData(data1);
        	
        	MovimentacaoInsertDTO movimentacaoInsert1 = new MovimentacaoInsertDTO();
        	movimentacaoInsert1.setId(idMov1);
        	movimentacaoInsert1.setIdProduto(codProduto);
        	movimentacaoInsert1.setQuantidade(5f);
        	movimentacaoInsert1.setValor(5f);
        	List<MovimentacaoInsertDTO> movs1 = new ArrayList<MovimentacaoInsertDTO>();
        	movs1.add(movimentacaoInsert1);
        	loteInsert1.setListMovimentacao(movs1);
    		
    		String location1 = mockMvc.perform(put("/lotes/"+idLote1)
        	        .contentType("application/json")
        	        .content(objectMapper.writeValueAsString(loteInsert1)))
        	        //.andExpect(status().isOk()).
        	        .andReturn().getResponse().getHeader("Location");
    		
    		
    		this.listaMov = movimentacaoService.buscarMovimentacaoPorProduto(codProduto);
    		
    		Assertions.assertEquals( 5,  this.listaMov.get(0).getQuantidade());
    		Assertions.assertEquals( 5,  this.listaMov.get(0).getValor());
    		Assertions.assertEquals( null,  this.listaMov.get(0).getQuantidadeUltimo());
    		Assertions.assertEquals( null,  this.listaMov.get(0).getSaldoUltimo());
    		
    		
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    }
    
//    @Test //Alterar Terceiro pra segundo
    @Order(3) 
    void alterarMovParaDataAnterior() {
    	try {
        	codProduto = 484;
        	Integer idLote = 14796;
        	Integer idMov1 = 48786;
        	//   DATA 				01/08						03/08					05/08					07/08
        	//   QTDE				  5							  3						  -2					  -5		
        	//   VALO                 5							  3						  -2					  -5
        	//   SaldoUL              0							  5						   8					   6		
        	//   QtdeUl				  0							  5						   8					   6
        	//   TIPO				  E							  E						   S					   S		
        	//   SALDO                5							  8						   6					   1	  
        	//   QUANTIDE			  5 						  8						   6					   1	
        	//    IDMOV				  48786						  48787                    48788                   48789       
        	
           	//   DATA 				01/08			[2/8]		03/08					(05/08)					07/08
        	//   QTDE				  5				[-2]		  3						  (-2)					  -5		
        	//   VALO                 5				[-2]		  3						  (-2)					  -5
        	//   SaldoUL              0				[5]		      3						   (8)					   6		
        	//   QtdeUl				  0				[5]		      3						   (8)					   6
        	//   TIPO				  E				[S]		      E						   (S)					   S		
        	//   SALDO                5				[3]		      6						   (6)					   1	  
        	//   QUANTIDE			  5 			[3]		      6						   (6)					   1	
        	//    IDMOV				  48786			[48788]		  48787                    (48788)                   48789   
        	
        	LoteMovimentacaoInsertDTO loteInsert1 = new LoteMovimentacaoInsertDTO();
        	loteInsert1.setTipoMovimentacaoEnum(TipoMovimentacaoEnum.ENTRADA);//Na alteração não importa
        	LocalDateTime data1 = LocalDateTime.of(2021, 8, 2, 0, 0);
        	loteInsert1.setData(data1);
        	
        	MovimentacaoInsertDTO movimentacaoInsert1 = new MovimentacaoInsertDTO();
        	movimentacaoInsert1.setId(48788);
        	movimentacaoInsert1.setIdProduto(codProduto);
        	movimentacaoInsert1.setQuantidade(2f);
        	movimentacaoInsert1.setValor(-9898f); //Não importa..
        	List<MovimentacaoInsertDTO> movs1 = new ArrayList<MovimentacaoInsertDTO>();
        	movs1.add(movimentacaoInsert1);
        	loteInsert1.setListMovimentacao(movs1);
    		
    		String location1 = mockMvc.perform(put("/lotes/"+idLote)
        	        .contentType("application/json")
        	        .content(objectMapper.writeValueAsString(loteInsert1)))
        	        //.andExpect(status().isOk()).
        	        .andReturn().getResponse().getHeader("Location");
    		
    		
    		this.listaMov = movimentacaoService.buscarMovimentacaoPorProduto(codProduto);
    		
    		Produto produto = produtoService.findById(codProduto);
    		
    		Assertions.assertEquals( 5,  this.listaMov.get(0).getQuantidade());
    		Assertions.assertEquals( 5,  this.listaMov.get(0).getValor());
    		Assertions.assertEquals( null,  this.listaMov.get(0).getQuantidadeUltimo());
    		Assertions.assertEquals( null,  this.listaMov.get(0).getSaldoUltimo());
    		
       		Assertions.assertEquals( 2,  this.listaMov.get(1).getQuantidade());
    		Assertions.assertEquals( 2,  this.listaMov.get(1).getValor());
    		Assertions.assertEquals( 5,  this.listaMov.get(1).getQuantidadeUltimo());
    		Assertions.assertEquals( 5,  this.listaMov.get(1).getSaldoUltimo());
    		
    		
       		Assertions.assertEquals( 3,  this.listaMov.get(2).getQuantidade());
    		Assertions.assertEquals( 3,  this.listaMov.get(2).getValor());
    		Assertions.assertEquals( 3,  this.listaMov.get(2).getQuantidadeUltimo());
    		Assertions.assertEquals( 3,  this.listaMov.get(2).getSaldoUltimo());
    		
    		
       		Assertions.assertEquals( 5,  this.listaMov.get(3).getQuantidade());
    		Assertions.assertEquals( 5,  this.listaMov.get(3).getValor());
    		Assertions.assertEquals( 6,  this.listaMov.get(3).getQuantidadeUltimo());
    		Assertions.assertEquals( 6,  this.listaMov.get(3).getSaldoUltimo());
    		
    		
       		Assertions.assertEquals( 1,  produto.getQuantidadeEstoque());
    		Assertions.assertEquals( 1,  produto.getSaldoEstoque());
    		Assertions.assertEquals( 8,  produto.getQuantidadeHistoricaTotal());
    		Assertions.assertEquals( 8,  produto.getValorHistoricoTotal());
    		
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    }
    
    
//    @Test //Alterar Terceiro pra segundo
    @Order(4) 
    void addMovNoMeio() {
    	try {
        	codProduto = 485;
        	Integer idLote = 14801;
        	Integer idMov1 = 48786;
        	//   DATA 				01/08						03/08					05/08					07/08
        	//   QTDE				  5							  3						  -2					  -5		
        	//   VALO                 5							  3						  -2					  -5
        	//   SaldoUL              0							  5						   8					   6		
        	//   QtdeUl				  0							  5						   8					   6
        	//   TIPO				  E							  E						   S					   S		
        	//   SALDO                5							  8						   6					   1	  
        	//   QUANTIDE			  5 						  8						   6					   1	
        	//    IDMOV				  48790						  48791                    48792                   48793       
        	
           	//   DATA 				01/08			[1/8]			03/08					05/08					07/08
        	//   QTDE				  5				[4]			  3						  -2					  -5		
        	//   VALO                 5				[4]			  3						  -2					  -5
        	//   SaldoUL              0				[5]			  9						   12					   10		
        	//   QtdeUl				  0				[5]			  9						   12					   10
        	//   TIPO				  E				[E]			  E						   S					   S		
        	//   SALDO                5				[9]			  12					   10					   5	  
        	//   QUANTIDE			  5 			[9]			  12					   10					   5	
        	//    IDMOV				  48790			[x]			  48791                    48792                   48793   
        	
        	LoteMovimentacaoInsertDTO loteInsert1 = new LoteMovimentacaoInsertDTO();
        	loteInsert1.setTipoMovimentacaoEnum(TipoMovimentacaoEnum.ENTRADA);//Na alteração não importa
        	LocalDateTime data1 = LocalDateTime.of(2021, 8, 1, 0, 0);
        	loteInsert1.setData(data1);
        	
        	MovimentacaoInsertDTO movimentacaoInsert1 = new MovimentacaoInsertDTO();
//        	movimentacaoInsert1.setId(48788);
        	movimentacaoInsert1.setIdProduto(codProduto);
        	movimentacaoInsert1.setQuantidade(4f);
        	movimentacaoInsert1.setValor(4f); //Não importa..
        	List<MovimentacaoInsertDTO> movs1 = new ArrayList<MovimentacaoInsertDTO>();
        	movs1.add(movimentacaoInsert1);
        	loteInsert1.setListMovimentacao(movs1);
    		
    		String location1 = mockMvc.perform(post("/lotes")
        	        .contentType("application/json")
        	        .content(objectMapper.writeValueAsString(loteInsert1)))
        	        //.andExpect(status().isOk()).
        	        .andReturn().getResponse().getHeader("Location");
    		
    		
    		this.listaMov = movimentacaoService.buscarMovimentacaoPorProduto(codProduto);
    		
    		Produto produto = produtoService.findById(codProduto);
    		
    		Assertions.assertEquals( 5,  this.listaMov.get(0).getQuantidade());
    		Assertions.assertEquals( 5,  this.listaMov.get(0).getValor());
    		Assertions.assertEquals( null,  this.listaMov.get(0).getQuantidadeUltimo());
    		Assertions.assertEquals( null,  this.listaMov.get(0).getSaldoUltimo());
    		
       		Assertions.assertEquals( 4,  this.listaMov.get(1).getQuantidade());
    		Assertions.assertEquals( 4,  this.listaMov.get(1).getValor());
    		Assertions.assertEquals( 5,  this.listaMov.get(1).getQuantidadeUltimo());
    		Assertions.assertEquals( 5,  this.listaMov.get(1).getSaldoUltimo());
    		
    		
       		Assertions.assertEquals( 3,  this.listaMov.get(2).getQuantidade());
    		Assertions.assertEquals( 3,  this.listaMov.get(2).getValor());
    		Assertions.assertEquals( 9,  this.listaMov.get(2).getQuantidadeUltimo());
    		Assertions.assertEquals( 9,  this.listaMov.get(2).getSaldoUltimo());
    		
    		
       		Assertions.assertEquals( 2,  this.listaMov.get(3).getQuantidade());
    		Assertions.assertEquals( 2,  this.listaMov.get(3).getValor());
    		Assertions.assertEquals( 12,  this.listaMov.get(3).getQuantidadeUltimo());
    		Assertions.assertEquals( 12,  this.listaMov.get(3).getSaldoUltimo());
    		
      		Assertions.assertEquals( 5,  this.listaMov.get(4).getQuantidade());
    		Assertions.assertEquals( 5,  this.listaMov.get(4).getValor());
    		Assertions.assertEquals( 10,  this.listaMov.get(4).getQuantidadeUltimo());
    		Assertions.assertEquals( 10,  this.listaMov.get(4).getSaldoUltimo());
    		
       		Assertions.assertEquals( 5,  produto.getQuantidadeEstoque());
    		Assertions.assertEquals( 5,  produto.getSaldoEstoque());
    		Assertions.assertEquals( 12,  produto.getQuantidadeHistoricaTotal());
    		Assertions.assertEquals( 12,  produto.getValorHistoricoTotal());
    		
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    }
    
    
//    @Test //Alterar Terceiro pra segundo
    @Order(5) 
    void alterarAntesDaPrimeira() {
    	try {
        	codProduto = 486;
        	Integer idLote = 14803;
        	Integer idMov1 = 48786;
        	//   DATA 				01/08						03/08					05/08					07/08
        	//   QTDE				  5							  3						  -2					  -5		
        	//   VALO                 5							  3						  -2					  -5
        	//   SaldoUL              0							  5						   8					   6		
        	//   QtdeUl				  0							  5						   8					   6
        	//   TIPO				  E							  E						   S					   S		
        	//   SALDO                5							  8						   6					   1	  
        	//   QUANTIDE			  5 						  8						   6					   1	
        	//    IDMOV				  48794						  48795                    48796                   48797       
        	
           	//   DATA 		[31/7]	01/08					(03/08)					05/08					07/08
        	//   QTDE		[3]		  5						  (3)						  -2					  -5		
        	//   VALO       [3]       5						  (3)						  -2					  -5
        	//   SaldoUL    [0]       3					      (3)						   8					   6		
        	//   QtdeUl		[0]		  3					      (3)						   8					   6
        	//   TIPO		[E]		  E					      (E)						   S					   S		
        	//   SALDO      [3]       8					      (6)						   6					   1	  
        	//   QUANTIDE	[3]		  8 				      (6)						   6					   1	
        	//    IDMOV		[48795]	  48794					  (48795)                    48796                   48797   
        	
        	LoteMovimentacaoInsertDTO loteInsert1 = new LoteMovimentacaoInsertDTO();
        	loteInsert1.setTipoMovimentacaoEnum(TipoMovimentacaoEnum.ENTRADA);//Na alteração não importa
        	LocalDateTime data1 = LocalDateTime.of(2021, 7, 31, 0, 0);
        	loteInsert1.setData(data1);
        	
        	MovimentacaoInsertDTO movimentacaoInsert1 = new MovimentacaoInsertDTO();
        	movimentacaoInsert1.setId(48795);
        	movimentacaoInsert1.setIdProduto(codProduto);
        	movimentacaoInsert1.setQuantidade(3f);
        	movimentacaoInsert1.setValor(3f); 
        	List<MovimentacaoInsertDTO> movs1 = new ArrayList<MovimentacaoInsertDTO>();
        	movs1.add(movimentacaoInsert1);
        	loteInsert1.setListMovimentacao(movs1);
    		
    		String location1 = mockMvc.perform(put("/lotes/"+idLote)
        	        .contentType("application/json")
        	        .content(objectMapper.writeValueAsString(loteInsert1)))
        	        //.andExpect(status().isOk()).
        	        .andReturn().getResponse().getHeader("Location");
    		
    		
    		this.listaMov = movimentacaoService.buscarMovimentacaoPorProduto(codProduto);
    		
    		Produto produto = produtoService.findById(codProduto);
    		
    		Assertions.assertEquals( 3,  this.listaMov.get(0).getQuantidade());
    		Assertions.assertEquals( 3,  this.listaMov.get(0).getValor());
    		Assertions.assertEquals( null,  this.listaMov.get(0).getQuantidadeUltimo());
    		Assertions.assertEquals( null,  this.listaMov.get(0).getSaldoUltimo());
    		
       		Assertions.assertEquals( 5,  this.listaMov.get(1).getQuantidade());
    		Assertions.assertEquals( 5,  this.listaMov.get(1).getValor());
    		Assertions.assertEquals( 3,  this.listaMov.get(1).getQuantidadeUltimo());
    		Assertions.assertEquals( 3,  this.listaMov.get(1).getSaldoUltimo());
    		
    		
       		Assertions.assertEquals( 2,  this.listaMov.get(2).getQuantidade());
    		Assertions.assertEquals( 2,  this.listaMov.get(2).getValor());
    		Assertions.assertEquals( 8,  this.listaMov.get(2).getQuantidadeUltimo());
    		Assertions.assertEquals( 8,  this.listaMov.get(2).getSaldoUltimo());
    		
    		
       		Assertions.assertEquals( 5,  this.listaMov.get(3).getQuantidade());
    		Assertions.assertEquals( 5,  this.listaMov.get(3).getValor());
    		Assertions.assertEquals( 6,  this.listaMov.get(3).getQuantidadeUltimo());
    		Assertions.assertEquals( 6,  this.listaMov.get(3).getSaldoUltimo());
    		
    		
       		Assertions.assertEquals( 1,  produto.getQuantidadeEstoque());
    		Assertions.assertEquals( 1,  produto.getSaldoEstoque());
    		Assertions.assertEquals( 8,  produto.getQuantidadeHistoricaTotal());
    		Assertions.assertEquals( 8,  produto.getValorHistoricoTotal());
    		
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    }
    
    
//    @Test //Alterar primeirp para terceiro
    @Order(6) 
    void alterarParaDireita() {
    	try {
        	codProduto = 487;
        	Integer idLote = 14806;
        	Integer idMov1 = 48786;
        	//   DATA 				01/08						03/08					05/08					07/08
        	//   QTDE				  5							  3						  -2					  -5		
        	//   VALO                 5							  3						  -2					  -5
        	//   SaldoUL              0							  5						   8					   6		
        	//   QtdeUl				  0							  5						   8					   6
        	//   TIPO				  E							  E						   S					   S		
        	//   SALDO                5							  8						   6					   1	  
        	//   QUANTIDE			  5 						  8						   6					   1	
        	//    IDMOV				  48798						  48799                    48800                   48801       
        	
        	//   DATA 				(01/08)						03/08					05/08		  [6/8]	      07/08
        	//   QTDE				  (5)						  3						  -2			[5]		  -5		
        	//   VALO                 (5)						  3						  -2			[5]		  -5
        	//   SaldoUL              (0)						  0						   3			[1]		   6		
        	//   QtdeUl				  (0)						  0						   3			[1]		   6
        	//   TIPO				  (E)						  E						   S			[E]		   S		
        	//   SALDO                (5)						  3						   1			[6]		   1	  
        	//   QUANTIDE			  (5) 						  3						   1			[6]		   1	
        	//    IDMOV				  (48798)					  48799                    48800        48798     48801    
        	
        	LoteMovimentacaoInsertDTO loteInsert1 = new LoteMovimentacaoInsertDTO();
        	loteInsert1.setTipoMovimentacaoEnum(TipoMovimentacaoEnum.ENTRADA);//Na alteração não importa
        	LocalDateTime data1 = LocalDateTime.of(2021, 8, 6, 0, 0);
        	loteInsert1.setData(data1);
        	
        	MovimentacaoInsertDTO movimentacaoInsert1 = new MovimentacaoInsertDTO();
        	movimentacaoInsert1.setId(48798);
        	movimentacaoInsert1.setIdProduto(codProduto);
        	movimentacaoInsert1.setQuantidade(5f);
        	movimentacaoInsert1.setValor(5f); 
        	List<MovimentacaoInsertDTO> movs1 = new ArrayList<MovimentacaoInsertDTO>();
        	movs1.add(movimentacaoInsert1);
        	loteInsert1.setListMovimentacao(movs1);
    		
    		String location1 = mockMvc.perform(put("/lotes/"+idLote)
        	        .contentType("application/json")
        	        .content(objectMapper.writeValueAsString(loteInsert1)))
        	        //.andExpect(status().isOk()).
        	        .andReturn().getResponse().getHeader("Location");
    		
    		
    		this.listaMov = movimentacaoService.buscarMovimentacaoPorProduto(codProduto);
    		
    		Produto produto = produtoService.findById(codProduto);
    		
    		Assertions.assertEquals( 3,  this.listaMov.get(0).getQuantidade());
    		Assertions.assertEquals( 3,  this.listaMov.get(0).getValor());
    		Assertions.assertEquals( 0,  this.listaMov.get(0).getQuantidadeUltimo());
    		Assertions.assertEquals( 0,  this.listaMov.get(0).getSaldoUltimo());
    		
       		Assertions.assertEquals( 2,  this.listaMov.get(1).getQuantidade());
    		Assertions.assertEquals( 2,  this.listaMov.get(1).getValor());
    		Assertions.assertEquals( 3,  this.listaMov.get(1).getQuantidadeUltimo());
    		Assertions.assertEquals( 3,  this.listaMov.get(1).getSaldoUltimo());
    		
    		
       		Assertions.assertEquals( 5,  this.listaMov.get(2).getQuantidade());
    		Assertions.assertEquals( 5,  this.listaMov.get(2).getValor());
    		Assertions.assertEquals( 1,  this.listaMov.get(2).getQuantidadeUltimo());
    		Assertions.assertEquals( 1,  this.listaMov.get(2).getSaldoUltimo());
    		
    		
       		Assertions.assertEquals( 5,  this.listaMov.get(3).getQuantidade());
    		Assertions.assertEquals( 5,  this.listaMov.get(3).getValor());
    		Assertions.assertEquals( 6,  this.listaMov.get(3).getQuantidadeUltimo());
    		Assertions.assertEquals( 6,  this.listaMov.get(3).getSaldoUltimo());
    		
    		
       		Assertions.assertEquals( 1,  produto.getQuantidadeEstoque());
    		Assertions.assertEquals( 1,  produto.getSaldoEstoque());
    		Assertions.assertEquals( 8,  produto.getQuantidadeHistoricaTotal());
    		Assertions.assertEquals( 8,  produto.getValorHistoricoTotal());
    		
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    }
    
    
    //@Test
    @Order(6) 
    void alterarParaEsquerda() {
    	try {
        	codProduto = 488;
        	Integer idLote = 14813;
        	//   DATA 				01/08						03/08					05/08					07/08
        	//   QTDE				  5							  3						  -2					  -5		
        	//   VALO                 5							  3						  -2					  -5
        	//   SaldoUL              0							  5						   8					   6		
        	//   QtdeUl				  0							  5						   8					   6
        	//   TIPO				  E							  E						   S					   S		
        	//   SALDO                5							  8						   6					   1	  
        	//   QUANTIDE			  5 						  8						   6					   1	
        	//    IDMOV				  48802						  48803                    48804                   48805       
        	
        	//   DATA 				01/08		[2/08]			03/08					05/08					(07/08)
        	//   QTDE				  5			  [-5]	    	  3						  -2					  (-5)		
        	//   VALO                 5			  [-5]			  3						  -2					  (-5)
        	//   SaldoUL              0			   [5]			  0						   3					   (6)		
        	//   QtdeUl				  0			   [5]			  0						   3					   (6)
        	//   TIPO				  E			   [S]			  E						   S					   (S)		
        	//   SALDO                5			   [0] 			  3						   1					   (1)	  
        	//   QUANTIDE			  5 		   [0]			  3						   1					   (1)	
        	//    IDMOV				  48802		  [48805]	    48803                    48804                   (48805)   
        	
        	LoteMovimentacaoInsertDTO loteInsert1 = new LoteMovimentacaoInsertDTO();
        	loteInsert1.setTipoMovimentacaoEnum(TipoMovimentacaoEnum.ENTRADA);//Na alteração não importa
        	LocalDateTime data1 = LocalDateTime.of(2021, 8, 2, 0, 0);
        	loteInsert1.setData(data1);
        	
        	MovimentacaoInsertDTO movimentacaoInsert1 = new MovimentacaoInsertDTO();
        	movimentacaoInsert1.setId(48805);
        	movimentacaoInsert1.setIdProduto(codProduto);
        	movimentacaoInsert1.setQuantidade(5f);
        	movimentacaoInsert1.setValor(5f); 
        	List<MovimentacaoInsertDTO> movs1 = new ArrayList<MovimentacaoInsertDTO>();
        	movs1.add(movimentacaoInsert1);
        	loteInsert1.setListMovimentacao(movs1);
    		
    		String location1 = mockMvc.perform(put("/lotes/"+idLote)
        	        .contentType("application/json")
        	        .content(objectMapper.writeValueAsString(loteInsert1)))
        	        //.andExpect(status().isOk()).
        	        .andReturn().getResponse().getHeader("Location");
    		
    		
    		this.listaMov = movimentacaoService.buscarMovimentacaoPorProduto(codProduto);
    		
    		Produto produto = produtoService.findById(codProduto);
    		
    		Assertions.assertEquals( 5,  this.listaMov.get(0).getQuantidade());
    		Assertions.assertEquals( 5,  this.listaMov.get(0).getValor());
    		Assertions.assertEquals( null,  this.listaMov.get(0).getQuantidadeUltimo());
    		Assertions.assertEquals( null,  this.listaMov.get(0).getSaldoUltimo());
    		
       		Assertions.assertEquals( 5,  this.listaMov.get(1).getQuantidade());
    		Assertions.assertEquals( 5,  this.listaMov.get(1).getValor());
    		Assertions.assertEquals( 5,  this.listaMov.get(1).getQuantidadeUltimo());
    		Assertions.assertEquals( 5,  this.listaMov.get(1).getSaldoUltimo());
    		
    		
       		Assertions.assertEquals( 3,  this.listaMov.get(2).getQuantidade());
    		Assertions.assertEquals( 3,  this.listaMov.get(2).getValor());
    		Assertions.assertEquals( 0,  this.listaMov.get(2).getQuantidadeUltimo());
    		Assertions.assertEquals( 0,  this.listaMov.get(2).getSaldoUltimo());
    		
    		
       		Assertions.assertEquals( 2,  this.listaMov.get(3).getQuantidade());
    		Assertions.assertEquals( 2,  this.listaMov.get(3).getValor());
    		Assertions.assertEquals( 3,  this.listaMov.get(3).getQuantidadeUltimo());
    		Assertions.assertEquals( 3,  this.listaMov.get(3).getSaldoUltimo());
    		
    		
       		Assertions.assertEquals( 1,  produto.getQuantidadeEstoque());
    		Assertions.assertEquals( 1,  produto.getSaldoEstoque());
    		Assertions.assertEquals( 8,  produto.getQuantidadeHistoricaTotal());
    		Assertions.assertEquals( 8,  produto.getValorHistoricoTotal());
    		
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    }
    
  
   //@Test //
  @Order(7) 
  void addMovNoMeioSaida() {
  	try {
      	codProduto = 489;
      	Integer idLote = 14801;
      	Integer idMov1 = 48786;
      	//   DATA 				01/08						03/08					05/08					07/08
      	//   QTDE				  5							  3						  -2					  -5		
      	//   VALO                 5							  3						  -2					  -5
      	//   SaldoUL              0							  5						   8					   6		
      	//   QtdeUl				  0							  5						   8					   6
      	//   TIPO				  E							  E						   S					   S		
      	//   SALDO                5							  8						   6					   1	  
      	//   QUANTIDE			  5 						  8						   6					   1	
      	//    IDMOV				  48806						  48807                    48808                   48809       
      	
     	//   DATA 				01/08		   [2/8]		03/08					05/08					07/08
      	//   QTDE				  5				[1]		     3						  -2					  -5		
      	//   VALO                 5				[x]		     3						  -2					  -5
      	//   SaldoUL              0				[5]		     4						   7					   5		
      	//   QtdeUl				  0				[5]		     4						   7					   5
      	//   TIPO				  E				[S]		     E						   S					   S		
      	//   SALDO                5				[4]		     7						   5					   0	  
      	//   QUANTIDE			  5 			[4]		     7						   5					   0	
      	//   IDMOV				  48806			[x]		    48807                    48808                   48809     
      	
      	LoteMovimentacaoInsertDTO loteInsert1 = new LoteMovimentacaoInsertDTO();
      	loteInsert1.setTipoMovimentacaoEnum(TipoMovimentacaoEnum.SAIDA);//Na alteração não importa
      	LocalDateTime data1 = LocalDateTime.of(2021, 8, 2, 0, 0);
      	loteInsert1.setData(data1);
      	
      	MovimentacaoInsertDTO movimentacaoInsert1 = new MovimentacaoInsertDTO();
//      	movimentacaoInsert1.setId(48788);
      	movimentacaoInsert1.setIdProduto(codProduto);
      	movimentacaoInsert1.setQuantidade(1f);
      	movimentacaoInsert1.setValor(888f); //Não importa..
      	List<MovimentacaoInsertDTO> movs1 = new ArrayList<MovimentacaoInsertDTO>();
      	movs1.add(movimentacaoInsert1);
      	loteInsert1.setListMovimentacao(movs1);
  		
  		String location1 = mockMvc.perform(post("/lotes")
      	        .contentType("application/json")
      	        .content(objectMapper.writeValueAsString(loteInsert1)))
      	        //.andExpect(status().isOk()).
      	        .andReturn().getResponse().getHeader("Location");
  		
  		
  		this.listaMov = movimentacaoService.buscarMovimentacaoPorProduto(codProduto);
  		
  		Produto produto = produtoService.findById(codProduto);
  		
  		Assertions.assertEquals( 5,  this.listaMov.get(0).getQuantidade());
  		Assertions.assertEquals( 5,  this.listaMov.get(0).getValor());
  		Assertions.assertEquals( null,  this.listaMov.get(0).getQuantidadeUltimo());
  		Assertions.assertEquals( null,  this.listaMov.get(0).getSaldoUltimo());
  		
     	Assertions.assertEquals( 1,  this.listaMov.get(1).getQuantidade());
  		Assertions.assertEquals( 1,  this.listaMov.get(1).getValor());
  		Assertions.assertEquals( 5,  this.listaMov.get(1).getQuantidadeUltimo());
  		Assertions.assertEquals( 5,  this.listaMov.get(1).getSaldoUltimo());
  		
  		
     	Assertions.assertEquals( 3,  this.listaMov.get(2).getQuantidade());
  		Assertions.assertEquals( 3,  this.listaMov.get(2).getValor());
  		Assertions.assertEquals( 4,  this.listaMov.get(2).getQuantidadeUltimo());
  		Assertions.assertEquals( 4,  this.listaMov.get(2).getSaldoUltimo());
  		
  		
     	Assertions.assertEquals( 2,  this.listaMov.get(3).getQuantidade());
  		Assertions.assertEquals( 2,  this.listaMov.get(3).getValor());
  		Assertions.assertEquals( 7,  this.listaMov.get(3).getQuantidadeUltimo());
  		Assertions.assertEquals( 7,  this.listaMov.get(3).getSaldoUltimo());
  		
    	Assertions.assertEquals( 5,  this.listaMov.get(4).getQuantidade());
  		Assertions.assertEquals( 5,  this.listaMov.get(4).getValor());
  		Assertions.assertEquals( 5,  this.listaMov.get(4).getQuantidadeUltimo());
  		Assertions.assertEquals( 5,  this.listaMov.get(4).getSaldoUltimo());
  		
  		
     	Assertions.assertEquals( 0,  produto.getQuantidadeEstoque());
  		Assertions.assertEquals( 0,  produto.getSaldoEstoque());
  		Assertions.assertEquals( 8,  produto.getQuantidadeHistoricaTotal());
  		Assertions.assertEquals( 8,  produto.getValorHistoricoTotal());
  		
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
  }
  
  
@Test //Alterar primeirp para terceiro
@Order(8) 
void alterarParaDireitaAlterandoValorEtFicandoNegativo() {
	try {
    	codProduto = 490;
    	Integer idLote = 14818;
    	//   DATA 				01/08						03/08					05/08					07/08
    	//   QTDE				  5							  3						  -2					  -5		
    	//   VALO                 5							  3						  -2					  -5
    	//   SaldoUL              0							  5						   8					   6		
    	//   QtdeUl				  0							  5						   8					   6
    	//   TIPO				  E							  E						   S					   S		
    	//   SALDO                5							  8						   6					   1	  
    	//   QUANTIDE			  5 						  8						   6					   1	
    	//    IDMOV				  48810						  48811                    48812                   48813       
    	
    	//   DATA 				(01/08)						03/08					05/08		  [6/8]	      07/08
    	//   QTDE				  (5)						  3						  -2			[3]		  -5		
    	//   VALO                 (5)						  3						  -2			[3]		  -5
    	//   SaldoUL              (0)						  0						   3			[1]		   6		
    	//   QtdeUl				  (0)						  0						   3			[1]		   6
    	//   TIPO				  (E)						  E						   S			[E]		   S		
    	//   SALDO                (5)						  3						   1			[6]		   1	  
    	//   QUANTIDE			  (5) 						  3						   1			[6]		   1	
    	//    IDMOV				  (48810)					  48811                    48812        48810     48813  
    	
    	//   DATA 				(01/08)						03/08					05/08		  [6/8]	      07/08
    	//   QTDE				  (5)						  3						  -2			[6]		  -5		
    	//   VALO                 (5)						  3						  -2			[12]	   9.25
    	//   SaldoUL              (0)						  0						   3			[1]		   13		
    	//   QtdeUl				  (0)						  0						   3			[1]		   7
    	//   TIPO				  (E)						  E						   S			[E]		   S		
    	//   SALDO                (5)						  3						   1			[6]		   2	  
    	//   QUANTIDE			  (5) 						  3						   1			[6]		   3.7
    	//    IDMOV				  (48810)					  48811                    48812        48810     48813    
    	
    	LoteMovimentacaoInsertDTO loteInsert1 = new LoteMovimentacaoInsertDTO();
    	loteInsert1.setTipoMovimentacaoEnum(TipoMovimentacaoEnum.ENTRADA);//Na alteração não importa
    	LocalDateTime data1 = LocalDateTime.of(2021, 8, 6, 0, 0);
    	loteInsert1.setData(data1);
    	
    	MovimentacaoInsertDTO movimentacaoInsert1 = new MovimentacaoInsertDTO();
    	movimentacaoInsert1.setId(48810);
    	movimentacaoInsert1.setIdProduto(codProduto);
    	movimentacaoInsert1.setQuantidade(3f);
    	movimentacaoInsert1.setValor(3f); 
    	List<MovimentacaoInsertDTO> movs1 = new ArrayList<MovimentacaoInsertDTO>();
    	movs1.add(movimentacaoInsert1);
    	loteInsert1.setListMovimentacao(movs1);
		
		mockMvc.perform(put("/lotes/"+idLote)
    	        .contentType("application/json")
    	        .content(objectMapper.writeValueAsString(loteInsert1)))
    	        .andExpect(status().is4xxClientError());
		
		
		this.listaMov = movimentacaoService.buscarMovimentacaoPorProduto(codProduto);
		
		Produto produto = produtoService.findById(codProduto);
		
		Assertions.assertEquals( 5,  this.listaMov.get(0).getQuantidade());
		Assertions.assertEquals( 5,  this.listaMov.get(0).getValor());
		Assertions.assertEquals( null,  this.listaMov.get(0).getQuantidadeUltimo());
		Assertions.assertEquals( null,  this.listaMov.get(0).getSaldoUltimo());
		
   		Assertions.assertEquals( 3,  this.listaMov.get(1).getQuantidade());
		Assertions.assertEquals( 3,  this.listaMov.get(1).getValor());
		Assertions.assertEquals( 5,  this.listaMov.get(1).getQuantidadeUltimo());
		Assertions.assertEquals( 5,  this.listaMov.get(1).getSaldoUltimo());
		
		
   		Assertions.assertEquals( 2,  this.listaMov.get(2).getQuantidade());
		Assertions.assertEquals( 2,  this.listaMov.get(2).getValor());
		Assertions.assertEquals( 8,  this.listaMov.get(2).getQuantidadeUltimo());
		Assertions.assertEquals( 8,  this.listaMov.get(2).getSaldoUltimo());
		
		
   		Assertions.assertEquals( 5,  this.listaMov.get(3).getQuantidade());
		Assertions.assertEquals( 5,  this.listaMov.get(3).getValor());
		Assertions.assertEquals( 6,  this.listaMov.get(3).getQuantidadeUltimo());
		Assertions.assertEquals( 6,  this.listaMov.get(3).getSaldoUltimo());
		
		
   		Assertions.assertEquals( 1,  produto.getQuantidadeEstoque());
		Assertions.assertEquals( 1,  produto.getSaldoEstoque());
		Assertions.assertEquals( 8,  produto.getQuantidadeHistoricaTotal());
		Assertions.assertEquals( 8,  produto.getValorHistoricoTotal());
		
		
		
		
    	LoteMovimentacaoInsertDTO loteInsert2 = new LoteMovimentacaoInsertDTO();
    	loteInsert2.setTipoMovimentacaoEnum(TipoMovimentacaoEnum.ENTRADA);//Na alteração não importa
    	LocalDateTime data2 = LocalDateTime.of(2021, 8, 6, 0, 0);
    	loteInsert2.setData(data2);
    	
    	MovimentacaoInsertDTO movimentacaoInsert2 = new MovimentacaoInsertDTO();
    	movimentacaoInsert2.setId(48810);
    	movimentacaoInsert2.setIdProduto(codProduto);
    	movimentacaoInsert2.setQuantidade(6f);
    	movimentacaoInsert2.setValor(12f); 
    	List<MovimentacaoInsertDTO> movs2 = new ArrayList<MovimentacaoInsertDTO>();
    	movs2.add(movimentacaoInsert2);
    	loteInsert2.setListMovimentacao(movs2);
		
		mockMvc.perform(put("/lotes/"+idLote)
    	        .contentType("application/json")
    	        .content(objectMapper.writeValueAsString(loteInsert2)))
    	        .andExpect(status().is2xxSuccessful());
		
		
		this.listaMov = movimentacaoService.buscarMovimentacaoPorProduto(codProduto);
		
//		produto = produtoService.findById(codProduto);
//		
//		Assertions.assertEquals( 5,  this.listaMov.get(0).getQuantidade());
//		Assertions.assertEquals( 5,  this.listaMov.get(0).getValor());
//		Assertions.assertEquals( null,  this.listaMov.get(0).getQuantidadeUltimo());
//		Assertions.assertEquals( null,  this.listaMov.get(0).getSaldoUltimo());
//		
//   		Assertions.assertEquals( 3,  this.listaMov.get(1).getQuantidade());
//		Assertions.assertEquals( 3,  this.listaMov.get(1).getValor());
//		Assertions.assertEquals( 5,  this.listaMov.get(1).getQuantidadeUltimo());
//		Assertions.assertEquals( 5,  this.listaMov.get(1).getSaldoUltimo());
//		
//		
//   		Assertions.assertEquals( 2,  this.listaMov.get(2).getQuantidade());
//		Assertions.assertEquals( 2,  this.listaMov.get(2).getValor());
//		Assertions.assertEquals( 8,  this.listaMov.get(2).getQuantidadeUltimo());
//		Assertions.assertEquals( 8,  this.listaMov.get(2).getSaldoUltimo());
//		
//		
//   		Assertions.assertEquals( 5,  this.listaMov.get(3).getQuantidade());
//		Assertions.assertEquals( 5,  this.listaMov.get(3).getValor());
//		Assertions.assertEquals( 6,  this.listaMov.get(3).getQuantidadeUltimo());
//		Assertions.assertEquals( 6,  this.listaMov.get(3).getSaldoUltimo());
//		
//		
//   		Assertions.assertEquals( 1,  produto.getQuantidadeEstoque());
//		Assertions.assertEquals( 1,  produto.getSaldoEstoque());
//		Assertions.assertEquals( 8,  produto.getQuantidadeHistoricaTotal());
//		Assertions.assertEquals( 8,  produto.getValorHistoricoTotal());
//		
		
		
		
		
		
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
}
    
}
