package br.org.demaosunidas;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;

public class Teste2 {
    public static void main(String[] args) throws Exception {
    	
    	String comeco = readFile("/home/giuliano/dmu/raportv2/comeco.txt");
    	
    	String abrirTipoReceita = readFile("/home/giuliano/dmu/raportv2/abrir-tipo.txt");
    	abrirTipoReceita = abrirTipoReceita.replace("${tipoMovimento}", "Receita").replace("${totalTipo}", "R$ 5.000,00");
    	
    	comeco += abrirTipoReceita;
    	
    	String grupoCategoriaAll = "";
    	
    	//grupoCategoria
    	for (int i = 0; i < 4; i++) {
    		String abrirGrupoCategoria = readFile("/home/giuliano/dmu/raportv2/abrir-grupo-categoria.txt");
    		abrirGrupoCategoria = abrirGrupoCategoria.replace("${id}", Integer.toString(i))
    				.replace("${nomeGrupoCategoria}", "nomeGrupo"+i)
    				.replace("${valorTotalGrupoCategoria}", "100"+i);
    		
    		//categoria
    		
    		String allCategorias = "";
    		
    		for (int y = 0; y < 4; y++) {
    			String abrirCategoria = readFile("/home/giuliano/dmu/raportv2/abrir-categoria.txt");
    			abrirCategoria = abrirCategoria.replace("${nomeCategoria}", "Categoria"+i+"-"+y);
    			
    			String abrirMovimento = readFile("/home/giuliano/dmu/raportv2/abrir-tabela-movimentos.txt");
    			
    			
    			for (int z = 0; z < 4; z++) {
    				String movimento = readFile("/home/giuliano/dmu/raportv2/movimento.txt");
    				movimento = movimento.replace("${data}", "data "+i+""+y+""+z)
    						.replace("${descricao}", "descricao "+i+""+y+""+z)
    						.replace("${centroCuso}", "centroCuso "+i+""+y+""+z)
    						.replace("${conta}", "conta "+i+""+y+""+z)
    						.replace("${pessoa}", "pessoa "+i+""+y+""+z)
    						.replace("${valor}", "valor "+i+""+y+""+z);
    				
    				abrirMovimento += movimento;
    			}
    			
    			
    			String fecharMovimento = readFile("/home/giuliano/dmu/raportv2/fechar-tabela-movimentos.txt");
    			
    			abrirMovimento += fecharMovimento;
    			
    			abrirCategoria += abrirMovimento;
    			allCategorias += abrirCategoria;
    		}
    		
    		abrirGrupoCategoria += allCategorias;
    		
    		abrirGrupoCategoria += readFile("/home/giuliano/dmu/raportv2/fechar-grupo-categoria.txt");
    		
    		grupoCategoriaAll += abrirGrupoCategoria;
    	}
    	
    	comeco += grupoCategoriaAll;
    	String fecharTipo = readFile("/home/giuliano/dmu/raportv2/fechar-tipo.txt.txt");
		comeco += fecharTipo;
    	
    	String fim = readFile("/home/giuliano/dmu/raportv2/fim.txt");
    	comeco += fim;
    	
    	System.out.println(comeco);
    	
        try (OutputStream os = new FileOutputStream("/tmp/out.pdf")) {
            PdfRendererBuilder builder = new PdfRendererBuilder();
            builder.useFastMode();
//            builder.withHtmlContent( readFile("/home/giuliano/teste3.html"), null);
//            builder.withHtmlContent( readFile("/home/giuliano/teste2-2-0.html"), null);
            builder.withHtmlContent( comeco, null);
            
            builder.toStream(os);
            builder.run();
        }
    }
    
    public static String readFile(String file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader (file));
        String         line = null;
        StringBuilder  stringBuilder = new StringBuilder();
        String         ls = System.getProperty("line.separator");

        try {
            while((line = reader.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append(ls);
            }

            return stringBuilder.toString();
        } finally {
            reader.close();
        }
    }
}
