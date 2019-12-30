/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.vin.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author MinhKudo
 */
public class ConnectionDB {

    public static Connection getConnectionDB() throws ClassNotFoundException, SQLException {

        String hostName = "localhost";
        String dbName = "vin";
        String userName = "root";
        String password = "";

        Class.forName("com.mysql.cj.jdbc.Driver");

        // Cấu trúc URL Connection dành cho Oracle
        // Ví dụ: jdbc:mysql://localhost:3306/simplehr
        String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName;

        Connection conn = DriverManager.getConnection(connectionURL, userName,
                password);
        return conn;
    }
}
