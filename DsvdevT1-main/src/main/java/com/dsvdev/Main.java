package com.dsvdev;

import com.dsvdev.repository.UserJdbcRepository;
import com.dsvdev.repository.UserRepository;

public class Main {
    public static void main(String[] args) {
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
    }
}