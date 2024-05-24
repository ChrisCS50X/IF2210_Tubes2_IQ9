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
    public AnchorPane cardToko; // AnchorPane untuk kartu toko

    @FXML
    public Pane cardholder; // Pane untuk pemegang kartu

    @FXML
    private Label hargainput; // Label untuk input harga

    @FXML
    private Label jumlahinput; // Label untuk input jumlah

    private Toko toko; // Objek Toko

    public CardTokoController() {
    }

    @FXML
    public void initialize() {
    }

    // Metode untuk mengatur kartu toko
    public void setCardToko(Card card) {
        try {
            // Membuat loader untuk memuat fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/CardIcon.fxml"));
            // Memuat pane baru
            Pane newLoadedPane = loader.load();

            // Menambahkan pane baru ke pemegang kartu
            cardholder.getChildren().add(newLoadedPane);
            // Mendapatkan controller untuk ikon kartu
            CardIconController cardIconController = loader.getController();
            // Mengatur kartu untuk controller ikon kartu
            cardIconController.setCard(card);
            // Jika kartu adalah produk
            if (card instanceof Product) {
                Product product = (Product) card;
                // Mengatur harga dan jumlah produk
                setPrice(String.valueOf(product.getPrice()));
                setQuantity(String.valueOf(toko.getAvailableProducts().get(card)));
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    // Metode untuk mengatur toko
    public void setToko(Toko toko) {
        this.toko = toko;
    }

    // Metode untuk mengatur harga
    private void setPrice(String price) {
        hargainput.setText(price);
    }

    // Metode untuk mengatur jumlah
    private void setQuantity(String quantity) {
        jumlahinput.setText(quantity);
    }
}