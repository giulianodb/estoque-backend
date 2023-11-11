package br.org.demaosunidas;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;

public class Teste {
    public static void main(String[] args) throws Exception {
    	
    	String comeco = readFile("/home/giuliano/dmu/comeco.txt");
    	
    	String abrirReceita = readFile("/home/giuliano/dmu/abrir-secao.txt");
    	abrirReceita = abrirReceita.replace("${tipoMovimento}", "Receita");
    	String grupoCategorias = "";
    	
    	for (int i = 0; i < 4; i++) {
    		grupoCategorias += readFile("/home//giuliano/dmu/abrir-grupo-categoria.txt");
    		grupoCategorias= grupoCategorias.replace("${nomeGrupoCategoria}", Integer.toString(i));
    		grupoCategorias=grupoCategorias.replace("${id}", Integer.toString(i));
    		String categoria = "";
    		for (int y = 0; y < 2; y++) {
    			categoria += readFile("/home//giuliano/dmu/categoria.txt");
    			categoria = categoria.replace("${descricaoCategoria}", "Bazar");
    			categoria = categoria.replace("${valorTotal}", "50");
    		}
    		grupoCategorias += categoria;
    		
    		grupoCategorias += readFile("/home/giuliano/dmu/fechar-grupo-categoria.txt");
    		grupoCategorias = grupoCategorias.replace("${valorTotal}", "500");
    		
		}
    	
    	String fecharSecao = readFile("/home/giuliano/dmu/fechar-secao.txt");
    	
    	
    	
    	
    	
    	
    	String finalTexte = readFile("/home/giuliano/dmu/final-teste.txt");
    	
    	
    	String documento = comeco + abrirReceita + grupoCategorias + fecharSecao + finalTexte;
    	
    	
        try (OutputStream os = new FileOutputStream("/tmp/out.pdf")) {
            PdfRendererBuilder builder = new PdfRendererBuilder();
            builder.useFastMode();
//            builder.withHtmlContent( readFile("/home/giuliano/teste3.html"), null);
//            builder.withHtmlContent( readFile("/home/giuliano/teste2-2-0.html"), null);
            builder.withHtmlContent( documento, null);
            
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
