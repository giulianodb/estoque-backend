package br.org.demaosunidas;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.jsoup.Jsoup;

import org.jsoup.nodes.Document;
import org.xhtmlrenderer.layout.SharedContext;
import org.xhtmlrenderer.pdf.ITextRenderer;

public class TesteRepport {
	public static void main(String args[]) {
		try {
//			File inputHTML = new File("/home/giuliano/Área de trabalho/teste2-1-1.html");
			File inputHTML = new File("/home/giuliano/teste2-2-0.html");
			
			Document document = Jsoup.parse(inputHTML, "UTF-8");
			document.outputSettings().syntax(Document.OutputSettings.Syntax.xml);
			
			OutputStream outputStream = new FileOutputStream("/tmp/teste2.pdf");
			
			 ITextRenderer renderer = new ITextRenderer();
			    SharedContext sharedContext = renderer.getSharedContext();
			    sharedContext.setPrint(true);
			    sharedContext.setInteractive(false);
			    renderer.setDocumentFromString(document.html());
			    renderer.layout();
			    renderer.createPDF(outputStream);
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
