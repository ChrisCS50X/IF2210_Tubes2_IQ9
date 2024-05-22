package com.istiqomah.if2210_tb2_iq9;

import com.istiqomah.if2210_tb2_iq9.model.card.Card;
import com.istiqomah.if2210_tb2_iq9.model.card.CardManager;
import com.istiqomah.if2210_tb2_iq9.model.cardcollection.Deck;
import com.istiqomah.if2210_tb2_iq9.model.ladang.KomponenPetak;
import com.istiqomah.if2210_tb2_iq9.model.player.Player;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
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
import com.istiqomah.if2210_tb2_iq9.model.ladang.Ladang;
import javafx.util.Duration;

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

    @FXML
    private Label bearAttackLabel;

    @FXML
    private Label timerLabel;

    private Timeline timeline;
    private Ladang ladang;
    private int timeRemaining; // in tenths of a second

    @FXML
    private void initialize() {
        ladang = new Ladang();
        setDeckAktifPlayer();
        setLadangPlayer(Player.getPlayerNow());
        // Mendapatkan semua node yang merupakan anak dari GridPane
        for (Node node : ladangGrid.getChildren()) {
            if (node instanceof Pane pane) {
                setupDragTarget(pane);
            }
        }

        turnText.setText("Turn " + TurnNow);

        nextTurn();

<<<<<<< Updated upstream
        // set button ladangku
        ladangku();

        // set button ladang lawan
        ladangLawan();

        // set gulden player
        player1Label.setText("Player 1:        " + Player.getPlayerByIdx(0).getGulden());
        player2Label.setText("Player 2:        " + Player.getPlayerByIdx(1).getGulden());

        // set image gold
=======
        // Set gulden player
        player1Label.setText("Player 1: " + Player.getPlayerByIdx(0).getGulden());
        player2Label.setText("Player 2: " + Player.getPlayerByIdx(1).getGulden());
>>>>>>> Stashed changes

        // Set image gold
        gold1.setImage(gold);
        gold2.setImage(gold);

        // Initialize bear attack UI elements
        bearAttackLabel.setText("");
        timerLabel.setText("");
    }

<<<<<<< Updated upstream
    // Metode untuk mengatur sumber drag untuk kartu
    private void setupDragSource(Pane source, Card card) {
        source.setOnDragDetected(event -> {
            Dragboard db = source.startDragAndDrop(TransferMode.MOVE); // Memulai drag-and-drop dengan mode transfer MOVE
            ClipboardContent content = new ClipboardContent(); // Membuat clipboard content
            content.putImage(card.getImage()); // Menetapkan gambar kartu ke clipboard content
            source.setUserData(card); // Simpan data kartu di panel sumber
            db.setContent(content); // Menetapkan konten ke dragboard
            event.consume(); // Mengkonsumsi event
=======
    private void initializeSerangan() {
        ladang.initiateBearAttack();

        List<int[]> attackPositions = ladang.getCurrentBear().getAttackPositions();
        bearAttackLabel.setText("Bear is attacking at multiple positions!");
        timeRemaining = ladang.getCurrentBear().getDuration() * 10; // Convert to tenths of a second
        updateTimerLabel();

        highlightAttackZone(attackPositions);

        // Disable the next turn, ladang lawan, and toko buttons during bear attack
        nextButton.setDisable(true);
        ladangLawanButton.setDisable(true);
        tokoButton.setDisable(true);

        // Start the timer
        Thread timerThread = new Thread(() -> {
            try {
                while (timeRemaining > 0) {
                    Thread.sleep(100); // Update every 0.1 seconds
                    timeRemaining--;
                    Platform.runLater(this::updateTimerLabel);
                }
                Platform.runLater(this::handleBearAttackEnd);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        timerThread.start();
    }

    private void updateTimerLabel() {
        int seconds = timeRemaining / 10;
        timerLabel.setText("Time remaining: " + seconds + "s");
    }

    private void handleBearAttackEnd() {
        System.out.println("Bear attack ended. Plants/Animals in the attack zone are removed.");
        List<int[]> attackPositions = ladang.getCurrentBear().getAttackPositions();
        for (int[] position : attackPositions) {
            int x = position[0];
            int y = position[1];
            if (x >= 0 && x < 5 && y >= 0 && y < 4 && ladang.getCardAtPosition(x, y) != null) {
                ladang.removeCardFromPosition(x, y);
            }
        }
        updateGridUI();
        bearAttackLabel.setText("");
        timerLabel.setText("");
        clearHighlight();

        // Re-enable the next turn, ladang lawan, and toko buttons after bear attack
        nextButton.setDisable(false);
        ladangLawanButton.setDisable(false);
        tokoButton.setDisable(false);
    }

    @FXML
    private void handleMove() {
        System.out.println("Move plant/animal clicked");
    }

    @FXML
    private void handleTrap() {
        System.out.println("Use trap clicked");
    }

    private void updateGridUI() {
        for (Node node : ladangGrid.getChildren()) {
            if (node instanceof Pane pane) {
                Integer col = GridPane.getColumnIndex(pane);
                Integer row = GridPane.getRowIndex(pane);
                if (col != null && row != null) {
                    if (col >= 0 && col < 5 && row >= 0 && row < 4) {
                        Card card = (Card) ladang.getCardAtPosition(col, row);
                        pane.getChildren().clear();
                        if (card != null) {
                            ImageView imageView = new ImageView(card.getImage());
                            imageView.setFitWidth(pane.getWidth());
                            imageView.setFitHeight(pane.getHeight());
                            pane.getChildren().add(imageView);
                        }
                    }
                }
            }
        }
    }

    private void highlightAttackZone(List<int[]> positions) {
        for (Node node : ladangGrid.getChildren()) {
            if (node instanceof Pane pane) {
                Integer col = GridPane.getColumnIndex(pane);
                Integer row = GridPane.getRowIndex(pane);
                if (col != null && row != null) {
                    for (int[] position : positions) {
                        if (col == position[0] && row == position[1]) {
                            pane.setStyle("-fx-border-color: red; -fx-background-color: #ffcccc;");
                        }
                    }
                }
            }
        }
    }

    private void clearHighlight() {
        for (Node node : ladangGrid.getChildren()) {
            if (node instanceof Pane pane) {
                pane.setStyle("-fx-border-color: grey;");
            }
        }
    }

    private Image image = new Image("file:src/main/resources/com/istiqomah/if2210_tb2_iq9/card/image/Hewan/bear.png");
    private Image gold = new Image("file:src/main/resources/com/istiqomah/if2210_tb2_iq9/card/image/Icon/gold.png");

    private void setupDragSource(Pane source, Image cardImage) {
        source.setOnDragDetected(event -> {
            Dragboard db = source.startDragAndDrop(TransferMode.MOVE);
            ClipboardContent content = new ClipboardContent();
            content.putImage(cardImage);
            db.setContent(content);
            event.consume();
>>>>>>> Stashed changes
        });
    }

    private void setupDragTarget(Pane target) {
        target.setOnDragOver(event -> {
            if (event.getGestureSource() != target && event.getDragboard().hasImage()) {
            }
        });

        target.setOnDragDropped(event -> {
            Dragboard db = event.getDragboard();
            if (db.hasImage()) {
                Pane source = (Pane) event.getGestureSource();
<<<<<<< Updated upstream
                Card newCard = (Card) source.getUserData();
                Pane newPane = createCardPane(newCard);
                target.getChildren().add(newPane);
                setupDragSource(newPane, newCard); // Mengatur pane baru sebagai sumber drag dengan gambar yang sama
                ((Pane) source.getParent()).getChildren().remove(source); // Menghapus sumber dari parent-nya
                event.setDropCompleted(true); // Menyatakan drop selesai
=======
                ImageView imageView = new ImageView(db.getImage());
                double targetWidth = target.getWidth();
                double targetHeight = target.getHeight();
                imageView.setFitWidth(targetWidth);
                imageView.setFitHeight(targetHeight);
                Pane newPane = new Pane(imageView);
                target.getChildren().add(newPane);
                setupDragSource(newPane, db.getImage());
                ((Pane) source.getParent()).getChildren().remove(source);
                event.setDropCompleted(true);
>>>>>>> Stashed changes
            } else {
            }
        });
    }

<<<<<<< Updated upstream
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

=======
    void setDeckAktifPlayer() {
        List<Card> deck = Player.getPlayerNow().getDeck().getHand();
        deckAktifBox.getChildren().clear();
        for (Card card : deck) {
            Pane cardPane = createCardPane(card);
            deckAktifBox.getChildren().add(cardPane);
            setupDragSource(cardPane, card.getImage());
        }
    }

>>>>>>> Stashed changes
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

<<<<<<< Updated upstream
        setupDragSource(cardPane, card);
=======
        setupDragSource(cardPane, card.getImage());
>>>>>>> Stashed changes

        return cardPane;
    }

    private void nextTurn() {
        nextButton.setOnAction(event -> {
            TurnNow++;
            turnText.setText("Turn " + TurnNow);
            Player.nextTurn();
            setDeckAktifPlayer();
<<<<<<< Updated upstream
            setLadangPlayer(Player.getPlayerNow());
=======
            if (Math.random() < 0.3) {
                initializeSerangan();
            }
>>>>>>> Stashed changes
        });
    }

    private void ladangku() {
        ladangkuButton.setOnAction(event -> {
<<<<<<< Updated upstream
            setLadangPlayer(Player.getPlayerNow());
=======
            Deck deck = Player.getPlayerNow().getDeck();
>>>>>>> Stashed changes
        });
    }

    private void ladangLawan() {
        ladangLawanButton.setOnAction(event -> {
<<<<<<< Updated upstream
            setLadangPlayer(Player.getPlayerEnemy());
=======
            Deck deck = Player.getPlayerEnemy().getDeck();
>>>>>>> Stashed changes
        });
    }
}
