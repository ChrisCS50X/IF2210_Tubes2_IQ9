package com.istiqomah.if2210_tb2_iq9;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class SaveController {

    @FXML
    private Button backButton;

    @FXML
    private ComboBox<String> formatComboBox;

    @FXML
    private TextField folderTextField;

    @FXML
    private Button saveButton;

    @FXML
    private Label statusLabel;

    @FXML
    private void initialize() {
        backButton.setOnAction(event -> handleBackButton());
        saveButton.setOnAction(event -> handleSaveButton());
    }

    private void handleBackButton() {
        // Implement the action for the back button
        System.out.println("Back button clicked");
    }

    private void handleSaveButton() {
        // Implement the action for the load button
        System.out.println("Load button clicked");
        statusLabel.setVisible(true); // Show the status label when load is clicked
    }
}

