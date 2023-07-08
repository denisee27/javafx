package com.mycompany.tb2.pbo;
//import javafx.fxml.Initializable;
//import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import java.io.IOException;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
//import zakat.app.util.DatabaseConnection;
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
public class borrowerController implements Initializable {
    @FXML
    private TextField txtNik;
    @FXML
    private TextField txtNama;
    @FXML
    private TextField txtTempatLahir;
    @FXML
    private DatePicker txtTanggalLahir;
    @FXML
    private TextField txtAlamat;
    @FXML
    private TextField txtNomor;
    @FXML
    private TextField txtPekerjaan;
    @FXML
    private TextField txtPenghasilan;
    @FXML
    private Label confirmLabel;

    private String Nik;
    private String Nama;
    private String TempatLahir;
    private String TanggalLahir;
    private String Pekerjaan;
    private String Nomor;
    private String Alamat;
    private String Penghasilan;
   
    @FXML
    private void btnSubmitDaftar() throws IOException {

        Nik = txtNik.getText();
        Nama = txtNama.getText();
        TempatLahir = txtTempatLahir.getText();
        TanggalLahir = txtTanggalLahir.getValue().toString();
        Pekerjaan = txtPekerjaan.getText();
        Nomor = txtNomor.getText();
        Alamat = txtAlamat.getText();
        Penghasilan = txtPenghasilan.getText();

        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        String query = "INSERT INTO user VALUES (NULL, '" + Nik + "', '" + Nama + "', '"+ Nomor + "', '" + TanggalLahir + "', '" + TempatLahir + "', '" + Alamat + "', '" + Pekerjaan + "', '" + Penghasilan + "')";
        if ((txtNama.getText() != null && !txtNama.getText().isEmpty())
                && (txtAlamat.getText() != null && !txtAlamat.getText().isEmpty())
                && (txtNik.getText() != null && !txtNik.getText().isEmpty())
                && (txtNomor.getText() != null && !txtNomor.getText().isEmpty())
                && (txtTempatLahir.getText() != null && !txtTempatLahir.getText().isEmpty())
                && (txtPenghasilan.getText() != null && !txtPenghasilan.getText().isEmpty())
                ) 
        {
            try {
            // Memeriksa apakah koneksi berhasil
            if (connectDB != null) {
                System.out.println("Koneksi ke database berhasil!");
                Statement statement = connectDB.createStatement();
                statement.executeUpdate(query);
            } else {
                System.out.println("Koneksi ke database gagal.");
            }
                txtNik.clear();
                txtNama.clear();
                txtTempatLahir.clear();
//                txtTanggalLahir.clear();
                txtAlamat.clear();
                txtPekerjaan.clear();
                txtNomor.clear();
                txtPenghasilan.clear();
                switchToBorrowerHome();
                confirmLabel.setText("Daftar berhasil.");
            } catch (SQLException e) {
                e.printStackTrace();
                confirmLabel.setText("Gagal!");
                System.out.println(e);
            }
        } else {
            confirmLabel.setText("ISI SEMUA DATA DENGAN LENGKAP!");
        }

    }
    public void switchToBorrowerHome() throws IOException {
        App.setRoot("borrowerhome");
}
      /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
}

