package com.istiqomah.if2210_tb2_iq9;

import com.istiqomah.if2210_tb2_iq9.model.card.*;
import com.istiqomah.if2210_tb2_iq9.model.cardcollection.Deck;
import com.istiqomah.if2210_tb2_iq9.model.player.Player;
import com.istiqomah.if2210_tb2_iq9.model.toko.Toko;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.List;
import java.util.Objects;
import java.util.Random;

public class MainPage extends Application {
    private static Deck playerDeck;
    public static Toko toko;
    public static void inisialisasi()
    {
        new CardManager();
        toko = new Toko ();
        Random rand = new Random();
        Player player1 = Player.createPlayer(100);
        Player player2 = Player.createPlayer(100);

        System.out.println("MainPage: Create player done...");

        player1.getDeck().initMainDeck();
        player2.getDeck().initMainDeck();

        System.out.println("MainPage: Player init main deck done...");

        for (int i = 0; i < 6; i++)
        {
            if (rand.nextBoolean())
            {
                player1.getDeck().addCardToHand(player1.getDeck().getMainDeck().get(rand.nextInt(player1.getDeck().getMainDeckSize())));
            }
            if (rand.nextBoolean())
            {
                player2.getDeck().addCardToHand(player2.getDeck().getMainDeck().get(rand.nextInt(player2.getDeck().getMainDeckSize())));
            }
        }

        System.out.println("Player add to hand done...");


        // debug ladang player
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                if (rand.nextBoolean()) {
                    player1.addCardToLadang(CardManager.getRandomCardAnimalPlant(), i, j);
                }
                if (rand.nextBoolean()) {
                    player2.addCardToLadang(CardManager.getRandomCardAnimalPlant(), i, j);
                }
            }
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("fxml/newMain.fxml")));
        root.getStylesheets().add(getClass().getResource("css/main.css").toExternalForm());
        primaryStage.setTitle("Tugas Besar 2 IF2210 - IQ9");
        primaryStage.setScene(new Scene(root, 850, 830));
        primaryStage.show();
    }

    public static void main(String[] args)
    {
        inisialisasi();
        launch(args);
    }

    public static Card getRandomCard(List<Card> cards) {
        Card randomCard;
        do {
            randomCard = cards.get(new Random().nextInt(cards.size()));
        } while (cards.contains(randomCard));
        return randomCard;
    }
}