package com.istiqomah.if2210_tb2_iq9;

import com.istiqomah.if2210_tb2_iq9.model.card.*;
import com.istiqomah.if2210_tb2_iq9.model.toko.Toko;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;


public class CardTokoController {

    @FXML
    public AnchorPane cardToko;

    @FXML
    public Pane cardholder;

    @FXML
    private Label hargainput;

    @FXML
    private Label jumlahinput;

    private Toko toko;

    public CardTokoController() {}

    @FXML
    public void initialize() {}

    public void setCardToko(Card card) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/CardIcon.fxml"));
            Pane newLoadedPane = loader.load();

            cardholder.getChildren().add(newLoadedPane);
            CardIconController cardIconController= loader.getController();
            cardIconController.setCard(card);
            if (card instanceof Product){
                Product product = (Product) card;
                setPrice(String.valueOf(product.getPrice()));
                setQuantity(String.valueOf(toko.getAvailableProducts().get(card)));
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void setToko(Toko toko) {
        this.toko = toko;
    }

    private void setPrice(String price) {
        hargainput.setText(price);
    }

    private void setQuantity(String quantity) {
        jumlahinput.setText(quantity);
    }
}