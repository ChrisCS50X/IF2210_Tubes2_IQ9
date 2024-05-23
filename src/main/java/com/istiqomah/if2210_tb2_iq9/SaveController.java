package com.istiqomah.if2210_tb2_iq9;

import com.istiqomah.if2210_tb2_iq9.model.card.Card;
import com.istiqomah.if2210_tb2_iq9.model.card.CardManager;
import com.istiqomah.if2210_tb2_iq9.model.card.Item;
import com.istiqomah.if2210_tb2_iq9.model.cardcollection.Deck;
import com.istiqomah.if2210_tb2_iq9.model.ladang.KomponenPetak;
import com.istiqomah.if2210_tb2_iq9.model.ladang.Ladang;
import com.istiqomah.if2210_tb2_iq9.model.player.Player;
import com.istiqomah.if2210_tb2_iq9.model.save_load.Readconfig;
import com.istiqomah.if2210_tb2_iq9.model.save_load.Pair;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class SaveController {
    private MainPageController mainPageController;

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

    public void setMainPageController(MainPageController mainPageController) {
        this.mainPageController = mainPageController;
    }

    private void handleBackButton() {
        // Implement the action for the back button
        System.out.println("Back button clicked");
        backButton.getScene().getWindow().hide();
    }

    private void handleSaveButton() {
        // Get the folder path from the TextField
        String folderPath = folderTextField.getText();

        // Validate the folder path
        if (folderPath == null || folderPath.isEmpty()) {
            statusLabel.setText("Folder path is empty");
            statusLabel.setVisible(true);
            return;
        }

        // Call method to save the game state
        saveData("src/main/java/com/istiqomah/if2210_tb2_iq9/model/save_load/" + folderPath);

        // Update status label
        statusLabel.setText("State Saved Successfully");
        statusLabel.setVisible(true);
    }

    private void saveData(String folderPath) {
        try {
            // Ensure the folder exists
            File folder = new File(folderPath);
            if (!folder.exists()) {
                folder.mkdirs();  // Create the folder if it doesn't exist
            }

            // Write to config1.txt
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(folderPath + "/config1.txt"))) {
                int gulden = Player.getPlayerByIdx(0).getGulden();
                int jumlahKartu = Player.getPlayerByIdx(0).getDeck().getMainDeckSize();
                int n_hand = 0;
                List<Card> handc = Player.getPlayerByIdx(0).getDeck().getHand();
                for (int i = 0; i < 6; i++){
                    if (handc.get(i) != null ){
                        n_hand++;
                    }
                }


                writer.write(gulden + "\n");
                writer.write(jumlahKartu + "\n");
                writer.write(n_hand + "\n");

                // Write hand cards
                for (int i = 0; i < 6; i++) {
                    Card card = Player.getPlayerByIdx(0).getDeck().getHand().get(i);
                    if (card != null) {
                        String name = card.getName();
                        String lokasi = "A0" + i;
                        writer.write(lokasi + " " + name + "\n");
                    }
                }

                // Write ladang cards
                Ladang ladang = Player.getPlayerByIdx(0).getLadang();
                int count = 0;

                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 5; j++) {
                        KomponenPetak komponen = ladang.getCardAtPosition(i, j);
                        if(komponen != null){
                            count++;
                        }
                    }
                }
                writer.write(count +"\n");
                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 5; j++) {
                        KomponenPetak komponen = ladang.getCardAtPosition(i, j);
                        if (komponen instanceof Card) {
                            Card card = (Card) komponen;
                            if (card != null) {
                                String name = card.getName();
                                String lokasi = convertRowToLetter(i) +"0"+ String.valueOf(j);
                                int beratUmur = card.getBerat_Umur();
                                List<Item> items = card.getActiveItems();
                                int jumlahItem = items.size();

                                writer.write(lokasi + " " + name + " " + beratUmur + " " + jumlahItem);
                                for (Item item : items) {
                                    writer.write(" " + item.getName());
                                }
                                writer.write("\n");
                            }
                        }
                    }
                }
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(folderPath + "/config2.txt"))) {
                int gulden = Player.getPlayerByIdx(1).getGulden();
                int jumlahKartu = Player.getPlayerByIdx(1).getDeck().getMainDeckSize();
                int n_hand = 0;
                List<Card> handc = Player.getPlayerByIdx(1).getDeck().getHand();
                for (int i = 0; i < 6; i++){
                    if (handc.get(i) != null ){
                        n_hand++;
                    }
                }


                writer.write(gulden + "\n");
                writer.write(jumlahKartu + "\n");
                writer.write(n_hand + "\n");

                // Write hand cards
                for (int i = 0; i < 6; i++) {
                    Card card = Player.getPlayerByIdx(1).getDeck().getHand().get(i);
                    if (card != null) {
                        String name = card.getName();
                        String lokasi = "A0" + i;
                        writer.write(lokasi + " " + name + "\n");
                    }
                }

                // Write ladang cards
                Ladang ladang = Player.getPlayerByIdx(1).getLadang();
                int count = 0;

                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 5; j++) {
                        KomponenPetak komponen = ladang.getCardAtPosition(i, j);
                        if(komponen != null){
                            count++;
                        }
                    }
                }
                writer.write(count+"\n");

                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 5; j++) {
                        KomponenPetak komponen = ladang.getCardAtPosition(i, j);
                        if (komponen instanceof Card) {
                            Card card = (Card) komponen;
                            if (card != null) {

                                String name = card.getName();
                                String lokasi = convertRowToLetter(i) +"0"+ String.valueOf(j);
                                int beratUmur = card.getBerat_Umur();
                                List<Item> items = card.getActiveItems();
                                int jumlahItem = items.size();

                                writer.write(lokasi + " " + name + " " + beratUmur + " " + jumlahItem);
                                for (Item item : items) {
                                    writer.write(" " + item.getName());
                                }
                                writer.write("\n");
                            }
                        }
                    }
                }
            }

            // Write to gamestate.txt
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(folderPath + "/gamestate.txt"))) {
                int turn = mainPageController.TurnNow;
                Map<Card, Integer> shopItems = mainPageController.toko.getAvailableProducts();
                int jumlahItemShop = shopItems.size();

                writer.write(turn + "\n");
                writer.write(jumlahItemShop + "\n");

                for (Map.Entry<Card, Integer> item : shopItems.entrySet()) {
                    Card card = item.getKey();
                    if (card != null) {
                        writer.write(card.getName() + " " + item.getValue() + "\n");
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String convertRowToLetter(int row) {
        return Character.toString((char) ('A' + row));
    }
}
