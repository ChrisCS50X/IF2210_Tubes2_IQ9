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
import com.istiqomah.if2210_tb2_iq9.plugin.SaveLoadPlugin;
import com.istiqomah.if2210_tb2_iq9.plugin.PluginRegistry;

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
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Map;

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
    private Label player1Score;
    @FXML
    private Label player2Score;
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
    @FXML
    private Label ladangTitle;

    @FXML
    private SaveController saveController;

    @FXML
    private LoadController loadController;

    public boolean ladangku;

    private Timeline timeline;
    private Ladang ladang;
    private MainPage mainpage;
    private int timeRemaining; // in tenths of a second
    private List<int[]> bearAttackPositions;
    private Image gold = new Image("file:src/main/resources/com/istiqomah/if2210_tb2_iq9/card/image/Icon/gold.png");

    @FXML
    private void updateDeckLabel(int newDeckSize) {
        deckLabel.setText("Deck: " + newDeckSize + "/40");
    }

    public void initialize() throws IOException {
        setDeckAktifPlayer();
        ladangku = true;
        setLadangPlayer(Player.getPlayerNow());
        ladangTitle.setText("LADANGKU (" + Player.getPlayerNow().getName() + ")");
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
        player1Score.setText(String.valueOf(Player.getPlayerByIdx(0).getGulden()));
        player2Score.setText(String.valueOf(Player.getPlayerByIdx(1).getGulden()));

        // Initialize bear attack UI elements
        bearAttackLabel.setText("");
        timerLabel.setText("");
        updateAvailableFormats();
    }
    public void updateAvailableFormats() {
        if (saveController != null) {
            saveController.updateAvailableFormats();
        }
        if (loadController != null) {
            loadController.updateAvailableFormats();
        }
    }

    // Metode untuk mengatur sumber drag untuk kartu
    private void setupDragSource(Pane source, Card card, boolean isCurrentPlayerLadang) {
        if (isCurrentPlayerLadang) {
            source.setOnDragDetected(event -> {
                Dragboard db = source.startDragAndDrop(TransferMode.MOVE); // Memulai drag-and-drop dengan mode transfer
                                                                           // MOVE
                ClipboardContent content = new ClipboardContent(); // Membuat clipboard content
                content.putImage(card.getImage()); // Menetapkan gambar kartu ke clipboard content
                source.setUserData(card); // Simpan data kartu di panel sumber
                db.setContent(content); // Menetapkan konten ke dragboard
                event.consume(); // Mengkonsumsi event
            });
        }
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
                } else if (card instanceof Item || card instanceof Product) {
                    if (!target.getChildren().isEmpty()) {
                        Card targetCard = (Card) target.getUserData();
                        // If the target card is not a Plant or the dragged card is not a Product,
                        // accept the transfer
                        if (!(targetCard instanceof Plant && card instanceof Product)) {
                            event.acceptTransferModes(TransferMode.MOVE);
                        }
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

                if (newCard instanceof Item) {
                    if (!target.getChildren().isEmpty()) {
                        Card targetCard = (Card) target.getUserData();
                        // Apply the item to the target card
                        if (ladangku) {
                            if (newCard.getName().equals("Accelerate") || newCard.getName().equals("Instant_Harvest")
                                    || newCard.getName().equals("Protect") || newCard.getName().equals("Trap")) {
                                if (targetCard instanceof Animal) {
                                    ((Animal) targetCard).applyItem((Item) newCard);
                                } else if (targetCard instanceof Plant) {
                                    ((Plant) targetCard).applyItem((Item) newCard);
                                }
                                ((Pane) source.getParent()).getChildren().remove(source);
                                Player.getPlayerNow().getDeck().removeFromHand(sourceCol);
                                event.setDropCompleted(true);
                            } else {
                                System.err.println("Tidak bisa menggunakan item ini di sini");
                                System.err.println(newCard.getName());
                            }
                        } else {
                            if (newCard.getName().equals("Delay")) {
                                if (targetCard.hasItem("Protect")) {
                                    System.err.println("Tidak bisa menggunakan item destroy untuk kartu yang dilindungi");
                                }

                                else {
                                    if (targetCard instanceof Animal) {
                                        ((Animal) targetCard).applyItem((Item) newCard);
                                    } else if (targetCard instanceof Plant) {
                                        ((Plant) targetCard).applyItem((Item) newCard);
                                    }
                                    ((Pane) source.getParent()).getChildren().remove(source);
                                    Player.getPlayerNow().getDeck().removeFromHand(sourceCol);
                                    event.setDropCompleted(true);
                                }
                            } else if (newCard.getName().equals("Destroy")) {
                                if (targetCard.hasItem("Protect")) {
                                    System.err.println("Tidak bisa menggunakan item destroy untuk kartu yang dilindungi");
                                } else {
                                    Player.getPlayerEnemy().getLadang().removeCardFromPosition(targetRow, targetCol);
                                    setLadangPlayer(Player.getPlayerEnemy());
                                    ((Pane) source.getParent()).getChildren().remove(source);
                                    Player.getPlayerNow().getDeck().removeFromHand(sourceCol);
                                    event.setDropCompleted(true);
                                }
                            } else {
                                System.err.println("Tidak bisa menggunakan item ini di sini");
                            }
                        }

                    }
                } else if (newCard instanceof Product) {
                    if (!target.getChildren().isEmpty()) {
                        Card targetCard = (Card) target.getUserData();

                        if (targetCard instanceof Animal) {
                            boolean isFeedValid = ((Animal) targetCard).feed((Product) newCard);
                            if (isFeedValid) {
                                ((Pane) source.getParent()).getChildren().remove(source);
                                Player.getPlayerNow().getDeck().removeFromHand(sourceCol);
                                event.setDropCompleted(true);
                            }
                        }
                        event.setDropCompleted(true);
                    }
                } else if (newCard instanceof Animal || newCard instanceof Plant) {
                    if (target.getChildren().isEmpty()) {
                        if (ladangku) {
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
                            setupDragSource(newPane, newCard, true); // Mengatur pane baru sebagai sumber drag dengan
                                                                     // gambar yang sama
                            setupClickHandler(newPane, newCard);
                            ((Pane) source.getParent()).getChildren().remove(source); // Menghapus sumber dari
                                                                                      // parent-nya
                            setLadangPlayer(Player.getPlayerNow());
                            event.setDropCompleted(true);
                        }
                    }
                } else {
                    event.setDropCompleted(false);
                }
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
                if (((Pane) node).getChildren().size() > 0) {
                    ((Pane) node).getChildren().clear();
                }
            }
        }
    }

    public void setDeckAktifPlayer() {
        List<Card> deck = Player.getPlayerNow().getDeck().getHand();
        clearGrid(deckAktifBox);
        updateDeckLabel(Player.getPlayerNow().getDeck().getMainDeckSize());
        System.out.println(Player.getPlayerNow().getName());
        System.out.println(Player.getPlayerNow().getDeck().getMainDeckSize());

        for (int i = 0; i < deck.size() && i < 6; i++) { // Add condition to prevent out of bounds access
            Card card = deck.get(i);
            if (card == null) {
                continue;
            }
            Pane cardPane = createCardPane(card);
            deckAktifBox.add(cardPane, i, 0);
            setupDragSource(cardPane, card, true);
        }
    }

    // Modify the setLadangPlayer method to ensure highlights are reapplied
    private void setLadangPlayer(Player p) {
        clearGrid(ladangGrid);
        KomponenPetak[][] ladang = p.getLadang().getGrid();
        boolean isCurrentPlayerLadang = p.equals(Player.getPlayerNow());
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                if (ladang[i][j] != null) {
                    Pane cardPane;
                    Card card = (Card) ladang[i][j];
                    if (card instanceof Animal animal && animal.isHarvestable()) {
                        Product product = (Product) animal.getProduct();
                        cardPane = createCardPaneHarvest(card, product.getImage(), product.getName());
                    } else if (card instanceof Plant plant && plant.isHarvestable()) {
                        Product product = (Product) plant.getProduct();
                        cardPane = createCardPaneHarvest(card, product.getImage(), product.getName());
                    } else {
                        cardPane = createCardPane(card);
                    }
                    ladangGrid.add(cardPane, j, i);
                    setupDragTarget(cardPane);
                    setupDragSource(cardPane, card, isCurrentPlayerLadang);
                    setupClickHandler(cardPane, card);
                } else {
                    Pane cardPane = new Pane();
                    cardPane.setPrefSize(100, 100);
                    ladangGrid.add(cardPane, j, i);
                    setupDragTarget(cardPane);
                }
            }
        }
        updateButtonColors(p);
        if (bearAttackPositions != null) {
            highlightAttackZone(bearAttackPositions); // Reapply highlight only if bear attack is ongoing
        }
    }

    private void updateButtonColors(Player currentPlayer) {
        if (currentPlayer.equals(Player.getPlayerNow())) {
            ladangkuButton.setStyle("-fx-background-color: linear-gradient(to top, #ff94bb, #f4abba, #efc3cc);");
            ladangLawanButton.setStyle("");
        } else {
            ladangkuButton.setStyle("");
            ladangLawanButton.setStyle("-fx-background-color: linear-gradient(to top, #ff94bb, #f4abba, #efc3cc);");
        }
    }

    private Pane createCardPane(Card card) {
        VBox cardPane = new VBox();
        cardPane.setPrefSize(100, 100);
        cardPane.setAlignment(Pos.CENTER);
        cardPane.setStyle("-fx-border-width: 1px; -fx-background-radius: 8; -fx-border-radius: 8;");

        ImageView imageView = new ImageView(card.getImage());
        imageView.setFitWidth(80);
        imageView.setFitHeight(80);

        Text cardName = new Text(card.getName());

        cardPane.getChildren().addAll(imageView, cardName);

        cardPane.setUserData(card); // Set the Card as the user data of the Pane

        return cardPane;
    }

    private Pane createCardPaneHarvest(Card card, Image img, String productName) {
        VBox cardPane = new VBox();
        cardPane.setPrefSize(100, 100);
        cardPane.setAlignment(Pos.CENTER);
        cardPane.setStyle("-fx-border-width: 1px; -fx-background-radius: 8; -fx-border-radius: 8;");

        ImageView imageView = new ImageView(img);
        imageView.setFitWidth(80);
        imageView.setFitHeight(80);

        Text cardName = new Text(productName);

        cardPane.getChildren().addAll(imageView, cardName);

        cardPane.setUserData(card); // Set the Card as the user data of the Pane

        return cardPane;
    }

    private void nextTurn() {
        nextButton.setOnAction(event -> {
            try {
                if (TurnNow == 20) {
                    calculateWinner();
                    return;
                }
                else {
                    TurnNow++;
                    turnText.setText("Turn " + TurnNow);
                    Player.nextTurn();
                    Player.updateAgePlant();
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
                    ladangTitle.setText("LADANGKU (" + Player.getPlayerNow().getName() + ")");
                    ladangku = true;
                    //                if (Math.random() < 0.3) {
                    //                    initializeSerangan();
                    //                }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void ladangku() {
        ladangkuButton.setOnAction(event -> {
            setLadangPlayer(Player.getPlayerNow());
            ladangTitle.setText("LADANGKU (" + Player.getPlayerNow().getName() + ")");
            ladangku = true;
        });
    }

    private void ladangLawan() {
        ladangLawanButton.setOnAction(event -> {
            setLadangPlayer(Player.getPlayerEnemy());
            ladangTitle.setText("LADANG LAWAN");
            ladangku = false;

        });
    }

    private void initializeSerangan() {
        ladang = Player.getPlayerNow().getLadang();
        ladang.initiateBearAttack();

        List<int[]> attackPositions = ladang.getCurrentBearAttack().getAttackPositions();
        bearAttackLabel.setText("Bear is attacking at\nmultiple positions!");
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
        boolean trapFound = false;

        // Check if there is any card with item "trap" in the attack positions
        for (int[] position : attackPositions) {
            int x = position[0];
            int y = position[1];
            Card card = (Card) ladang.getCardAtPosition(x, y);
            if (card != null && card.hasItem("Trap")) {
                trapFound = true;
                break;
            }
        }

        // If a card with item "trap" is found, add a bear card to the active deck and
        // do not remove any card
        if (trapFound) {
            Card bearCard = CardManager.getCard("animal", "Beruang");
            if (bearCard != null) {
                Player.getPlayerNow().getDeck().addCardToHand(bearCard);
                setDeckAktifPlayer();
            }
        } else {
            // Remove cards that do not have the "protected" item
            for (int[] position : attackPositions) {
                int x = position[0];
                int y = position[1];
                Card card = (Card) ladang.getCardAtPosition(x, y);
                if (card != null && !card.hasItem("Protect")) {
                    ladang.removeCardFromPosition(x, y);
                }
            }
        }

        updateGridUI();
        bearAttackLabel.setText("");
        timerLabel.setText("");
        clearHighlight();

        // Clear bear attack positions after attack ends
        bearAttackPositions = null;

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
                    setupDragSource(cardPane, card, true);
                } else {
                    Pane cardPane = new Pane();
                    cardPane.setPrefSize(100, 100);
                    this.ladangGrid.add(cardPane, j, i);
                    setupDragTarget(cardPane);
                }
            }
        }
        if (bearAttackPositions != null) {
            highlightAttackZone(bearAttackPositions); // Reapply highlight only if bear attack is ongoing
        }
    }

    private void highlightAttackZone(List<int[]> positions) {
        bearAttackPositions = positions; // Store positions for later use
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
                pane.setStyle("-fx-border-color: none;");
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
            stage.initStyle(StageStyle.UNDECORATED);
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
            AnchorPane root = loader.load();

            SaveController saveController = loader.getController();
            saveController.setMainPageController(this);

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("Load State");
            stage.setScene(new Scene(root));
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("State saved!");
    }

    public void pluginState() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/istiqomah/if2210_tb2_iq9/fxml/plugin.fxml"));
            Parent root = loader.load();

            PluginController loadPluginController = loader.getController();
            loadPluginController.setMainPageController(this);

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Load Plugin");
            stage.setScene(new Scene(root));
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("State loaded!");

    }

    public void loadData(String folderPath) {
        try {
            String config1Path = folderPath + "/config1.txt";
            String config2Path = folderPath + "/config2.txt";
            String gameStatePath = folderPath + "/gamestate.txt";

            System.out.println("Loading config1 from: " + config1Path);
            System.out.println("Loading config2 from: " + config2Path);
            System.out.println("Loading game state from: " + gameStatePath);

            try (BufferedReader config1Reader = new BufferedReader(new FileReader(config1Path));
                    BufferedReader config2Reader = new BufferedReader(new FileReader(config2Path));
                    BufferedReader gameStateReader = new BufferedReader(new FileReader(gameStatePath))) {

                // Load data for player 1
                loadPlayerData(config1Reader, Player.getPlayerByIdx(0));

                // Load data for player 2
                loadPlayerData(config2Reader, Player.getPlayerByIdx(1));

                // Read gamestate.txt
                loadGameState(gameStateReader);

                setLadangPlayer(Player.getPlayerNow());
                setDeckAktifPlayer();

            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadPlayerData(BufferedReader reader, Player player) throws IOException {
        String line;

        // Read player data
        try {
            int gulden = Integer.parseInt(reader.readLine());
            int jumlahKartu = Integer.parseInt(reader.readLine());
            int handSize = Integer.parseInt(reader.readLine());

            player.setGulden(gulden);
            player.getDeck().clearHand();
            if (player.getDeck().getHand().isEmpty()) {
                System.err.println("kosong: ");
            }

            // Read hand cards
            for (int i = 0; i < handSize; i++) {
                line = reader.readLine();
                String[] parts = line.split(" ");
                if (parts.length < 2) {
                    System.err.println("Invalid hand card entry: " + line);
                    continue;
                }
                String cardName = parts[1];
                String slot = parts[0];
                int lok = Integer.parseInt(slot.substring(1));
                Card card = getCardByName(cardName);
                if (card != null) {
                    player.getDeck().addCardToHand(lok, card);

                } else {
                    System.err.println("Card not found: " + cardName);
                }
            }
            if (player.getDeck().getHand().isEmpty()) {
                System.err.println(" Masih kosong: ");
            }

            // Read ladang cards
            player.clearLadang();

            int count = Integer.parseInt(reader.readLine());
            for (int i = 0; i < count; i++) {
                line = reader.readLine();
                String[] parts = line.split(" ");
                if (parts.length < 4) {
                    System.err.println("Invalid ladang card entry: " + line);
                    continue;
                }
                String lokasi = parts[0];
                String cardName = parts[1];
                int beratUmur = Integer.parseInt(parts[2]);
                int jumlahItem = Integer.parseInt(parts[3]);

                // Extract row and column from lokasi
                String rowLetter = lokasi.substring(0, 1);
                String colIndexStr = lokasi.substring(1);
                int rowIndex = convertLetterToRow(rowLetter);
                int colIndex = Integer.parseInt(colIndexStr);

                Card card = getCardByName(cardName);
                if (card != null) {
                    card.setBerat_Umur(beratUmur);
                    player.addCardToLadang(card, rowIndex, colIndex);
                    for (int j = 0; j < jumlahItem; j++) {
                        String itemName = parts[4 + j];
                        Item item = (Item) CardManager.getCard("item", itemName);
                        if (item != null) {
                            card.applyItem(item);
                        } else {
                            System.err.println("Item not found: " + itemName);
                        }
                    }
                } else {
                    System.err.println("Card not found in ladang: " + cardName);
                }
            }
        } catch (NumberFormatException e) {
            System.err.println("Error parsing player data: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error reading player data: " + e.getMessage());
        }
    }

    private int convertLetterToRow(String letter) {
        return letter.charAt(0) - 'A';
    }

    private Card getCardByName(String cardName) {
        Card card = null;
        if (CardManager.getAnimalNames().contains(cardName)) {
            card = CardManager.getCard("animal", cardName);
        } else if (CardManager.getPlantNames().contains(cardName)) {
            card = CardManager.getCard("plant", cardName);
        } else if (CardManager.getProductNames().contains(cardName)) {
            card = CardManager.getCard("product", cardName);
        } else {
            card = CardManager.getCard("item", cardName);
        }

        if (card == null) {
            System.err.println("Card not found in CardManager: " + cardName);
        }

        return card;
    }

    private void loadGameState(BufferedReader reader) throws IOException {
        String line;
        int turn = Integer.parseInt(reader.readLine());
        TurnNow = turn;
        int jumlahItemShop = Integer.parseInt(reader.readLine());

        mainpage.toko.clearProducts();

        for (int i = 0; i < jumlahItemShop; i++) {
            line = reader.readLine();
            String[] parts = line.split(" ");
            String productName = parts[0];
            int quantity = Integer.parseInt(parts[1]);

            Card itemCard = CardManager.getCard("product", productName);
            if (itemCard != null) {
                for (int j = 0; j < quantity; j++) {
                    mainpage.toko.addProduct(itemCard);
                }
            } else {
                System.err.println("Product not found in shop: " + productName);
            }
        }
    }

    private void openToko() {
        try {
            // Memuat file FXML toko
            FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/Toko.fxml"));
            Pane view = loader.load();

            // Mendapatkan instance dari TokoController
            TokoController tokoController = loader.getController();
            Player currentPlayer = Player.getPlayerNow();

            tokoController.setToko(MainPage.toko);
            tokoController.setPlayer(currentPlayer);
            tokoController.updateCardSellsDeck();

            // Set listener untuk mengetahui saat toko ditutup
            tokoController.setOnTokoClosedListener(() -> {
                int numberPlayer = Player.getIdxTurnPlayer() + 1;
                if (numberPlayer == 1) {
                    player1Score.setText(String.valueOf(Player.getPlayerByIdx(0).getGulden()));
                } else {
                    player2Score.setText(String.valueOf(Player.getPlayerByIdx(1).getGulden()));
                }
                setDeckAktifPlayer();
            });

            Stage stage = new Stage();
            stage.setTitle("Toko View");
            stage.setScene(new Scene(view, 850, 830));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setupClickHandler(Pane cardPane, Card card) {
        Integer rowIndex = GridPane.getRowIndex(cardPane);
        Integer colIndex = GridPane.getColumnIndex(cardPane);

        if (rowIndex != null && colIndex != null) {
            int x = rowIndex;
            int y = colIndex;
            cardPane.setOnMouseClicked(event -> {
                handleCardClick(card, x, y);
            });
        } else {
            // Set default click handler if rowIndex or colIndex is null
            cardPane.setOnMouseClicked(event -> {
                System.out.println("Card position not set.");
            });
        }
    }

    private void handleCardClick(Card card, int x, int y) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/Card.fxml"));
            Pane view = loader.load();
            CardController cardController = loader.getController();
            if (ladangku) {
                cardController.setCard(card, x, y, true);
            } else {
                cardController.setCard(card, x, y, false);
            }
            Stage stage = new Stage();
            stage.setTitle("Shuffle View");
            stage.setScene(new Scene(view, 650, 300));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.showAndWait();
            if (ladangku) {
                setLadangPlayer(Player.getPlayerNow());
            } else {
                setLadangPlayer(Player.getPlayerEnemy());
            }
            updateDeckLabel(Player.getPlayerNow().getDeck().getMainDeckSize());
            setDeckAktifPlayer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void calculateWinner() throws IOException {
        System.out.println("Loading FXML...");
        FXMLLoader p = new FXMLLoader(getClass().getResource("fxml/winning-pane.fxml"));
        StackPane winpane = p.load();

        System.out.println("Getting controller...");
        WinController winController = p.getController();
        winController.setWinner();

        System.out.println("Show window...");
        Stage stage = new Stage();
        stage.setScene(new Scene(winpane, 550, 530));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.showAndWait();
    }

    public void loadData(String folderPath, String format) {
        SaveLoadPlugin plugin = PluginRegistry.getPluginForFormat(format);
        if (plugin != null) {
            Map<String, Object> player1Data = plugin.load(folderPath + "/config1." + format.toLowerCase());
            Map<String, Object> player2Data = plugin.load(folderPath + "/config2." + format.toLowerCase());
            Map<String, Object> gameStateData = plugin.load(folderPath + "/gamestate." + format.toLowerCase());

            setPlayerData(Player.getPlayerByIdx(0), player1Data);
            setPlayerData(Player.getPlayerByIdx(1), player2Data);
            setGameStateData(gameStateData);

            setLadangPlayer(Player.getPlayerNow());
            setDeckAktifPlayer();
        } else {
            String config1Path = folderPath + "/config1.txt";
            String config2Path = folderPath + "/config2.txt";
            String gameStatePath = folderPath + "/gamestate.txt";

            System.out.println("Loading config1 from: " + config1Path);
            System.out.println("Loading config2 from: " + config2Path);
            System.out.println("Loading game state from: " + gameStatePath);

            try (BufferedReader config1Reader = new BufferedReader(new FileReader(config1Path));
                 BufferedReader config2Reader = new BufferedReader(new FileReader(config2Path));
                 BufferedReader gameStateReader = new BufferedReader(new FileReader(gameStatePath))) {

                // Load data for player 1
                loadPlayerData(config1Reader, Player.getPlayerByIdx(0));

                // Load data for player 2
                loadPlayerData(config2Reader, Player.getPlayerByIdx(1));

                // Read gamestate.txt
                loadGameState(gameStateReader);

                setLadangPlayer(Player.getPlayerNow());
                setDeckAktifPlayer();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void saveData(String folderPath, String format) {
        SaveLoadPlugin plugin = PluginRegistry.getPluginForFormat(format);
        if (plugin != null) {
            Map<String, Object> player1Data = getPlayerData(Player.getPlayerByIdx(0));
            Map<String, Object> player2Data = getPlayerData(Player.getPlayerByIdx(1));
            Map<String, Object> gameStateData = getGameStateData();

            plugin.save(folderPath + "/config1." + format.toLowerCase(), player1Data);
            plugin.save(folderPath + "/config2." + format.toLowerCase(), player2Data);
            plugin.save(folderPath + "/gamestate." + format.toLowerCase(), gameStateData);
        } else {
            saveDataAsTxt(folderPath);
        }
    }

    private void saveDataAsTxt(String folderPath) {
        try {
            // Ensure the folder exists
            File folder = new File(folderPath);
            if (!folder.exists()) {
                folder.mkdirs();  // Create the folder if it doesn't exist
            }

            // Write to config1.txt
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(folderPath + "/config1.txt"))) {
                int gulden = Player.getPlayerByIdx(0).getGulden();
                int jumlahKartu = Player.getPlayerByIdx(0).getDeck().getMainDeckSize();
                int n_hand = 0;
                List<Card> handc = Player.getPlayerByIdx(0).getDeck().getHand();
                for (int i = 0; i < 6; i++) {
                    if (handc.get(i) != null) {
                        n_hand++;
                    }
                }

                writer.write(gulden + "\n");
                writer.write(jumlahKartu + "\n");
                writer.write(n_hand + "\n");

                // Write hand cards
                for (int i = 0; i < 6; i++) {
                    Card card = Player.getPlayerByIdx(0).getDeck().getHand().get(i);
                    if (card != null) {
                        String name = card.getName();
                        String lokasi = "A0" + i;
                        writer.write(lokasi + " " + name + "\n");
                    }
                }

                // Write ladang cards
                Ladang ladang = Player.getPlayerByIdx(0).getLadang();
                int count = 0;

                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 5; j++) {
                        KomponenPetak komponen = ladang.getCardAtPosition(i, j);
                        if (komponen != null) {
                            count++;
                        }
                    }
                }
                writer.write(count + "\n");
                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 5; j++) {
                        KomponenPetak komponen = ladang.getCardAtPosition(i, j);
                        if (komponen instanceof Card) {
                            Card card = (Card) komponen;
                            if (card != null) {
                                String name = card.getName();
                                String lokasi = convertRowToLetter(i) + "0" + String.valueOf(j);
                                int beratUmur = card.getBerat_Umur();
                                List<Item> items = card.getActiveItems();
                                int jumlahItem = items.size();

                                writer.write(lokasi + " " + name + " " + beratUmur + " " + jumlahItem);
                                for (Item item : items) {
                                    writer.write(" " + item.getName());
                                }
                                writer.write("\n");
                            }
                        }
                    }
                }
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(folderPath + "/config2.txt"))) {
                int gulden = Player.getPlayerByIdx(1).getGulden();
                int jumlahKartu = Player.getPlayerByIdx(1).getDeck().getMainDeckSize();
                int n_hand = 0;
                List<Card> handc = Player.getPlayerByIdx(1).getDeck().getHand();
                for (int i = 0; i < 6; i++) {
                    if (handc.get(i) != null) {
                        n_hand++;
                    }
                }

                writer.write(gulden + "\n");
                writer.write(jumlahKartu + "\n");
                writer.write(n_hand + "\n");

                // Write hand cards
                for (int i = 0; i < 6; i++) {
                    Card card = Player.getPlayerByIdx(1).getDeck().getHand().get(i);
                    if (card != null) {
                        String name = card.getName();
                        String lokasi = "A0" + i;
                        writer.write(lokasi + " " + name + "\n");
                    }
                }

                // Write ladang cards
                Ladang ladang = Player.getPlayerByIdx(1).getLadang();
                int count = 0;

                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 5; j++) {
                        KomponenPetak komponen = ladang.getCardAtPosition(i, j);
                        if (komponen != null) {
                            count++;
                        }
                    }
                }
                writer.write(count + "\n");

                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 5; j++) {
                        KomponenPetak komponen = ladang.getCardAtPosition(i, j);
                        if (komponen instanceof Card) {
                            Card card = (Card) komponen;
                            if (card != null) {
                                String name = card.getName();
                                String lokasi = convertRowToLetter(i) + "0" + String.valueOf(j);
                                int beratUmur = card.getBerat_Umur();
                                List<Item> items = card.getActiveItems();
                                int jumlahItem = items.size();

                                writer.write(lokasi + " " + name + " " + beratUmur + " " + jumlahItem);
                                for (Item item : items) {
                                    writer.write(" " + item.getName());
                                }
                                writer.write("\n");
                            }
                        }
                    }
                }
            }

            // Write to gamestate.txt
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(folderPath + "/gamestate.txt"))) {
                int turn = TurnNow;
                Map<Card, Integer> shopItems = mainpage.toko.getAvailableProducts();
                int jumlahItemShop = shopItems.size();

                writer.write(turn + "\n");
                writer.write(jumlahItemShop + "\n");

                for (Map.Entry<Card, Integer> item : shopItems.entrySet()) {
                    Card card = item.getKey();
                    if (card != null) {
                        writer.write(card.getName() + " " + item.getValue() + "\n");
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String convertRowToLetter(int row) {
        return Character.toString((char) ('A' + row));
    }

    private Map<String, Object> getPlayerData(Player player) {
        Map<String, Object> data = new HashMap<>();
        data.put("gulden", player.getGulden());
        data.put("jumlahKartu", player.getDeck().getMainDeckSize());

        List<Map<String, Object>> hand = new ArrayList<>();
        for (Card card : player.getDeck().getHand()) {
            if (card != null) {
                Map<String, Object> cardData = new HashMap<>();
                cardData.put("name", card.getName());
                cardData.put("lokasi", "A0" + player.getDeck().getHand().indexOf(card));
                hand.add(cardData);
            }
        }
        data.put("hand", hand);

        List<Map<String, Object>> ladang = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                KomponenPetak komponen = player.getLadang().getCardAtPosition(i, j);
                if (komponen instanceof Card) {
                    Card card = (Card) komponen;
                    Map<String, Object> cardData = new HashMap<>();
                    cardData.put("name", card.getName());
                    cardData.put("lokasi", convertRowToLetter(i) + "0" + j);
                    cardData.put("beratUmur", card.getBerat_Umur());
                    List<String> items = new ArrayList<>();
                    for (Item item : card.getActiveItems()) {
                        items.add(item.getName());
                    }
                    cardData.put("items", items);
                    ladang.add(cardData);
                }
            }
        }
        data.put("ladang", ladang);

        return data;
    }

    private Map<String, Object> getGameStateData() {
        Map<String, Object> data = new HashMap<>();
        data.put("turn", TurnNow);

        List<Map<String, Object>> shopItems = new ArrayList<>();
        for (Map.Entry<Card, Integer> entry : mainpage.toko.getAvailableProducts().entrySet()) {
            Map<String, Object> itemData = new HashMap<>();
            itemData.put("name", entry.getKey().getName());
            itemData.put("quantity", entry.getValue());
            shopItems.add(itemData);
        }
        data.put("shopItems", shopItems);

        return data;
    }

    public Map<String, Object> getGameData() {
        Map<String, Object> data = new HashMap<>();

        // Extract player 1 data
        data.put("player1", getPlayerData(Player.getPlayerByIdx(0)));

        // Extract player 2 data
        data.put("player2", getPlayerData(Player.getPlayerByIdx(1)));

        // Extract game state data
        data.put("gameState", getGameStateData());

        return data;
    }

    private void setPlayerData(Player player, Map<String, Object> data) {
        player.setGulden(convertToInt(data.get("gulden")));
        player.getDeck().clearHand();

        // Handle hand data
        Object handData = data.get("hand");
        if (handData instanceof List) {
            List<Map<String, Object>> hand = (List<Map<String, Object>>) handData;
            for (Map<String, Object> cardData : hand) {
                Card card = getCardByName((String) cardData.get("name"));
                if (card != null) {
                    int lok = Integer.parseInt(((String) cardData.get("lokasi")).substring(2));
                    player.getDeck().addCardToHand(lok, card);
                }
            }
        } else if (handData instanceof Map) {
            Map<String, Object> hand = (Map<String, Object>) handData;
            Card card = getCardByName((String) hand.get("name"));
            if (card != null) {
                int lok = Integer.parseInt(((String) hand.get("lokasi")).substring(2));
                player.getDeck().addCardToHand(lok, card);
            }
        }

        // Handle ladang data
        player.clearLadang();
        Object ladangData = data.get("ladang");
        if (ladangData instanceof List) {
            List<Map<String, Object>> ladang = (List<Map<String, Object>>) ladangData;
            for (Map<String, Object> cardData : ladang) {
                Card card = getCardByName((String) cardData.get("name"));
                if (card != null) {
                    int rowIndex = convertLetterToRow(((String) cardData.get("lokasi")).substring(0, 1));
                    int colIndex = Integer.parseInt(((String) cardData.get("lokasi")).substring(2));
                    card.setBerat_Umur(convertToInt(cardData.get("beratUmur")));
                    player.addCardToLadang(card, rowIndex, colIndex);

                    if (cardData.containsKey("items")) {
                        List<String> items = (List<String>) cardData.get("items");
                        for (String itemName : items) {
                            Item item = (Item) CardManager.getCard("item", itemName);
                            if (item != null) {
                                card.applyItem(item);
                            }
                        }
                    }
                }
            }
        } else if (ladangData instanceof Map) {
            Map<String, Object> cardData = (Map<String, Object>) ladangData;
            Card card = getCardByName((String) cardData.get("name"));
            if (card != null) {
                int rowIndex = convertLetterToRow(((String) cardData.get("lokasi")).substring(0, 1));
                int colIndex = Integer.parseInt(((String) cardData.get("lokasi")).substring(2));
                card.setBerat_Umur(convertToInt(cardData.get("beratUmur")));
                player.addCardToLadang(card, rowIndex, colIndex);

                if (cardData.containsKey("items")) {
                    List<String> items = (List<String>) cardData.get("items");
                    for (String itemName : items) {
                        Item item = (Item) CardManager.getCard("item", itemName);
                        if (item != null) {
                            card.applyItem(item);
                        }
                    }
                }
            }
        }
    }

    private int convertToInt(Object value) {
        if (value instanceof Integer) {
            return (Integer) value;
        } else if (value instanceof String) {
            return Integer.parseInt((String) value);
        } else {
            throw new IllegalArgumentException("Cannot convert to int: " + value);
        }
    }

    private void setGameStateData(Map<String, Object> gameStateData) {
        TurnNow = convertToInt(gameStateData.get("turn"));

        // Ensure shopItems is initialized and not null
        Object shopItemsData = gameStateData.get("shopItems");
        if (shopItemsData == null) {
            System.err.println("Warning: 'shopItems' is null, initializing to an empty list.");
            shopItemsData = new ArrayList<Map<String, Object>>();
        }

        List<Map<String, Object>> shopItems = (List<Map<String, Object>>) shopItemsData;
        mainpage.toko.clearProducts();

        for (Map<String, Object> itemData : shopItems) {
            Card card = getCardByName((String) itemData.get("name"));
            if (card != null) {
                int quantity = convertToInt(itemData.get("quantity"));
                for (int i = 0; i < quantity; i++) {
                    mainpage.toko.addProduct(card);
                }
            }
        }
    }
}