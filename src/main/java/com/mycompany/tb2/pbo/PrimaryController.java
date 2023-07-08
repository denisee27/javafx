package com.mycompany.tb2.pbo;

import java.io.IOException;
//import javafx.event.ActionEvent;
import javafx.fxml.FXML;
//import java.sql.Connection;
//import java.sql.DriverManager;


public class PrimaryController  {
    
   
    //Borrower
    public void switchToHome() throws IOException {
        App.setRoot("home");
    }
    public void switchToBorrowerDaftar() throws IOException {
        App.setRoot("borrowerdaftar");
    }
    public void switchToBorrowerTrx() throws IOException {
        App.setRoot("borrowerpengajuan");
    }
    public void switchToBorrowerHome() throws IOException {
        App.setRoot("borrowerhome");
    }
    
    //Lender
    public void switchToLenderHome() throws IOException {
        App.setRoot("lenderhome");
    }
    public void switchToLenderDaftar() throws IOException {
        App.setRoot("lenderdaftar");
    }
    public void switchToLenderApprove() throws IOException {
        App.setRoot("lenderapprove");
    }
}
