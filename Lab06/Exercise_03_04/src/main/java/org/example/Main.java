package org.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        TextEditor editor = context.getBean(TextEditor.class);
        editor.input("This is a test for PDF text.");
        editor.save("output");
    }
}
