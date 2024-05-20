package com.istiqomah.if2210_tb2_iq9;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CardController {

    @FXML
    private Label cardName;

    @FXML
    private ImageView cardImage;

    @FXML
    private Label statusLabel;

    @FXML
    private Label itemsLabel;

    @FXML
    private Button harvestButton;

    private StringProperty cardStatus = new SimpleStringProperty();
    private StringProperty activeItems = new SimpleStringProperty();

    @FXML
    private void initialize() {
        // Bind the labels to the properties
        statusLabel.textProperty().bind(cardStatus);
        itemsLabel.textProperty().bind(activeItems);

        // Example data
        cardName.setText("Domba");
//        cardImage.setImage(new Image("card.image/Hewan/sheep.png")); // Update the path to your image file
        setCardStatus("Berat : 5 (8)");
        setActiveItems("Item aktif : Accelerate (1), Delay (1), Protect (1)");

        // Example of enabling the harvest button if the condition is met
        harvestButton.setDisable(true); // Or use a condition here
    }

    public void setCardStatus(String status) {
        this.cardStatus.set(status);
    }

    public void setActiveItems(String items) {
        this.activeItems.set(items);
    }

    @FXML
    private void handleBack() {
        // Handle back button logic
        System.out.println("Back button clicked");
    }

    @FXML
    private void handleHarvest() {
        // Handle harvest logic
        System.out.println("Harvest button clicked");
    }
}
