package com.istiqomah.if2210_tb2_iq9;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoadController {
    private MainPageController mainPageController;

    @FXML
    private Button backButton;

    @FXML
    private ComboBox<String> formatComboBox;

    @FXML
    private TextField folderTextField;

    @FXML
    private Button loadButton;

    @FXML
    private Label statusLabel;

    @FXML
    private void initialize() {
        backButton.setOnAction(event -> handleBackButton());
        loadButton.setOnAction(event -> handleLoadButton());
    }

    public void setMainPageController(MainPageController mainPageController) {
        this.mainPageController = mainPageController;
    }

    private void handleBackButton() {
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();
    }

    private void handleLoadButton() {
        String folderPath = folderTextField.getText();

        if (folderPath == null || folderPath.isEmpty()) {
            statusLabel.setText("Folder path is empty");
            return;
        }

        mainPageController.loadData("src/main/java/com/istiqomah/if2210_tb2_iq9/model/save_load/"+folderPath);
        Stage stage = (Stage) loadButton.getScene().getWindow();
        stage.close();
    }
}
