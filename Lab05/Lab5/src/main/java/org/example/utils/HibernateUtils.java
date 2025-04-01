package org.example.utils;

import lombok.Getter;
import org.example.model.Product;
import org.example.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtils {

    @Getter
    private static final SessionFactory sessionFactory;

    public HibernateUtils() {

    }
    static {
        try {
            Configuration getConfiguration = new Configuration();
            getConfiguration.configure();
            getConfiguration.addAnnotatedClass(User.class);
            getConfiguration.addAnnotatedClass(Product.class);
            sessionFactory = buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    private static SessionFactory buildSessionFactory() {
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .configure()
                .build();
        Metadata metadata = new MetadataSources(serviceRegistry).getMetadataBuilder().build();
        return metadata.getSessionFactoryBuilder().build();
    }

    public static void Close() {
        getSessionFactory().close();
    }

}
