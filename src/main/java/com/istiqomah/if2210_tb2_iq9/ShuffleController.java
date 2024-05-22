package com.istiqomah.if2210_tb2_iq9;

import com.istiqomah.if2210_tb2_iq9.model.card.Card;
import com.istiqomah.if2210_tb2_iq9.model.card.CardManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ShuffleController {
    @FXML
    private GridPane cardContainer;

    private List<Card> items;

    @FXML
    public void initialize() {
        items = new ArrayList<>();
        new CardManager();

        addAllCards();

        reshuffle();
    }

    private void addAllCards() {
        // Add animal cards
        for (String name : CardManager.getAnimalNames()) {
            items.add(CardManager.getCard("animal", name));
        }
        // Add plant cards
        for (String name : CardManager.getPlantNames()) {
            items.add(CardManager.getCard("plant", name));
        }
        // Add item cards
        for (String name : CardManager.getItemNames()) {
            items.add(CardManager.getCard("item", name));
        }
    }

    @FXML
    private void reshuffle() {
        Collections.shuffle(items);
        displayCards();
    }

    private void displayCards() {
        cardContainer.getChildren().clear();
        double cardWidth = 160;
        double cardHeight = 230;

        for (int i = 0; i < 4 && i < items.size(); i++) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/CardIcon.fxml"));
                Pane cardPane = loader.load();

                cardPane.setPrefWidth(cardWidth);
                cardPane.setPrefHeight(cardHeight);

                CardIconController controller = loader.getController();
                controller.setCard(items.get(i));

                // Add the card to the container
                cardContainer.add(cardPane, i % 2, i / 2);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
