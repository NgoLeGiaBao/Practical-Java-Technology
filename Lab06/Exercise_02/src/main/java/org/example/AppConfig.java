package org.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class AppConfig {

    @Bean
    @Scope("prototype")
    public Product product1() {
        return new Product(1, "Product 1", 10.0, "Description of Product 1");
    }

    @Bean
    @Scope("prototype")
    public Product product2() {
        return new Product(2, "Product 2", 20.0, "Description of Product 2");
    }

    @Bean
    @Scope("singleton")
    public Product product3() {
        return new Product(3, "Product 3", 30.0, "Description of Product 3");
    }
}
