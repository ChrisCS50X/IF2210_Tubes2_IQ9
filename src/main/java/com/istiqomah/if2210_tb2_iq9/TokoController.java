package com.istiqomah.if2210_tb2_iq9;

import com.istiqomah.if2210_tb2_iq9.model.card.*;
import com.istiqomah.if2210_tb2_iq9.model.toko.Toko;
import com.istiqomah.if2210_tb2_iq9.model.player.Player;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.Map;

public class TokoController {

    @FXML
    public GridPane cardSellsDeck;

    @FXML
    public GridPane sellsBox;

    @FXML
    public Button backButton;

    @FXML
    private GridPane cardProducts;

    private Toko toko;
    private Player player;

    @FXML
    public void initialize() {
        backButton.setOnAction(event -> goBackToMainPage());
    }

    public void setToko(Toko toko) {
        this.toko = toko;
        loadProducts();
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void loadProducts() {
        cardProducts.getChildren().clear();
        int row = 0;
        int column = 0;
        for (Map.Entry<Card, Integer> entry : toko.getAvailableProducts().entrySet()) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/CardToko.fxml"));
                Pane newLoadedPane = loader.load();

                CardTokoController cardTokoController = loader.getController();
                cardTokoController.setToko(toko);
                cardTokoController.setCardToko(entry.getKey());

                newLoadedPane.setOnMouseClicked(event -> {
                    Card card = entry.getKey();
                    if (card instanceof Product product){
                        if (player.getGulden() >= product.getPrice()) {
                            player.buyProduct(toko, card);
                            System.out.println("Product bought: " + entry.getKey());
                            System.out.println("Gulden: " + player.getGulden());
                            loadProducts();
                            updateCardSellsDeck();
                        }
                        else
                        {
                            System.out.println("Not enough money to buy this product.");
                        }
                    }
                });

                GridPane.setConstraints(newLoadedPane, column, row);
                cardProducts.getChildren().add(newLoadedPane);

                column++;
                if (column > 3) { // if you want 4 items per row
                    column = 0;
                    row++;
                }
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }

    public void updateCardSellsDeck() {
        cardSellsDeck.getChildren().clear();
        int column = 0;
        for (Card card : player.getDeck().getHand()) {
            try {
                if (card == null) {
                    continue;
                }
                FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/CardIcon.fxml"));
                Pane newLoadedPane = loader.load();

                // Set the maximum size of the Pane to allow it to fill the cell
                newLoadedPane.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

                CardIconController cardIconController = loader.getController();
                cardIconController.setCard(card);

                newLoadedPane.setOnMouseClicked(event -> {
                    if (card instanceof Product product){
                        player.sellProduct(toko,card);
                        System.out.println("Product sold: " + card);
                        System.out.println("Gulden: " + player.getGulden());
                        loadProducts();
                        updateCardSellsDeck();
                    }
                    else {
                        System.out.println("Card is not a product.");
                    }
                });

                GridPane.setConstraints(newLoadedPane, column, 0);
                cardSellsDeck.getChildren().add(newLoadedPane);

                column++;
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }

    @FXML
    private void goBackToMainPage() {
        try {
            // Load the FXML file for the main page
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/istiqomah/if2210_tb2_iq9/fxml/newMain.fxml"));
            ScrollPane mainPagePane = loader.load();
            mainPagePane.getStylesheets().add(getClass().getResource("css/main.css").toExternalForm());

            // Set the main page pane as the root of the current scene
            Scene currentScene = backButton.getScene();
            currentScene.setRoot(mainPagePane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}