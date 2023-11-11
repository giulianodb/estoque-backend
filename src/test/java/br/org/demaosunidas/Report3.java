package br.org.demaosunidas;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;

public class Report3 {
    public static void main(String[] args) throws Exception { 
        try (OutputStream os = new FileOutputStream("/tmp/out.pdf")) {
            PdfRendererBuilder builder = new PdfRendererBuilder();
            builder.useFastMode();
//            builder.withHtmlContent( readFile("/home/giuliano/teste3.html"), null);
            builder.withHtmlContent( readFile("/home/giuliano/teste3-0-0.html"), null);
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
