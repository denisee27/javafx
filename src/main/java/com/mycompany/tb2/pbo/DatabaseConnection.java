package com.mycompany.tb2.pbo;
import java.sql.Connection;
import java.sql.DriverManager;
//
///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//
///**
// *
// * @author Denis
// */
public class DatabaseConnection {
    public Connection databaseLink;
    
    public Connection getConnection(){
        String databaseName = "pbo";
        String databaseUser = "root";
        String databasePassword  = "qwerty";
        String url = "jdbc:mysql://localhost:3305/" + databaseName;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink = DriverManager.getConnection(url, databaseUser, databasePassword);
        } catch (Exception e) {
//            e.printStackTrace();
        }
        return databaseLink;
    }
}
