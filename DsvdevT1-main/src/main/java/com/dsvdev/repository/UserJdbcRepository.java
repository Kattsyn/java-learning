package com.dsvdev.repository;

import com.dsvdev.model.User;
import com.dsvdev.utils.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserJdbcRepository implements UserRepository {


    @Override
    public void createUsersTable() {
        try (Connection connection = Util.getConnection(); Statement statement = connection.createStatement()) {
            String userTableQuery = "CREATE TABLE IF NOT EXISTS users" +
                    "(id BIGSERIAL PRIMARY KEY, name VARCHAR(30), lastName VARCHAR(30), age SMALLINT)";
            statement.execute(userTableQuery);
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }
    }

    @Override
    public void dropUsersTable() {
        try (Connection connection = Util.getConnection(); Statement statement = connection.createStatement()) {
            String dropTableQuery = "DROP TABLE IF EXISTS users";
            statement.execute(dropTableQuery);
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        String saveUserQuery = "INSERT INTO users(name, lastName, age) VALUES(?, ?, ?)";
        try (Connection connection = Util.getConnection(); PreparedStatement statement = connection.prepareStatement(saveUserQuery)) {
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setByte(3, age);
            statement.execute();
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }
    }

    @Override
    public void removeUserById(long id) {
        String deleteUserPrepQuery = "DELETE FROM users WHERE id=?";
        try (Connection connection = Util.getConnection(); PreparedStatement statement = connection.prepareStatement(deleteUserPrepQuery)) {
            statement.setLong(1, id);
            statement.execute();
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }
    }

    @Override
    public List<User> getAllUsers() {
        String selectQuery = "SELECT * FROM users";
        List<User> users = new ArrayList<>();
        try (Connection connection = Util.getConnection(); ResultSet resultSet = connection.createStatement().executeQuery(selectQuery)) {
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                users.add(user);
            }
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        try (Connection connection = Util.getConnection(); Statement statement = connection.createStatement()) {
            String cleanUsersQuery = "DELETE FROM users";
            statement.execute(cleanUsersQuery);
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }
    }
}
