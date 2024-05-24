package com.istiqomah.if2210_tb2_iq9;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoadController {
    private MainPageController mainPageController; // Controller untuk halaman utama

    @FXML
    private Button backButton; // Tombol kembali

    @FXML
    private ComboBox<String> formatComboBox; // ComboBox untuk memilih format

    @FXML
    private TextField folderTextField; // TextField untuk memasukkan path folder

    @FXML
    private Button loadButton; // Tombol load

    @FXML
    private Label statusLabel; // Label untuk menampilkan status

    @FXML
    private void initialize() {
        backButton.setOnAction(event -> handleBackButton()); // Mengatur aksi untuk tombol kembali
        loadButton.setOnAction(event -> handleLoadButton()); // Mengatur aksi untuk tombol load
    }

    // Mengatur controller untuk halaman utama
    public void setMainPageController(MainPageController mainPageController) {
        this.mainPageController = mainPageController;
    }

    // Menangani aksi tombol kembali
    private void handleBackButton() {
        Stage stage = (Stage) backButton.getScene().getWindow(); // Mendapatkan stage dari tombol kembali
        stage.close(); // Menutup stage
    }

    // Menangani aksi tombol load
    private void handleLoadButton() {
        String folderPath = folderTextField.getText(); // Mendapatkan path folder dari TextField

        // Cek apakah path folder kosong
        if (folderPath == null || folderPath.isEmpty()) {
            statusLabel.setText("Folder path is empty"); // Menampilkan pesan bahwa path folder kosong
            return;
        }

        // Memanggil metode loadData pada controller halaman utama
        mainPageController.loadData("src/main/java/com/istiqomah/if2210_tb2_iq9/model/save_load/"+folderPath);
        Stage stage = (Stage) loadButton.getScene().getWindow(); // Mendapatkan stage dari tombol load
        stage.close(); // Menutup stage
    }
}