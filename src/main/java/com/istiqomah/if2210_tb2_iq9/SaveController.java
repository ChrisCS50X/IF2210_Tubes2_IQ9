package com.istiqomah.if2210_tb2_iq9;

import javafx.fxml.FXML;
import com.istiqomah.if2210_tb2_iq9.plugin.PluginRegistry;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Button;

import java.util.List;

public class SaveController {

    private MainPageController mainPageController;

    @FXML
    private ComboBox<String> formatComboBoxplug;

    @FXML
    private TextField folderTextField;

    @FXML
    private Button backButton;

    @FXML
    private Label statusLabel;

    @FXML
    private Button saveButton;

    @FXML
    private void initialize() {
        updateAvailableFormats();
        backButton.setOnAction(event -> handleBackButton());
        saveButton.setOnAction(event -> handleSaveButton());
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
    private void handleSaveButton() {
        String format = formatComboBoxplug.getValue();
        String folderPath = folderTextField.getText();

        if (folderPath == null || folderPath.isEmpty()) {
            statusLabel.setText("Folder path is empty");
            return;
        }

        mainPageController.saveData("classes/com/istiqomah/if2210_tb2_iq9/model/save_load/"+folderPath, format);
        statusLabel.setText("State Saved Successfully");
    }
}
