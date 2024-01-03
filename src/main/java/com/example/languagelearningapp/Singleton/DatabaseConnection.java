package com.example.languagelearningapp.Singleton;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatabaseConnection {

    public static final String URL = "jdbc:sqlite:database.db";

    private static DatabaseConnection instance = null;
    private Connection conn = null;
    private DatabaseConnection() {
        try {
            Class.forName("org.sqlite.JDBC");
            this.conn = DriverManager.getConnection(URL);
            System.out.println("Connection to SQLite has been established.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    public Connection getConn() {
        return conn;
    }
}
