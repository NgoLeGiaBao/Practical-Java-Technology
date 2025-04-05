package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class TextEditor {
    private TextWriter writer;
    private String text;

    /* @Autowired
    public TextEditor(@Qualifier("plainTextWriter") TextWriter writer) {
        this.writer = writer;
    }
     */
    @Autowired
    public TextEditor(@Qualifier("pdfTextWriter") TextWriter writer) {
        this.writer = writer;
    }

    public void input(String text) {
        this.text = text;
    }

    public void save(String fileName) {
        writer.write(fileName, text);
    }
}
