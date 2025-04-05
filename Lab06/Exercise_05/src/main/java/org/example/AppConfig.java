package org.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import org.springframework.beans.factory.annotation.Value;

@Configuration
@ComponentScan(basePackages = "org.example")
@PropertySource("classpath:application.properties")
public class AppConfig {

    @Value("${editor.name}")
    private String editorName;

    @Value("${file.name}")
    private String fileName;

    @Value("${text.content}")
    private String textContent;

    @Bean
    public TextEditor textEditor() {
        return new TextEditor(editorName, fileName, textContent);
    }
}
