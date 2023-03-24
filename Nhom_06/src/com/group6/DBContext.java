package com.group6;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBContext {
    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
            System.out.println("Kết nối thành công!");
        } catch (Exception ex) {
            System.out.println("Kết nối thất bại!");
            ex.printStackTrace();
        }
        return conn;
    }

    private static String DB_URL = "jdbc:mysql://localhost:3306/hr_management";
    private static String USER_NAME = "root";
    private static String PASSWORD = "123456";
}
