package com.lab03.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class HibernateUtil {
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            // Load configure from db properties
            Properties properties = new Properties();
            properties.load(new FileInputStream("src/main/resources/db.properties"));

            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml"); // Load từ hibernate.cfg.xml

            // Set up information from db.properties
            configuration.setProperty("hibernate.connection.url", properties.getProperty("db.url"));
            configuration.setProperty("hibernate.connection.username", properties.getProperty("db.user"));

            return configuration.buildSessionFactory();
        } catch (IOException ex) {
            throw new RuntimeException("Error from read db.properties: " + ex.getMessage(), ex);
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError("Error to create SessionFactory: " + ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
