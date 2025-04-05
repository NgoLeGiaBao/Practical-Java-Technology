package org.example;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

@Component
public class PdfTextWriter implements TextWriter {

    @Override
    public void write(String fileName, String text) {
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(fileName + ".pdf"));
            document.open();
            document.add(new Paragraph(text));
            document.close();
            System.out.println("PDF file created: " + fileName + ".pdf");
        } catch (FileNotFoundException | DocumentException e) {
            e.printStackTrace();
        }
    }
}
