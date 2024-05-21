package com.istiqomah.if2210_tb2_iq9;

import com.istiqomah.if2210_tb2_iq9.model.card.Card;


import javafx.fxml.*;
import javafx.scene.image.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class CardIconController {

    public VBox cardIcon;
    @FXML
    private ImageView cardImage;

    @FXML
    private Text cardName;


    @FXML
    public void initialize() {
        // Initialization logic here
    }

    public void setCard(Card card) {
        cardImage.setImage(card.getImage());
        cardName.setText(card.getName());
    }
}