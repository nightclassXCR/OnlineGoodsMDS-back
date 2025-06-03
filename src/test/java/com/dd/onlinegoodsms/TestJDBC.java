package com.dd.onlinegoodsms;

import java.sql.*;

public class TestJDBC {
    public static void main(String[] args) throws Exception {

        String url ="jdbc:mysql://localhost:3306/onlinegoodsms?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";
        String user ="root";
        String password ="20050129aaa";

        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connection success!");

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM user");

            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") + ", 名称: " + rs.getString("username"));
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    }


