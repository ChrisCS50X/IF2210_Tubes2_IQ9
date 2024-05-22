package com.istiqomah.if2210_tb2_iq9;

import com.istiqomah.if2210_tb2_iq9.model.card.Animal;
import com.istiqomah.if2210_tb2_iq9.model.card.Card;
import com.istiqomah.if2210_tb2_iq9.model.card.Item;
import com.istiqomah.if2210_tb2_iq9.model.card.Plant;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CardController {

    @FXML
    private Text namaCardText;

    @FXML
    private Label umurLabel;

    @FXML
    private Label itemAktifLabel;

    @FXML
    private ImageView cardImage;

    @FXML
    private Button kembaliButton;

    @FXML
    private Button panenButton;

    public void setCard(Card card) {
        String type = card.getType();
        StringBuilder activeItems = new StringBuilder();
        if (card instanceof Animal) {
            System.out.println("Animal");
            Animal animal = (Animal) card;
            namaCardText.setText(animal.getName());
            umurLabel.setText("Berat : " + animal.getWeight() + " (" + animal.getHarvestWeight() + ")");

            // Map untuk menyimpan item dan jumlah kemunculannya
            Map<String, Integer> itemCounts = new HashMap<>();
            for (Item item : animal.getActiveItems()) {
                itemCounts.put(item.getName(), itemCounts.getOrDefault(item.getName(), 0) + 1);
            }

            // Membuat string untuk menampilkan item dan jumlah kemunculanny
            for (Map.Entry<String, Integer> entry : itemCounts.entrySet()) {
                activeItems.append(entry.getKey()).append(" (").append(entry.getValue()).append("), ");
            }

            // Menghapus koma dan spasi terakhir
            if (!activeItems.isEmpty()) {
                activeItems.setLength(activeItems.length() - 2);
                itemAktifLabel.setText("Item aktif : " + activeItems.toString());
            }
            else {
                itemAktifLabel.setText("Item aktif : Tidak ada item aktif");
            }

            if (animal.getWeight() >= animal.getHarvestWeight()) {
                panenButton.setDisable(false);
            } else {
                panenButton.setDisable(true);
            }

        }
        else if (card instanceof Plant) {
            System.out.println("Plant");
            Plant plant = (Plant) card;
            namaCardText.setText(card.getName());
            umurLabel.setText("Umur : " + plant.getAge() +" (" + plant.getHarvestDuration() + ")");

            // Map untuk menyimpan item dan jumlah kemunculannya
            Map<String, Integer> itemCounts = new HashMap<>();
            for (Item item : plant.getActiveItems()) {
                itemCounts.put(item.getName(), itemCounts.getOrDefault(item.getName(), 0) + 1);
            }

            // Membuat string untuk menampilkan item dan jumlah kemunculannya

            for (Map.Entry<String, Integer> entry : itemCounts.entrySet()) {
                activeItems.append(entry.getKey()).append(" (").append(entry.getValue()).append("), ");
            }

            // Menghapus koma dan spasi terakhir
            if (!activeItems.isEmpty()) {
                activeItems.setLength(activeItems.length() - 2);
                itemAktifLabel.setText("Item aktif : " + activeItems.toString());
            }
            else {
                itemAktifLabel.setText("Item aktif : Tidak ada item aktif");
            }

            if (plant.getAge() >= plant.getHarvestDuration()) {
                panenButton.setDisable(false);
            } else {
                panenButton.setDisable(true);
            }
        }

        // Assuming you have a method to get the image from the card
        cardImage.setImage(card.getImage());
    }

    @FXML
    private void initialize() {
        kembaliButton.setOnAction(event -> goBack());
    }

    private void goBack() {
        Stage stage = (Stage) kembaliButton.getScene().getWindow();
        stage.close();
    }
}
