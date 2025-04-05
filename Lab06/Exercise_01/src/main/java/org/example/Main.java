package org.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("appConfig.xml");


        Product product1_1 = (Product) context.getBean("product1");
        Product product1_2 = (Product) context.getBean("product1");

        Product product2_1 = (Product) context.getBean("product2");
        Product product2_2 = (Product) context.getBean("product2");

        Product product3_1 = (Product) context.getBean("product3");
        Product product3_2 = (Product) context.getBean("product3");

        System.out.println("Product1_1: " + product1_1);
        System.out.println("Product1_2: " + product1_2);

        System.out.println("Product2_1: " + product2_1);
        System.out.println("Product2_2: " + product2_2);

        System.out.println("Product3_1: " + product3_1);
        System.out.println("Product3_2: " + product3_2);

        System.out.println("Product1 is Prototype: " + (product1_1 == product1_2));
        System.out.println("Product2 is Prototype: " + (product2_1 != product2_2));
        System.out.println("Product3 is Singleton: " + (product3_1 == product3_2));

    }
}
