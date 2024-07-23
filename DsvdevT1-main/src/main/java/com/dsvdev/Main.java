package com.dsvdev;
import com.dsvdev.model.User;
import com.dsvdev.repository.UserHibernateRepository;
import com.dsvdev.repository.UserJdbcRepository;
import com.dsvdev.repository.UserRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {
    public static void main(String[] args) {
/*
        UserRepository userRepository = new UserJdbcRepository();
        userRepository.createUsersTable();
        userRepository.saveUser("Ivan", "Ivanov", (byte) 15);
        userRepository.saveUser("Abram", "Loginov", (byte) 42);
        userRepository.saveUser("Mark", "II", (byte) 22);
        userRepository.saveUser("Ellie", "Lincoln", (byte) 37);
        System.out.println(userRepository.getAllUsers());
        userRepository.removeUserById(2);
        System.out.println(userRepository.getAllUsers());
        userRepository.cleanUsersTable();
        System.out.println(userRepository.getAllUsers());
        userRepository.dropUsersTable();


 */

        UserRepository userHibernateRepository = new UserHibernateRepository();
        userHibernateRepository.createUsersTable();
        userHibernateRepository.saveUser("Ivan", "Ivanov", (byte) 15);
        userHibernateRepository.saveUser("Abram", "Loginov", (byte) 42);
        userHibernateRepository.saveUser("Mark", "II", (byte) 22);
        userHibernateRepository.saveUser("Ellie", "Lincoln", (byte) 37);
        System.out.println(userHibernateRepository.getAllUsers());
        userHibernateRepository.removeUserById(2);
        System.out.println(userHibernateRepository.getAllUsers());
        userHibernateRepository.cleanUsersTable();
        System.out.println(userHibernateRepository.getAllUsers());
        userHibernateRepository.dropUsersTable();

    }
}