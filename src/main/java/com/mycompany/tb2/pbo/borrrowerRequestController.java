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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author Denis
 */
public class borrrowerRequestController implements Initializable {
    @FXML
    private TextField txtNik;
    @FXML
    private Label txtNama;
    @FXML
    private Label txtGaji;
    @FXML
    private Label txtMargin;
    @FXML
    private Label alert;
    @FXML
    private ComboBox jumlahPinjaman;
    @FXML
    private ComboBox tenor;
    
    private String Nik;
    private String nama;
    private String gaji;
    private String jumlahPengajuan;
    private String tenorPengajuan;
    
    @FXML
    void pilihPinjaman(ActionEvent event) {
        String q = jumlahPinjaman.getSelectionModel().getSelectedItem().toString();
        jumlahPengajuan = q.replaceAll("[^0-9]", "");
    }
    @FXML
    void pilihTenor(ActionEvent event) {
        String q = tenor.getSelectionModel().getSelectedItem().toString();
        tenorPengajuan = q.replaceAll("\\D+", "");
    }

   @FXML
   private void cariNik() throws SQLException {
    Nik = txtNik.getText();
    DatabaseConnection connectNow = new DatabaseConnection();
    Connection connectDB = connectNow.getConnection();
    String query = "SELECT * FROM user WHERE nik = ?";
    
    if (connectDB != null) {
        System.out.println("Koneksi ke database berhasil!");
        PreparedStatement preparedStatement = connectDB.prepareStatement(query);
        preparedStatement.setString(1, Nik);
        ResultSet resultSet = preparedStatement.executeQuery();
        ObservableList<String> listPinjaman = FXCollections.observableArrayList();
        jumlahPinjaman.setItems(listPinjaman);
        ComboBox<String> comboBoxPinjaman = new ComboBox<>(listPinjaman);
        
        ObservableList<String> listTenor = FXCollections.observableArrayList();
        tenor.setItems(listTenor);
        ComboBox<String> comboBoxTenor = new ComboBox<>(listTenor);

        if (resultSet.next()) {
            nama = resultSet.getString("nama");
            gaji = resultSet.getString("penghasilan");
            DecimalFormat decimalFormat = new DecimalFormat("#,###");
            String gajiFormatted = decimalFormat.format(Integer.parseInt(gaji));
            txtNama.setText(nama);
            txtGaji.setText("Rp. " + gajiFormatted);
            txtMargin.setText("4%");
            if(Integer.parseInt(gaji) <= 7000000){
                listPinjaman.add("Rp. 5000000");
            }else if(Integer.parseInt(gaji)  > 7000000 && Integer.parseInt(gaji) <= 10000000){
                listPinjaman.add("Rp. 5000000");
                listPinjaman.add("Rp. 10000000");
            }else if(Integer.parseInt(gaji)  > 10000000){
                listPinjaman.add("Rp. 5000000");
                listPinjaman.add("Rp. 10000000");
                listPinjaman.add("Rp. 15000000");
            }
            listTenor.add("3 Bulan");
            listTenor.add("6 Bulan");
            listTenor.add("12 Bulan");
            listTenor.add("18 Bulan"); 
            alert.setText("");
        } else {
            System.out.println("Data tidak ditemukan.");
            alert.setText("NIK PENGAJUAN TIDAK TERDAFTAR!");
            txtNama.setText("");
            txtGaji.setText("");
            txtMargin.setText("");
            listTenor.clear();  
            listPinjaman.clear();  
        }
        
    } else {
        System.out.println("Koneksi ke database gagal.");
    }
}
        @FXML
        private void submitRequest() throws IOException{
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        String query = "INSERT INTO trx VALUES (NULL, '" + nama + "', '" + gaji + "', '" + Nik + "', '" + jumlahPengajuan + "', '"+ tenorPengajuan + "', 0)";
        if ((txtNik.getText() != null && !txtNik.getText().isEmpty())) 
        {
            System.out.print(jumlahPengajuan);

            try {
            if (connectDB != null) {
                System.out.println("Koneksi ke database berhasil!");
                Statement statement = connectDB.createStatement();
                statement.executeUpdate(query);
            } else {
                System.out.println("Koneksi ke database gagal.");
            }
                txtNik.clear();
                switchToBorrowerHome();
                alert.setText("Daftar berhasil.");
            } catch (SQLException e) {
                e.printStackTrace();
                alert.setText("Gagal!");
                System.out.println(e);
            }
        } else {
            alert.setText("Isi semua data yang diperlukan!");
        }
    }
    public void switchToBorrowerHome() throws IOException {
        App.setRoot("borrowerhome");
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
    }
}
