package com.istiqomah.if2210_tb2_iq9;

import com.istiqomah.if2210_tb2_iq9.model.save_load.Pair;
import com.istiqomah.if2210_tb2_iq9.model.save_load.Triple;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.util.ArrayList;
import com.istiqomah.if2210_tb2_iq9.model.save_load.Readconfig;


public class LoadController {

    @FXML
    private Button backButton;

    @FXML
    private ComboBox<String> formatComboBox;

    @FXML
    private TextField folderTextField;

    @FXML
    private Button loadButton;

    @FXML
    private Label statusLabel;

    @FXML
    private void initialize() {
        backButton.setOnAction(event -> handleBackButton());
        loadButton.setOnAction(event -> handleLoadButton());
    }

    private void handleBackButton() {
        // Implement the action for the back button
        System.out.println("Back button clicked");
    }

    private void handleLoadButton() {
        // Get the folder path from the TextField
        String folderPath = folderTextField.getText();

        // Validate the folder path (optional)
        if (folderPath == null || folderPath.isEmpty()) {
            System.out.println("Folder path is empty");
            return;
        }

        // Create an instance of Readconfig with the folder path
        Readconfig config = new Readconfig("src/main/java/com/istiqomah/if2210_tb2_iq9/model/save_load/"+folderPath);
        System.out.println("Jumlah Gulden: " + config.getJumlahGulden());
        System.out.println("Jumlah Deck: " + config.getJumlahDeck());

        System.out.println("Kordinat Card:");
        for (Triple<String, Integer, String> card : config.getKordinatCard()) {
            System.out.println(card.getLeft() + " " + card.getMiddle() + " " + card.getRight());
        }

        System.out.println("Kordinat Ladang:");
        for (Triple<String, Integer, String> ladang : config.getKordinatLadang()) {
            System.out.println(ladang.getLeft() + " " + ladang.getMiddle() + " " + ladang.getRight());
        }
        // Assuming successful load, show the status label
        statusLabel.setVisible(true);
        System.out.println("Load button clicked with folder: " + folderPath);

        System.out.println("Items:");
        for (ArrayList<String> items : config.getItem()) {
            for (String item : items) {
                System.out.print(item + " ");
            }
            System.out.println();
        }

        System.out.println("Current Turn: " + config.getCurrentTurn());
        System.out.println("Jumlah Item di Shop: " + config.getJumlahItemShop());

        System.out.println("Item Shop:");
        for (Pair<String, Integer> item : config.getItemShop()) {
            System.out.println(item.getLeft() + " " + item.getRight());
        }
    }
}
