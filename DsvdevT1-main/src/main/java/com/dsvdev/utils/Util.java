package com.dsvdev.utils;

import com.dsvdev.model.User;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String url = "jdbc:postgresql://localhost:5432/jdbc_db";
    private static final String user = "kattsyn";
    private static final String pass = "katt";

    private static SessionFactory sessionFactory;
    private static EntityManagerFactory entityManagerFactory;

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, pass);
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {

                Configuration configuration = new Configuration().configure();
                configuration.addAnnotatedClass(User.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());



/*
                sessionFactory = new Configuration()
                        .configure()
                        .addAnnotatedClass(User.class)
                        .buildSessionFactory();

 */
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return sessionFactory;
    }

    public static EntityManagerFactory getEntityManagerFactory(){
        return null;
    }

}
