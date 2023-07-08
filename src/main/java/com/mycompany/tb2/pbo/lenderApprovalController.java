/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tb2.pbo;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author Denis
 */
public class lenderApprovalController implements Initializable {
      @FXML
    private Label alert;

    @FXML
    private Label txtGaji;

    @FXML
    private Label txtMargin;

    @FXML
    private Label txtNama;

    @FXML
    private TextField txtNik;

    @FXML
    private Label txtPengajuan;

    @FXML
    private Label txtStatus;

    @FXML
    private Label txtTenor;
    
    private String Nik;
    private String nama;
    private String gaji;
    private String pengajuan;
    private String status;
    private String tenor;

    @FXML
   private void cariNik() throws SQLException {
    Nik = txtNik.getText();
    DatabaseConnection connectNow = new DatabaseConnection();
    Connection connectDB = connectNow.getConnection();
    String query = "SELECT * FROM trx WHERE nik = ?";
    
     if (connectDB != null) {
        System.out.println("Koneksi ke database berhasil!");
        PreparedStatement preparedStatement = connectDB.prepareStatement(query);
        preparedStatement.setString(1, Nik);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            nama = resultSet.getString("nama");
            gaji = resultSet.getString("penghasilan");
            pengajuan = resultSet.getString("pengajuan");
            status = resultSet.getString("status");
            tenor = resultSet.getString("tenor");
            DecimalFormat decimalFormat = new DecimalFormat("#,###");
            String gajiFormatted = decimalFormat.format(Integer.parseInt(gaji));
            DecimalFormat pengajuanFormat = new DecimalFormat("#,###");
            String pengajuanFormatted = pengajuanFormat.format(Integer.parseInt(pengajuan));
            txtNama.setText(nama);
            txtPengajuan.setText("Rp. " + pengajuanFormatted);
            txtGaji.setText("Rp. " + gajiFormatted);
            txtTenor.setText(tenor + " Bulan");
            txtMargin.setText("4%");
            if (Integer.parseInt(status) == 0) {
            txtStatus.setText("Menunggu Approval");
            } else if (Integer.parseInt(status) == -1) {
            txtStatus.setText("Pengajuan Ditolak");
            } else {
            txtStatus.setText("Pengajuan Telah Diterima");
            }
        }else{
            System.out.println("Data tidak ditemukan.");
            alert.setText("NIK PENGAJUAN TIDAK ADA!");
            txtNama.setText("");
            txtGaji.setText("");
            txtMargin.setText("");
            txtPengajuan.setText("");
            txtStatus.setText("");
            txtTenor.setText("");
        }
     }else {
        System.out.println("Koneksi ke database gagal.");

     }
    }
        @FXML
        private void btnApprove() throws IOException{
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        String query = "UPDATE trx SET status = 1 WHERE nik = ('"+ Nik + "')";
        if ((txtNik.getText() != null && !txtNik.getText().isEmpty())) 
        {
            try {
            if (connectDB != null) {
                System.out.println("Koneksi ke database berhasil!");
                Statement statement = connectDB.createStatement();
                statement.executeUpdate(query);
                alert.setText("Data Pengajuan Berhasil Diperbaharui");
                txtStatus.setText("Pengajuan Telah Diterima");
            } else {
                System.out.println("Koneksi ke database gagal.");
            }
            } catch (SQLException e) {
                e.printStackTrace();
                alert.setText("Gagal!");
                System.out.println(e);
            }
        } else {
            alert.setText("DATA PENGAJUAN MASIH KOSONG!");
        }
    }
        
        @FXML
        private void btnReject() throws IOException{
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        String query = "UPDATE trx SET status = -1 WHERE nik = ('"+ Nik + "')";
        if ((txtNik.getText() != null && !txtNik.getText().isEmpty())) 
        {
            try {
            if (connectDB != null) {
                System.out.println("Koneksi ke database berhasil!");
                Statement statement = connectDB.createStatement();
                statement.executeUpdate(query);
                txtStatus.setText("Pengajuan Ditolak");
                alert.setText("Data Pengajuan Berhasil Diperbaharui");  
            } else {
                System.out.println("Koneksi ke database gagal.");
            }
            } catch (SQLException e) {
                e.printStackTrace();
                alert.setText("Gagal!");
                System.out.println(e);
            }
        } else {
            alert.setText("DATA PENGAJUAN MASIH KOSONG!");
        }
    }
    
    
    public void switchToLenderHome() throws IOException {
        App.setRoot("lenderhome");
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                
    }

    
}
