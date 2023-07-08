/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tb2.pbo;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author Denis
 */
public class lenderController implements Initializable {
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
    private Label confirmLabel;

    private String Nik;
    private String Nama;
    private String TempatLahir;
    private String TanggalLahir;
    private String Pekerjaan;
    private String Nomor;
    private String Alamat;
   
    @FXML
    private void btnSubmitDaftar() throws IOException {

        Nik = txtNik.getText();
        Nama = txtNama.getText();
        TempatLahir = txtTempatLahir.getText();
        TanggalLahir = txtTanggalLahir.getValue().toString();
        Pekerjaan = txtPekerjaan.getText();
        Nomor = txtNomor.getText();
        Alamat = txtAlamat.getText();

        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        String query = "INSERT INTO user VALUES (NULL, '" + Nik + "', '" + Nama + "', '"+ Nomor + "', '" + TanggalLahir + "', '" + TempatLahir + "', '" + Alamat + "', '" + Pekerjaan + "', Null)";
        if ((txtNama.getText() != null && !txtNama.getText().isEmpty())
                && (txtAlamat.getText() != null && !txtAlamat.getText().isEmpty())
                && (txtNik.getText() != null && !txtNik.getText().isEmpty())
                && (txtNomor.getText() != null && !txtNomor.getText().isEmpty())
                && (txtTempatLahir.getText() != null && !txtTempatLahir.getText().isEmpty())
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
                txtAlamat.clear();
                txtPekerjaan.clear();
                txtNomor.clear();
                switchToLenderHome();
                confirmLabel.setText("Daftar berhasil.");
            } catch (SQLException e) {
                e.printStackTrace();
                confirmLabel.setText("Gagal!");
                System.out.println(e);
            }
        } else {
            confirmLabel.setText("Isi semua data yang diperlukan!");
        }

    }
    public void switchToLenderHome() throws IOException {
        App.setRoot("lenderhome");
    }
      /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
}
