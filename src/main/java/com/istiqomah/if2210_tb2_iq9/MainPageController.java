package com.istiqomah.if2210_tb2_iq9;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class MainPageController {

    @FXML
    private GridPane ladangGrid;
    @FXML
    private HBox deckAktifBox;

    private Image image = new Image("file:src/main/resources/com/istiqomah/if2210_tb2_iq9/card/image/Hewan/bear.png");

    public void initialize() {
        // Mendapatkan semua node yang merupakan anak dari HBox
        for (Node node : deckAktifBox.getChildren()) {
            if (node instanceof Pane pane) {
                // Lakukan sesuatu dengan setiap Pane di sini
                setupDragSource(pane); // Misalnya, Anda bisa menggunakan setupDragSource() di sini
            }
        }

        // Mendapatkan semua node yang merupakan anak dari GridPane
        for (Node node : ladangGrid.getChildren()) {
            if (node instanceof Pane pane) {
                // Lakukan sesuatu dengan setiap Pane di sini
                setupDragTarget(pane); // Misalnya, Anda bisa menggunakan setupDragTarget() di sini
            }
        }
    }

    // Metode untuk mengatur sumber drag untuk kartu
    private void setupDragSource(Pane source) {
        source.setOnDragDetected(event -> {
            Dragboard db = source.startDragAndDrop(TransferMode.MOVE); // Memulai drag-and-drop dengan mode transfer MOVE
            ClipboardContent content = new ClipboardContent(); // Membuat clipboard content
            content.putImage(image);
            db.setContent(content); // Menetapkan konten ke dragboard
            event.consume(); // Mengkonsumsi event
        });
    }

    // Metode untuk mengatur target drag untuk sel
    private void setupDragTarget(Pane target) {
        target.setOnDragOver(event -> {
            if (event.getGestureSource() != target && event.getDragboard().hasImage()) {
                event.acceptTransferModes(TransferMode.MOVE); // Menerima mode transfer MOVE jika kondisi terpenuhi
            }
            event.consume(); // Mengkonsumsi event
        });

        target.setOnDragDropped(event -> {
            Dragboard db = event.getDragboard();
            if (db.hasImage()) {
                Pane source = (Pane) event.getGestureSource(); // Mendapatkan sumber drag
                ImageView imageView = new ImageView(db.getImage());
                double targetWidth = target.getWidth();
                double targetHeight = target.getHeight();
                imageView.setFitWidth(targetWidth);
                imageView.setFitHeight(targetHeight);
                Pane newPane = new Pane(imageView); // Buat pane baru dengan imageView
                target.getChildren().add(newPane); // Menambahkan pane baru ke target
                setupDragSource(newPane); // Mengatur pane baru sebagai sumber drag
                ((Pane) source.getParent()).getChildren().remove(source); // Menghapus sumber dari parent-nya
                event.setDropCompleted(true); // Menyatakan drop selesai
            } else {
                event.setDropCompleted(false); // Menyatakan drop tidak selesai
            }
            event.consume(); // Mengkonsumsi event
        });
    }
}
