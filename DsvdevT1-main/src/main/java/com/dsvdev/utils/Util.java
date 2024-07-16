package com.dsvdev.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

    public static Connection getConnection() throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/JDBC-test";
        String user = "postgres";
        String pass = "admin";
        return DriverManager.getConnection(url, user, pass);
    }

}
