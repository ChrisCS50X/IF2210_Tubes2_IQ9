package com.istiqomah.if2210_tb2_iq9;

import com.istiqomah.if2210_tb2_iq9.model.card.*;
import com.istiqomah.if2210_tb2_iq9.model.toko.OnDisplayClosedListener;
import com.istiqomah.if2210_tb2_iq9.model.toko.Toko;
import com.istiqomah.if2210_tb2_iq9.model.player.Player;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Map;

// Controller untuk menampilkan toko
public class TokoController {

    // Mendeklarasikan elemen-elemen yang ada di antarmuka pengguna
    @FXML
    public GridPane cardSellsDeck; // Dek kartu yang dijual

    @FXML
    public GridPane sellsBox; // Kotak penjualan

    @FXML
    public Button backButton; // Tombol kembali

    @FXML
    private GridPane cardProducts; // Produk kartu

    // Mendeklarasikan variabel-variabel yang dibutuhkan
    private Toko toko; // Toko
    private Player player; // Pemain
    private OnDisplayClosedListener listener; // Listener untuk event penutupan tampilan

    // Metode untuk inisialisasi
    @FXML
    public void initialize() {
        // Mengatur aksi untuk tombol kembali
        backButton.setOnAction(event -> goBackToMainPage());
    }

    // Metode untuk mengatur listener penutupan tampilan
    public void setOnTokoClosedListener(OnDisplayClosedListener listener) {
        this.listener = listener;
    }

    // Metode untuk mengatur toko
    public void setToko(Toko toko) {
        this.toko = toko;
        loadProducts(); // Memuat produk
    }

    // Metode untuk mengatur pemain
    public void setPlayer(Player player) {
        this.player = player;
    }

    // Metode untuk memuat produk
    public void loadProducts() {
        // Menghapus semua anak dari cardProducts
        cardProducts.getChildren().clear();
        int row = 0;
        int column = 0;
        // Iterasi melalui semua produk yang tersedia di toko
        for (Map.Entry<Card, Integer> entry : toko.getAvailableProducts().entrySet()) {
            try {
                // Membuat loader dan memuat Pane baru
                FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/CardToko.fxml"));
                Pane newLoadedPane = loader.load();

                // Mengatur toko dan kartu untuk controller
                CardTokoController cardTokoController = loader.getController();
                cardTokoController.setToko(toko);
                cardTokoController.setCardToko(entry.getKey());

                // Mengatur aksi ketika Pane diklik
                newLoadedPane.setOnMouseClicked(event -> {
                    Card card = entry.getKey();
                    if (card instanceof Product product) {
                        // Jika pemain memiliki cukup uang, beli produk
                        if (player.getGulden() >= product.getPrice()) {
                            player.buyProduct(toko, card);
                            System.out.println("Produk dibeli: " + entry.getKey());
                            System.out.println("Gulden: " + player.getGulden());
                            loadProducts(); // Memuat ulang produk
                            updateCardSellsDeck(); // Memperbarui dek penjualan kartu
                        } else {
                            System.err.println("Uang tidak cukup untuk membeli produk ini.");
                        }
                    }
                });

                // Menambahkan Pane ke cardProducts
                GridPane.setConstraints(newLoadedPane, column, row);
                cardProducts.getChildren().add(newLoadedPane);

                // Mengatur posisi untuk Pane berikutnya
                column++;
                if (column > 3) { // jika Anda ingin 4 item per baris
                    column = 0;
                    row++;
                }
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }

    // Metode untuk memperbarui dek penjualan kartu
    public void updateCardSellsDeck() {
        // Menghapus semua anak dari cardSellsDeck
        cardSellsDeck.getChildren().clear();
        int column = 0;
        // Iterasi melalui semua kartu di tangan pemain
        for (Card card : player.getDeck().getHand()) {
            try {
                if (card == null) {
                    continue;
                }
                // Membuat loader dan memuat Pane baru
                FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/CardIcon.fxml"));
                Pane newLoadedPane = loader.load();

                // Mengatur kartu untuk controller
                CardIconController cardIconController = loader.getController();
                cardIconController.setCard(card);

                // Mengatur aksi ketika Pane diklik
                newLoadedPane.setOnMouseClicked(event -> {
                    if (card instanceof Product product) {
                        // Jual produk
                        player.sellProduct(toko, card);
                        System.out.println("Produk dijual: " + card);
                        System.out.println("Gulden: " + player.getGulden());
                        loadProducts(); // Memuat ulang produk
                        updateCardSellsDeck(); // Memperbarui dek penjualan kartu
                    } else {
                        System.err.println("Kartu bukan produk.");
                    }
                });

                // Menambahkan Pane ke cardSellsDeck
                GridPane.setConstraints(newLoadedPane, column, 0);
                cardSellsDeck.getChildren().add(newLoadedPane);

                column++;
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }

    // Metode untuk kembali ke halaman utama
    @FXML
    private void goBackToMainPage() {
        try {
            // Memuat file FXML untuk halaman utama
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/com/istiqomah/if2210_tb2_iq9/fxml/newMain.fxml"));
            ScrollPane mainPagePane = loader.load();
            mainPagePane.getStylesheets().add(getClass().getResource("css/main.css").toExternalForm());

            // Mengatur pane halaman utama sebagai root dari scene saat ini
            Stage currentScene = (Stage) backButton.getScene().getWindow();
            currentScene.close();

            // Notifikasi bahwa toko telah ditutup
            if (listener != null) {
                listener.onSceneClosed();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}