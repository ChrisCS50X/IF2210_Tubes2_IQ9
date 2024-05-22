package com.istiqomah.if2210_tb2_iq9;

import com.istiqomah.if2210_tb2_iq9.model.card.Card;
import com.istiqomah.if2210_tb2_iq9.model.card.CardManager;
import com.istiqomah.if2210_tb2_iq9.model.player.Player;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.*;

public class ShuffleController {
    @FXML
    private GridPane cardContainer;

    @FXML
    private Button okButton;

    @FXML
    private Label remainingSelectionsLabel;

    private int maxSelections;
    private List<Card> items;
    private List<Card> selectedCards = new ArrayList<>();
    private List<Button> cardButtons = new ArrayList<>();
    private List<Card> displayedCards = new ArrayList<>();

    @FXML
    public void initialize() {
        items = new ArrayList<>();
        new CardManager();

        addAllCards();
        reshuffle();
    }

    private void addAllCards() {
        // Tambahin animal cards
        for (String name : CardManager.getAnimalNames()) {
            items.add(CardManager.getCard("animal", name));
        }
        // Tambahin plant cards
        for (String name : CardManager.getPlantNames()) {
            items.add(CardManager.getCard("plant", name));
        }
        // Tambahin item cards
        for (String name : CardManager.getItemNames()) {
            items.add(CardManager.getCard("item", name));
        }

        // Tambahin product cards
        for (String name : CardManager.getProductNames()) {
            items.add(CardManager.getCard("product", name));
        }
    }

    @FXML
    private void reshuffle() {
        displayedCards.clear();
        selectedCards.clear();
        Collections.shuffle(items);
        displayCards();
    }

    private void displayCards() {
        cardContainer.getChildren().clear();
        double cardWidth = 160;
        double cardHeight = 230;

        // Jumlah cards yang bisa diselect player
        maxSelections = 6 - (int) Player.getPlayerNow().getDeck().getHand().stream().filter(Objects::nonNull).count();
        updateLabel(maxSelections);

        for (int i = 0; i < 4 && i < items.size(); i++) {
            try {
                Card randomCard;
                do {
                    randomCard = items.get(new Random().nextInt(items.size()));
                } while (displayedCards.contains(randomCard));

                System.out.println("Random card: " + randomCard.getName());
                System.out.println("Displayed cards:");
                for (Card displayedCard : displayedCards) {
                    System.out.println(displayedCard.getName());
                }

                displayedCards.add(randomCard);

                FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/CardIcon.fxml"));
                Pane cardPane = loader.load();

                cardPane.setPrefWidth(cardWidth);
                cardPane.setPrefHeight(cardHeight);

                // Ubah card jadi button type biar clickable
                Button cardButton = new Button();
                cardButton.setGraphic(cardPane);

                CardIconController controller = loader.getController();
                controller.setCard(displayedCards.get(i));

                int index = i;
                cardButton.setOnAction(event -> toggleSelection(cardButton, items.get(index)));

                cardButtons.add(cardButton);

                cardContainer.add(cardButton, i % 2, i / 2);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void toggleSelection(Button cardButton, Card card) {
        System.out.println(maxSelections);
        if (selectedCards.contains(card)) {
            selectedCards.remove(card);
            cardButton.setStyle("");
        } else {
            if (selectedCards.size() < maxSelections) {
                selectedCards.add(card);
                cardButton.setStyle("-fx-background-color: lightgreen;");
            }
        }
    }

    @FXML
    private void updateLabel(int maxSelections) {
        if (maxSelections > 0 && maxSelections < 4) {
            remainingSelectionsLabel.setText("Select " + maxSelections + " cards");
        } else if (maxSelections >= 4) {
            remainingSelectionsLabel.setText("Select all cards");
        } else {
            remainingSelectionsLabel.setText("Your hand is full! Click OK");
        }
    }

    @FXML
    private void selectCards() {
        System.out.println(selectedCards);
        Player.getPlayerNow().addCardsToHand(selectedCards);
    }

    @FXML
    public void closeWindow() {
        selectCards();
        System.out.println(Player.getPlayerNow().getDeck().getHand());
        Stage stage = (Stage) okButton.getScene().getWindow();
        selectedCards.clear();
        stage.close();
    }
}
