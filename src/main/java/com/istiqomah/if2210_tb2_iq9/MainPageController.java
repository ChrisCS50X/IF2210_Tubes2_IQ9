package com.istiqomah.if2210_tb2_iq9;

import com.istiqomah.if2210_tb2_iq9.model.card.*;
import com.istiqomah.if2210_tb2_iq9.model.card.CardManager;
import com.istiqomah.if2210_tb2_iq9.model.cardcollection.Deck;
import com.istiqomah.if2210_tb2_iq9.model.card.Item;
import com.istiqomah.if2210_tb2_iq9.model.ladang.KomponenPetak;
import com.istiqomah.if2210_tb2_iq9.model.ladang.Ladang;
import com.istiqomah.if2210_tb2_iq9.model.player.Player;
import com.istiqomah.if2210_tb2_iq9.model.save_load.Readconfig;
import com.istiqomah.if2210_tb2_iq9.model.save_load.Triple;
import com.istiqomah.if2210_tb2_iq9.model.toko.Toko;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import java.io.IOException;
import java.util.ArrayList;

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
    public Toko toko;
    private int timeRemaining; // in tenths of a second

    private Image gold = new Image("file:src/main/resources/com/istiqomah/if2210_tb2_iq9/card/image/Icon/gold.png");

    public void initialize() {
        setDeckAktifPlayer();
        setLadangPlayer(Player.getPlayerNow());
        toko = new Toko();
        // Mendapatkan semua node yang merupakan anak dari GridPane
        for (Node node : ladangGrid.getChildren()) {
            if (node instanceof Pane pane) {
                setupDragTarget(pane);
            }
        }

        // Mengatur giliran pemain
        turnText.setText("Turn " + TurnNow);

        // Set button next turn
        nextTurn();

        // Set button ladangku
        ladangku();

        // Set button ladang lawan
        ladangLawan();

        // Set button toko
        tokoButton.setOnAction(event -> openToko());

        // Set gulden player
        player1Label.setText("Player 1: " + Player.getPlayerByIdx(0).getGulden());
        player2Label.setText("Player 2: " + Player.getPlayerByIdx(1).getGulden());

        // Set image gold
        gold1.setImage(gold);
        gold2.setImage(gold);

        // Initialize bear attack UI elements
        bearAttackLabel.setText("");
        timerLabel.setText("");
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
                Pane source = (Pane) event.getGestureSource();
                Card card = (Card) source.getUserData();
                if (card instanceof Animal || card instanceof Plant) {
                    if (target.getChildren().isEmpty()) {
                        event.acceptTransferModes(TransferMode.MOVE);
                    }
                }
                else if (card instanceof Item || card instanceof Product)
                {
                    if (!target.getChildren().isEmpty()) {
                        event.acceptTransferModes(TransferMode.MOVE);
                    }
                }
            }
            event.consume(); // Mengkonsumsi event
        });

        target.setOnDragDropped(event -> {
            Dragboard db = event.getDragboard();
            if (db.hasImage()) {
                Pane source = (Pane) event.getGestureSource();
                Card newCard = (Card) source.getUserData();
                Integer sourceCol = GridPane.getColumnIndex(source);
                Integer sourceRow = GridPane.getRowIndex(source);
                Integer targetCol = GridPane.getColumnIndex(target);
                Integer targetRow = GridPane.getRowIndex(target);

                if (newCard instanceof Item || newCard instanceof Product) {
                    if (!target.getChildren().isEmpty()) {

                        // Remove the card from the source pane and the player's hand
                        ((Pane) source.getParent()).getChildren().remove(source);
                        Player.getPlayerNow().getDeck().removeFromHand(sourceCol);
                        event.setDropCompleted(true);
                    }
                }
                else if (newCard instanceof Animal || newCard instanceof Plant)
                {
                    if (target.getChildren().isEmpty()){
                        if (sourceCol != null && sourceRow != null) {
                            if (source.getParent() == deckAktifBox) {
                                Player.getPlayerNow().getDeck().removeFromHand(sourceCol);
                            } else {
                                Player.getPlayerNow().getLadang().removeCardFromPosition(sourceRow, sourceCol);
                            }
                        }

                        if (targetCol != null && targetRow != null) {
                            if (target.getParent() == deckAktifBox) {
                                Player.getPlayerNow().getDeck().addCardToHand(newCard);
                            } else {
                                Player.getPlayerNow().getLadang().addCardToPosition(newCard, targetRow, targetCol);
                            }
                        }
                        Pane newPane = createCardPane(newCard);
                        target.getChildren().add(newPane);
                        setupDragSource(newPane, newCard); // Mengatur pane baru sebagai sumber drag dengan gambar yang sama
                        setupClickHandler(newPane,newCard);
                        ((Pane) source.getParent()).getChildren().remove(source); // Menghapus sumber dari parent-nya
                        event.setDropCompleted(true);
                    }
                }
                else
                {
                    event.setDropCompleted(false);
                }
            }
            else
            {
                event.setDropCompleted(false); // Menyatakan drop tidak selesai
            }
            event.consume(); // Mengkonsumsi event
        });
    }

    // Metode untuk membersihkan kartu dari grid ladang tanpa menghapus pane wadah
    private void clearGrid(GridPane grid) {
        for (Node node : grid.getChildren()) {
            if (node instanceof Pane) {
                if (((Pane) node).getChildren().size() > 0) {
                    ((Pane) node).getChildren().clear();
                }
                node.setStyle("-fx-border-color: grey;");
            }
        }
    }

    private void setDeckAktifPlayer() {
        List<Card> deck = Player.getPlayerNow().getDeck().getHand();
        clearGrid(deckAktifBox);

        for (int i = 0; i < deck.size() && i < 6; i++) { // Add condition to prevent out of bounds access
            Card card = deck.get(i);
            if (card == null) {
                continue;
            }
            Pane cardPane = createCardPane(card);
            deckAktifBox.add(cardPane, i, 0);
            setupDragSource(cardPane, card);
        }
    }

    private void setLadangPlayer(Player p) {
        clearGrid(ladangGrid);
        KomponenPetak[][] ladang = p.getLadang().getGrid();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                if (ladang[i][j] != null) {
                    Card card = (Card) ladang[i][j];
                    Pane cardPane = createCardPane(card);
                    ladangGrid.add(cardPane, j, i);
                    setupDragTarget(cardPane);
                    setupDragSource(cardPane, card);
                    setupClickHandler(cardPane, card);
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
        cardPane.setStyle("-fx-border-width: 1px; -fx-border-color: black; -fx-background-radius: 8; -fx-border-radius: 8;");

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
            try {
                TurnNow++;
                turnText.setText("Turn " + TurnNow);
                Player.nextTurn();
                setDeckAktifPlayer();
                setLadangPlayer(Player.getPlayerNow());

                FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/shuffle-view.fxml"));
                StackPane view = loader.load();

                Stage stage = new Stage();
                stage.setTitle("Shuffle View");
                stage.setScene(new Scene(view, 450, 430));
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.initStyle(StageStyle.UNDECORATED);
                stage.showAndWait();

                setDeckAktifPlayer();

                if (Math.random() < 0.3) {
                    initializeSerangan();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
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

    private void initializeSerangan() {
        ladang = Player.getPlayerNow().getLadang();
        ladang.initiateBearAttack();

        List<int[]> attackPositions = ladang.getCurrentBearAttack().getAttackPositions();
        bearAttackLabel.setText("Bear is attacking at multiple positions!");
        timeRemaining = ladang.getCurrentBearAttack().getDuration() * 10; // Convert to tenths of a second
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
        List<int[]> attackPositions = ladang.getCurrentBearAttack().getAttackPositions();
        for (int[] position : attackPositions) {
            int x = position[0];
            int y = position[1];
            if (x >= 0 && x < 4 && y >= 0 && y < 5 && ladang.getCardAtPosition(x, y) != null) {
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

    private void updateGridUI() {
        clearGrid(ladangGrid); // Clear the grid first
        KomponenPetak[][] ladangGrid = ladang.getGrid();
        for (int i = 0; i < ladangGrid.length; i++) {
            for (int j = 0; j < ladangGrid[i].length; j++) {
                KomponenPetak component = ladangGrid[i][j];
                if (component != null) {
                    Card card = (Card) component;
                    Pane cardPane = createCardPane(card);
                    this.ladangGrid.add(cardPane, j, i);
                    setupDragTarget(cardPane);
                    setupDragSource(cardPane, card);
                } else {
                    Pane cardPane = new Pane();
                    cardPane.setPrefSize(100, 100);
                    cardPane.setStyle("-fx-border-color: grey;");
                    this.ladangGrid.add(cardPane, j, i);
                    setupDragTarget(cardPane);
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
                        if (row == position[0] && col == position[1]) { // Use correct order of indices
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
    public void loadState() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/istiqomah/if2210_tb2_iq9/fxml/Load.fxml"));
            Parent root = loader.load();

            LoadController loadController = loader.getController();
            loadController.setMainPageController(this);

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Load State");
            stage.setScene(new Scene(root));
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("State loaded!");
    }

    public void saveState() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/istiqomah/if2210_tb2_iq9/fxml/Save.fxml"));
            Parent root = loader.load();

            SaveController saveController = loader.getController();
            saveController.setMainPageController(this); // Set the reference to MainPageController

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Save State");
            stage.setScene(new Scene(root));
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("State saved!");
    }

    public void loadData(String folderPath) {
        Readconfig config = new Readconfig("src/main/java/com/istiqomah/if2210_tb2_iq9/model/save_load/" + folderPath);

        // Update Player's Gulden
        Player.getPlayerNow().setGulden(config.getJumlahGulden());

        // Update Deck
        Player.getPlayerNow().getDeck().clearHand();
        for (Triple<String, Integer, String> cardInfo : config.getKordinatCard()) {
            Card card = CardManager.getCard("deck", cardInfo.getRight());
            if (card != null) {
                Player.getPlayerNow().getDeck().addCardToHand(card);
            }
        }

        // Clear ladang
        Ladang ladang = Player.getPlayerNow().getLadang();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                if (ladang.getCardAtPosition(i, j) != null) {
                    ladang.removeCardFromPosition(i, j);
                }
            }
        }
        for (int i = 0; i < config.getKordinatLadang().size(); i++) {
            Triple<String, Integer, String> ladangInfo = config.getKordinatLadang().get(i);
            String row = ladangInfo.getLeft();
            int col = ladangInfo.getMiddle();
            String cardName = ladangInfo.getRight();

            int rowIndex = convertLetterToRow(row);
            Card card = CardManager.getCard("ladang", cardName);
            if (card != null) {
                ladang.addCardToPosition(card, rowIndex, col);
                // Apply items to card
                ArrayList<String> items = config.getItem().get(i);
                for (String itemName : items) {
                    Item item = (Item) CardManager.getCard("item", itemName);
                    card.applyItem(item);
                }
            }
        }
    }

    public int getCurrentTurn() {
        return TurnNow;
    }

    private int convertLetterToRow(String letter) {
        return letter.charAt(0) - 'A';
    }

    private void openToko() {
        try {
            // Memuat file FXML toko
            FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/Toko.fxml"));
            Pane tokoPane = loader.load();

            // Mendapatkan instance dari TokoController
            TokoController tokoController = loader.getController();
            Toko toko = new Toko(); // Atur ini sesuai dengan cara Anda menginisialisasi objek Toko
            Player currentPlayer = Player.getPlayerNow();
            tokoController.setToko(toko);
            tokoController.setPlayer(currentPlayer);

            // Menggantikan tampilan saat ini dengan tampilan toko
            Scene currentScene = tokoButton.getScene();
            currentScene.setRoot(tokoPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setupClickHandler(Pane cardPane, Card card) {
        int x = GridPane.getColumnIndex(cardPane);
        int y = GridPane.getRowIndex(cardPane);
        cardPane.setOnMouseClicked(event -> {
            handleCardClick(card,y,x);
        });
    }

    private void handleCardClick(Card card, int x, int y) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/Card.fxml"));
            Pane view = loader.load();
            CardController cardController = loader.getController();
            cardController.setCard(card, x, y);
            Stage stage = new Stage();
            stage.setTitle("Shuffle View");
            stage.setScene(new Scene(view, 650, 300));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.showAndWait();
            setLadangPlayer(Player.getPlayerNow());
            setDeckAktifPlayer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}