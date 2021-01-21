package com.hpanushan.sink;

import java.sql.Connection;
import java.sql.DriverManager;

public class Test {
    private static Connection getConnection() {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://192.168.8.105:3306/flink?useUnicode=true&characterEncoding=UTF-8", "anushan", "Omnibis.1234");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return con;
    }

    public static void main(String[] args) {
        Connection con = getConnection();
    }
}
