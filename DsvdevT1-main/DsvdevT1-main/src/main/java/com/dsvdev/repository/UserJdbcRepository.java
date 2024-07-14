package com.dsvdev.repository;

import com.dsvdev.model.User;

import java.util.List;

public class UserJdbcRepository implements UserRepository{
    @Override
    public void createUsersTable() {

    }

    @Override
    public void dropUsersTable() {

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {

    }

    @Override
    public void removeUserById(long id) {

    }

    @Override
    public List<User> getAllUsers() {
        return List.of();
    }

    @Override
    public void cleanUsersTable() {

    }
}
