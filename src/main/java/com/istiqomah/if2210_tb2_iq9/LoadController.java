package com.istiqomah.if2210_tb2_iq9;

import com.istiqomah.if2210_tb2_iq9.plugin.PluginRegistry;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.List;

public class LoadController {
    private MainPageController mainPageController;

    @FXML
    private Button backButton;

    @FXML
    private ComboBox<String> formatComboBoxplug;

    @FXML
    private TextField folderTextField;

    @FXML
    private Button loadButton;

    @FXML
    private Label statusLabel;

    @FXML
    private void initialize() {
        updateAvailableFormats();
        backButton.setOnAction(event -> handleBackButton());
        loadButton.setOnAction(event -> handleLoadButton());
    }

    public void updateAvailableFormats() {
        if (formatComboBoxplug != null) {
            formatComboBoxplug.getItems().clear();
            formatComboBoxplug.getItems().add("TXT");

            // Add formats supported by plugins
            List<String> pluginFormats = PluginRegistry.getSupportedFormats();
            formatComboBoxplug.getItems().addAll(pluginFormats);

            // Set default selection
            formatComboBoxplug.setValue("TXT");
        }
    }

    public void setMainPageController(MainPageController mainPageController) {
        this.mainPageController = mainPageController;
    }

    private void handleBackButton() {
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handleLoadButton() {
        String format = formatComboBoxplug.getValue();
        String folderPath = folderTextField.getText();

        if (folderPath == null || folderPath.isEmpty()) {
            statusLabel.setText("Folder path is empty");
            return;
        }

        mainPageController.loadData("classes/com/istiqomah/if2210_tb2_iq9/model/save_load/"+folderPath, format);
        statusLabel.setText("State Loaded Successfully");
    }
}