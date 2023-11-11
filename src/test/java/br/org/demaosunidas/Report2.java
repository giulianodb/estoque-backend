package br.org.demaosunidas;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.jsoup.Jsoup;
import org.jsoup.helper.W3CDom;
import org.jsoup.nodes.Document;

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;

public class Report2 {
	public static void main(String args[]) {
		

		
		try {
			File inputHTML = new File("/home/giuliano/teste2-2-0.html");
			Document document = Jsoup.parse(inputHTML, "UTF-8");
			document.outputSettings().syntax(Document.OutputSettings.Syntax.xml);
			
			OutputStream outputStream = new FileOutputStream("/tmp/teste22.pdf");
			    PdfRendererBuilder builder = new PdfRendererBuilder();
//		    builder.withUri(inputHTML);
			    builder.toStream(outputStream);
			    builder.withW3cDocument(new W3CDom().fromJsoup(document), "/");
			    builder.run();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
