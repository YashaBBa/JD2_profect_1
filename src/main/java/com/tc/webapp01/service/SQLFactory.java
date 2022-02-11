package com.tc.webapp01.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.ResourceBundle;

public class SQLFactory {
    private static final String userName= "root";
    private static final String password="8707778Yasha";
    private static final String url="jdbc:mysql://localhost:3306/applicationsSystem";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return DriverManager.getConnection(url, userName, password);
    }

}
