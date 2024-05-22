package com.istiqomah.if2210_tb2_iq9;

import com.istiqomah.if2210_tb2_iq9.model.save_load.Pair;
import com.istiqomah.if2210_tb2_iq9.model.save_load.Triple;
import com.istiqomah.if2210_tb2_iq9.model.save_load.Readconfig;
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
        // Implement the action for the back button
        System.out.println("Back button clicked");
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();
    }

    private void handleLoadButton() {
        // Get the folder path from the TextField
        String folderPath = folderTextField.getText();

        // Validate the folder path (optional)
        if (folderPath == null || folderPath.isEmpty()) {
            System.out.println("Folder path is empty");
            statusLabel.setText("Folder path is empty");
            return;
        }

        mainPageController.loadData(folderPath);
        Stage stage = (Stage) loadButton.getScene().getWindow();
        stage.close();
    }
}
