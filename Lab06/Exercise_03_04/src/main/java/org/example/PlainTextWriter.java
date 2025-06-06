package org.example;

import java.io.FileWriter;
import java.io.IOException;
import org.springframework.stereotype.Component;


@Component
public class PlainTextWriter implements TextWriter {
    @Override
    public void write(String fileName, String text) {
        try (FileWriter writer = new FileWriter(fileName + ".txt")) {
            writer.write(text);
            System.out.println("Plain text written to " + fileName + ".txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
