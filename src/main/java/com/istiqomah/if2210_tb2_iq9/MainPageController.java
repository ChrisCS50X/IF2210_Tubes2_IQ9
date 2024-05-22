package com.istiqomah.if2210_tb2_iq9;

import com.istiqomah.if2210_tb2_iq9.model.card.Card;
import com.istiqomah.if2210_tb2_iq9.model.card.CardManager;
import com.istiqomah.if2210_tb2_iq9.model.cardcollection.Deck;
import com.istiqomah.if2210_tb2_iq9.model.ladang.KomponenPetak;
import com.istiqomah.if2210_tb2_iq9.model.player.Player;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
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
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.List;

public class MainPageController {
    public int TurnNow = 1;
    @FXML
    private GridPane ladangGrid;
    @FXML
    private GridPane deckAktifBox;
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
        setDeckAktifPlayer();
        setLadangPlayer(Player.getPlayerNow());
        // Mendapatkan semua node yang merupakan anak dari GridPane
        for (Node node : ladangGrid.getChildren()) {
            if (node instanceof Pane pane) {
                setupDragTarget(pane);
            }
        }

        // mengatur giliran pemain
        turnText.setText("Turn " + TurnNow);

        // set button next turn
        nextTurn();

        // set button ladangku
        ladangku();

        // set button ladang lawan
        ladangLawan();

        // set gulden player
        player1Label.setText("Player 1:        " + Player.getPlayerByIdx(0).getGulden());
        player2Label.setText("Player 2:        " + Player.getPlayerByIdx(1).getGulden());

        // set image gold

        gold1.setImage(gold);
        gold2.setImage(gold);
    }

    // Metode untuk mengatur sumber drag untuk kartu
    private void setupDragSource(Pane source, Card card) {
        source.setOnDragDetected(event -> {
            Dragboard db = source.startDragAndDrop(TransferMode.MOVE); // Memulai drag-and-drop dengan mode transfer MOVE
            ClipboardContent content = new ClipboardContent(); // Membuat clipboard content
            content.putImage(card.getImage()); // Menetapkan gambar kartu ke clipboard content
            source.setUserData(card); // Simpan data kartu di panel sumber
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
                Pane source = (Pane) event.getGestureSource();
                Card newCard = (Card) source.getUserData();
                Pane newPane = createCardPane(newCard);
                target.getChildren().add(newPane);
                setupDragSource(newPane, newCard); // Mengatur pane baru sebagai sumber drag dengan gambar yang sama
                ((Pane) source.getParent()).getChildren().remove(source); // Menghapus sumber dari parent-nya
                event.setDropCompleted(true); // Menyatakan drop selesai
            } else {
                event.setDropCompleted(false); // Menyatakan drop tidak selesai
            }
            event.consume(); // Mengkonsumsi event
        });
    }

    // Metode untuk membersihkan kartu dari grid ladang tanpa menghapus pane wadah
    private void clearGrid(GridPane grid) {
        for (Node node : grid.getChildren()) {
            if (node instanceof Pane) {
                if (((Pane) node).getChildren().size() > 0)
                {
                    ((Pane) node).getChildren().clear();
                }
                node.setStyle("-fx-border-color: grey;");
            }
        }
    }

    private void setDeckAktifPlayer() {
        List<Card> deck = Player.getPlayerNow().getDeck().getHand();
        clearGrid(deckAktifBox);

        for (int i = 0; i < deck.size(); i++) {
            Card card = deck.get(i);
            Pane cardPane = createCardPane(card);
            deckAktifBox.add(cardPane, i, 0);
            setupDragSource(cardPane, card);
        }
    }

    private void setLadangPlayer(Player p)
    {
        clearGrid(ladangGrid);
        KomponenPetak[][] ladang = p.getLadang().getGrid();
        for (int i = 0; i < 4 ; i++) {
            for (int j = 0; j < 5 ; j++) {
                if (ladang[i][j] != null)
                {
                    Card card = (Card) ladang[i][j];
                    Pane cardPane = createCardPane(card);
                    ladangGrid.add(cardPane, j, i);
                    setupDragTarget(cardPane);
                    setupDragSource(cardPane, card);
                } else {
                    Pane cardPane = new Pane();
                    cardPane.setPrefSize(100, 100);
                    cardPane.setStyle("-fx-border-color: grey;");
                    ladangGrid.add(cardPane, j, i);
                    setupDragTarget(cardPane);
                }
            }
        }
    }

    private Pane createCardPane(Card card) {
        VBox cardPane = new VBox();
        cardPane.setPrefSize(100, 100);
        cardPane.setAlignment(Pos.CENTER);
        cardPane.setStyle("-fx-border-color: black; -fx-background-color: lightgrey;");

        ImageView imageView = new ImageView(card.getImage());
        imageView.setFitWidth(80);
        imageView.setFitHeight(80);

        Text cardName = new Text(card.getName());

        cardPane.getChildren().addAll(imageView, cardName);

        setupDragSource(cardPane, card);

        return cardPane;
    }

    private void nextTurn() {
        nextButton.setOnAction(event -> {

            TurnNow++;
            turnText.setText("Turn " + TurnNow);
            Player.nextTurn();
            setDeckAktifPlayer();
            setLadangPlayer(Player.getPlayerNow());
        });
    }

    private void ladangku() {
        ladangkuButton.setOnAction(event -> {
            setLadangPlayer(Player.getPlayerNow());
        });
    }

    private void ladangLawan() {
        ladangLawanButton.setOnAction(event -> {
            setLadangPlayer(Player.getPlayerEnemy());
        });
    }
}
