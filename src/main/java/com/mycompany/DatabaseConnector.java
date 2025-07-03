package com.mycompany;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {

    // --- CONFIGURE YOUR DATABASE CONNECTION HERE --- //
    private static final String USER = "root";          // <-- CHANGE TO YOUR DB USER
    private static final String PASS = "root"; // <-- CHANGE TO YOUR DB PASSWORD

    // --- UNCOMMENT THE URL FOR THE DATABASE YOU ARE USING --- //
    // For MySQL
    private static final String DB_URL = "jdbc:mysql://localhost:3306/employee_db";

    // For PostgreSQL
    // private static final String DB_URL = "jdbc:postgresql://localhost:5432/employee_db";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }
}