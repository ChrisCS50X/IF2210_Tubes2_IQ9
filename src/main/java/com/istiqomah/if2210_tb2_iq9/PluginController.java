package com.istiqomah.if2210_tb2_iq9;

import com.istiqomah.if2210_tb2_iq9.plugin.PluginRegistry;
import com.istiqomah.if2210_tb2_iq9.plugin.SaveLoadPlugin;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

public class PluginController {
    private MainPageController mainPageController;

    @FXML
    private Label fileLabel;

    @FXML
    private Text statusText;

    private File selectedFile;

    @FXML
    private void handleKembali() {
        // Implementasi aksi tombol Kembali
        Stage stage = (Stage) fileLabel.getScene().getWindow();
        stage.close();
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

    public void setMainPageController(MainPageController mainPageController) {
        this.mainPageController = mainPageController;
    }

    @FXML
    private void handleUpload() {
        if (selectedFile != null) {
            try {
                URL jarUrl = selectedFile.toURI().toURL();
                URLClassLoader classLoader = new URLClassLoader(new URL[]{jarUrl}, this.getClass().getClassLoader());

                // Assume that plugin class implements SaveLoadPlugin and has a no-arg constructor
                String pluginClassName = getPluginClassName(selectedFile);
                Class<?> pluginClass = Class.forName(pluginClassName, true, classLoader);
                SaveLoadPlugin pluginInstance = (SaveLoadPlugin) pluginClass.getDeclaredConstructor().newInstance();

                // Register the plugin with the main application
                PluginRegistry.registerPlugin(pluginInstance);

                // Update available formats in the main controller
                if (mainPageController != null) {
                    mainPageController.updateAvailableFormats();
                }

                statusText.setText("Plugin Loaded Successfully");
                statusText.setVisible(true); // Menampilkan teks status setelah upload berhasil
            } catch (Exception e) {
                e.printStackTrace();
                statusText.setText("Failed to Load Plugin");
                statusText.setVisible(true); // Menampilkan teks status jika terjadi kesalahan
            }
        } else {
            statusText.setText("No File Selected");
            statusText.setVisible(true); // Menampilkan teks status jika tidak ada file yang dipilih
        }
    }

    private String getPluginClassName(File file) {
        if (file.getName().contains("json")) {
            return "com.example.jsonplugin.JsonSaveLoadPlugin";
        } else if (file.getName().contains("xml")) {
            return "com.example.xmlplugin.XmlSaveLoadPlugin";
        }
        return null;
    }
}
