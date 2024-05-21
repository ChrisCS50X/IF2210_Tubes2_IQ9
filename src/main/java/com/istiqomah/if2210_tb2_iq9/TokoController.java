package com.istiqomah.if2210_tb2_iq9;

import com.istiqomah.if2210_tb2_iq9.model.card.*;
import com.istiqomah.if2210_tb2_iq9.model.toko.Toko;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.Map;

public class TokoController {

    @FXML
    private GridPane cardProducts;

    private Toko toko;

    @FXML
    public void initialize() {
    }

    public void setToko(Toko toko) {
        this.toko = toko;
        loadProducts();
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
                    // Implement your buying logic here
                    System.out.println("Product bought: " + entry.getKey());
                    toko.removeProduct(entry.getKey());
                    loadProducts(); // Refresh the products
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
}