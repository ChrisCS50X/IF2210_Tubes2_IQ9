package com.istiqomah.if2210_tb2_iq9;

import com.istiqomah.if2210_tb2_iq9.model.card.Card;
import com.istiqomah.if2210_tb2_iq9.model.card.Item;
import com.istiqomah.if2210_tb2_iq9.model.ladang.KomponenPetak;
import com.istiqomah.if2210_tb2_iq9.model.ladang.Ladang;
import com.istiqomah.if2210_tb2_iq9.model.player.Player;

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
    private MainPage mainPage;

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

    // Mengatur controller untuk halaman utama
    public void setMainPageController(MainPageController mainPageController) {
        this.mainPageController = mainPageController;
    }

    // Mengatur halaman utama
    public void setMainPage(MainPage mainPage) {
        this.mainPage = mainPage;
    }

    // Menangani aksi tombol kembali
    private void handleBackButton() {
        // Implementasi ketika tombol kembali ditekan
        System.out.println("Back button clicked");
        backButton.getScene().getWindow().hide();
    }

    // Menangani aksi tombol simpan
    private void handleSaveButton() {
        // Dapatkan path folder dari TextField
        String folderPath = folderTextField.getText();

        // Validasi path folder
        if (folderPath == null || folderPath.isEmpty()) {
            statusLabel.setText("Folder path is empty");
            statusLabel.setVisible(true);
            return;
        }

        // Panggil metode saveData pada controller halaman utama
        saveData("src/main/java/com/istiqomah/if2210_tb2_iq9/model/save_load/" + folderPath);

        // Perbarui label status
        statusLabel.setText("State Saved Successfully");
        statusLabel.setVisible(true);
    }

    // Metode untuk menyimpan data
    private void saveData(String folderPath) {
        try {
            // Memastikan keberadaan folder ada
            File folder = new File(folderPath);
            if (!folder.exists()) {
                folder.mkdirs(); // Buat folder jika belum ada
            }

            // Tulis ke config1.txt
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(folderPath + "/config1.txt"))) {
                int gulden = Player.getPlayerByIdx(0).getGulden();
                int jumlahKartu = Player.getPlayerByIdx(0).getDeck().getMainDeckSize();
                int n_hand = 0;
                List<Card> handc = Player.getPlayerByIdx(0).getDeck().getHand();
                for (int i = 0; i < 6; i++) {
                    if (handc.get(i) != null) {
                        n_hand++;
                    }
                }

                writer.write(gulden + "\n");
                writer.write(jumlahKartu + "\n");
                writer.write(n_hand + "\n");

                // Tulis kartu untuk jadi hand cards
                for (int i = 0; i < 6; i++) {
                    Card card = Player.getPlayerByIdx(0).getDeck().getHand().get(i);
                    if (card != null) {
                        String name = card.getName();
                        String lokasi = "A0" + i;
                        writer.write(lokasi + " " + name + "\n");
                    }
                }

                // Tulis kartu untuk jadi ladang cards
                Ladang ladang = Player.getPlayerByIdx(0).getLadang();
                int count = 0;

                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 5; j++) {
                        KomponenPetak komponen = ladang.getCardAtPosition(i, j);
                        if (komponen != null) {
                            count++;
                        }
                    }
                }
                writer.write(count + "\n");
                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 5; j++) {
                        KomponenPetak komponen = ladang.getCardAtPosition(i, j);
                        if (komponen instanceof Card) {
                            Card card = (Card) komponen;
                            if (card != null) {
                                String name = card.getName();
                                String lokasi = convertRowToLetter(i) + "0" + String.valueOf(j);
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
                for (int i = 0; i < 6; i++) {
                    if (handc.get(i) != null) {
                        n_hand++;
                    }
                }

                writer.write(gulden + "\n");
                writer.write(jumlahKartu + "\n");
                writer.write(n_hand + "\n");

                // Tulis hand cards
                for (int i = 0; i < 6; i++) {
                    Card card = Player.getPlayerByIdx(1).getDeck().getHand().get(i);
                    if (card != null) {
                        String name = card.getName();
                        String lokasi = "A0" + i;
                        writer.write(lokasi + " " + name + "\n");
                    }
                }

                // Tulis ladang cards
                Ladang ladang = Player.getPlayerByIdx(1).getLadang();
                int count = 0;

                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 5; j++) {
                        KomponenPetak komponen = ladang.getCardAtPosition(i, j);
                        if (komponen != null) {
                            count++;
                        }
                    }
                }
                writer.write(count + "\n");

                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 5; j++) {
                        KomponenPetak komponen = ladang.getCardAtPosition(i, j);
                        if (komponen instanceof Card) {
                            Card card = (Card) komponen;
                            if (card != null) {

                                String name = card.getName();
                                String lokasi = convertRowToLetter(i) + "0" + String.valueOf(j);
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

            // Tulis gamestate.txt
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(folderPath + "/gamestate.txt"))) {
                int turn = mainPageController.TurnNow;
                Map<Card, Integer> shopItems = mainPage.toko.getAvailableProducts();
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

    // Metode untuk mengubah baris menjadi huruf
    private String convertRowToLetter(int row) {
        return Character.toString((char) ('A' + row));
    }
}
