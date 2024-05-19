package com.istiqomah.if2210_tb2_iq9;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class PluginController {

    @FXML
    private Label fileLabel;

    @FXML
    private Text statusText;

    private File selectedFile;

    @FXML
    private void handleKembali() {
        // Implementasi aksi tombol Kembali
    }

    @FXML
    private void handleChooseFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose Plugin File");
        selectedFile = fileChooser.showOpenDialog(new Stage());

        if (selectedFile != null) {
            fileLabel.setText("File Plugin: " + selectedFile.getName());
            statusText.setVisible(false); // Menyembunyikan teks status ketika file dipilih
        }
    }

    @FXML
    private void handleUpload() {
        if (selectedFile != null) {
            // Implementasi aksi upload plugin
            statusText.setText("Plugin Loaded Successfully");
            statusText.setVisible(true); // Menampilkan teks status setelah upload berhasil
        } else {
            statusText.setText("No File Selected");
            statusText.setVisible(true); // Menampilkan teks status jika tidak ada file yang dipilih
        }
    }
}