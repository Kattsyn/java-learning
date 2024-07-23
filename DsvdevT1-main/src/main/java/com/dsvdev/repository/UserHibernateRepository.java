package com.dsvdev.repository;

import com.dsvdev.model.User;
import com.dsvdev.utils.Util;
import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserHibernateRepository implements UserRepository {
    private static final String USER_TABLE_QUERY = "CREATE TABLE IF NOT EXISTS users" +
            "(id BIGSERIAL PRIMARY KEY, name VARCHAR(30), lastName VARCHAR(30), age SMALLINT)";
    private static final String DROP_TABLE_QUERY = "DROP TABLE IF EXISTS users";
    private static final String CLEAN_USERS_QUERY = "delete User users";

    @Override
    public void createUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createNativeQuery(USER_TABLE_QUERY).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createNativeQuery(DROP_TABLE_QUERY).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = Util.getSessionFactory().openSession()) {
            session.save(new User(name, lastName, age));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = Util.getSessionFactory().openSession()) {

            Transaction transaction = session.beginTransaction();
            User user = session.get(User.class, id);
            session.remove(user);
            session.flush();
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<User> getAllUsers() {
        try (Session session = Util.getSessionFactory().openSession()) {
            return session.createQuery("from User", User.class).list();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createQuery(CLEAN_USERS_QUERY).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
