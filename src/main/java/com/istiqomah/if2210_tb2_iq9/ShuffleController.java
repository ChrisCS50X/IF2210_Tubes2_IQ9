package com.istiqomah.if2210_tb2_iq9;

import com.istiqomah.if2210_tb2_iq9.model.card.Card;
import com.istiqomah.if2210_tb2_iq9.model.cardcollection.Deck;
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
    private Deck playerDeck = Player.getPlayerNow().getDeck();;
    private List<Card> selectedCards = new ArrayList<>();
    private List<Button> cardButtons = new ArrayList<>();
    private List<Card> displayedCards = new ArrayList<>();

    @FXML
    private void initialize() {
        System.out.println("playerDeck: " + playerDeck.getMainDeckSize());
        reshuffle();
    }
    @FXML
    private void reshuffle() {
        displayedCards.clear();
        selectedCards.clear();
        System.out.println("Shuffling main deck...");
        playerDeck.shuffleMainDeck();
        System.out.println("Starting display cards...");
        displayCards();
    }

    private void displayCards() {
        cardContainer.getChildren().clear();
        double cardWidth = 160;
        double cardHeight = 230;

        // Jumlah cards yang bisa diselect player
        maxSelections = 6 - (int) playerDeck.getHand().stream().filter(Objects::nonNull).count();
        System.out.println("maxSelections: " + maxSelections);
        updateLabel(maxSelections);

        System.out.println("Main deck size: " + playerDeck.getMainDeckSize());

        if (playerDeck.getMainDeckSize() != 0) {
            for (int i = 0; i < 4; i++) {
                try {
                    Random random = new Random();
                    Boolean cek = true;
                    while (cek) {
                        Card randomCard = playerDeck.getMainDeck().get(random.nextInt(playerDeck.getMainDeckSize()));
                        if (!displayedCards.stream().anyMatch(card -> card.getName().equals(randomCard.getName())) && !randomCard.getName().equals("Beruang")) {
                            displayedCards.add(randomCard);
                            cek = false;
                        }
                    }

//                    System.out.println("Random card: " + randomCard.getName());
                    System.out.println("Displayed cards:");
                    for (Card displayedCard : displayedCards) {
                        System.out.println(displayedCard.getName());
                    }


                    FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/CardIcon.fxml"));
                    Pane cardPane = loader.load();

                    cardPane.setPrefWidth(cardWidth);
                    cardPane.setPrefHeight(cardHeight);

                    // Ubah card jadi button type biar clickable
                    Button cardButton = new Button();
                    cardButton.setGraphic(cardPane);
                    cardButton.setStyle("-fx-background-color: none; -fx-font-size: 14px; -fx-border-radius: 5; -fx-background-radius: 5;");

                    CardIconController controller = loader.getController();
                    controller.setCard(displayedCards.get(i));

                    int index = i;
                    cardButton.setOnAction(event -> toggleSelection(cardButton, displayedCards.get(index)));

                    cardButtons.add(cardButton);

                    cardContainer.add(cardButton, i % 2, i / 2);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            updateLabel(0);
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
            remainingSelectionsLabel.setText("SELECT " + maxSelections + " CARDS");
        } else if (maxSelections >= 4) {
            remainingSelectionsLabel.setText("SELECT ALL CARDS");
        } else if (maxSelections == 0) {
            remainingSelectionsLabel.setText("YOUR HAND IS FULL! CLICK OK");
        }
    }

    @FXML
    private void selectCards() {
        System.out.println(selectedCards);
        Player.getPlayerNow().addCardsToHand(selectedCards);
    }

    // ok Button
    @FXML
    public void closeWindow() {
        if (selectedCards.size() < Math.min(4, maxSelections)) {
            System.out.println("Select more cards");
        } else {
            selectCards();
            Stage stage = (Stage) okButton.getScene().getWindow();
            playerDeck.removeFromMainDeck(selectedCards);
            selectedCards.clear();
            stage.close();
        }
    }
}
