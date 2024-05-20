package com.istiqomah.if2210_tb2_iq9;

import com.istiqomah.if2210_tb2_iq9.model.cardcollection.Deck;
import com.istiqomah.if2210_tb2_iq9.model.player.Player;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class MainPageController {
    public int TurnNow = 1;
    @FXML
    private GridPane ladangGrid;
    @FXML
    private HBox deckAktifBox;
    @FXML
    private Text turnText;
    @FXML
    private Button nextButton;
    @FXML
    private Label player1Label;
    @FXML
    private Label player2Label;
    @FXML
    private ImageView gold1;
    @FXML
    private ImageView gold2;
    @FXML
    private Button ladangkuButton;
    @FXML
    private Button ladangLawanButton;
    @FXML
    private Button tokoButton;
    @FXML
    private Button saveStateButton;
    @FXML
    private Button loadStateButton;
    @FXML
    private Button loadPluginButton;
    @FXML
    private Label deckLabel;

    private Image image = new Image("file:src/main/resources/com/istiqomah/if2210_tb2_iq9/card/image/Hewan/bear.png");
    private Image gold = new Image("file:src/main/resources/com/istiqomah/if2210_tb2_iq9/card/image/Icon/gold.png");
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

        // mengatur giliran pemain
        turnText.setText("Turn " + TurnNow);

        // set button next turn
        nextTurn();

        // set gulden player
        player1Label.setText("Player 1:        " + Player.getPlayerByIdx(0).getGulden());
        player2Label.setText("Player 2:        " + Player.getPlayerByIdx(1).getGulden());

        // set image gold

        gold1.setImage(gold);
        gold2.setImage(gold);
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

    private void nextTurn() {
        nextButton.setOnAction(event -> {

            TurnNow++;
            turnText.setText("Turn " + TurnNow);
            Player.nextTurn();
        });
    }

    private void ladangku() {
        ladangkuButton.setOnAction(event -> {
//            deckLabel.setText("Ladangku");
//            deckAktifBox.getChildren().clear();
            Deck deck = Player.getPlayerNow().getDeck();
//            for (int i = 0; i < deck.size() ; i++) {
//                Pane pane = new Pane(new ImageView(image));
//                deckAktifBox.getChildren().add(pane);
//                setupDragSource(pane);
//            }
        });
    }

    private void ladangLawan() {
        ladangLawanButton.setOnAction(event -> {
//            deckLabel.setText("Ladang Lawan");
//            deckAktifBox.getChildren().clear();
            Deck deck = Player.getPlayerEnemy().getDeck();
//            for (int i = 0; i < deck.size() ; i++) {
//                Pane pane = new Pane(new ImageView(image));
//                deckAktifBox.getChildren().add(pane);
//                setupDragSource(pane);
//            }
        });
    }
}
