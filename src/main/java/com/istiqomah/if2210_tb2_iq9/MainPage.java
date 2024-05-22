package com.istiqomah.if2210_tb2_iq9;

import com.istiqomah.if2210_tb2_iq9.model.card.*;
import com.istiqomah.if2210_tb2_iq9.model.player.Player;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;
import java.util.Random;

public class MainPage extends Application {
    public static void inisialisasi()
    {
        Random rand = new Random();
        Player player1 = Player.createPlayer(100);
        Player player2 = Player.createPlayer(100);
        new CardManager();
        for (int i = 0; i < 6; i++)
        {
            if (rand.nextBoolean())
            {
                player1.getDeck().addCardToHand(CardManager.getRandomCard());
            }
            if (rand.nextBoolean())
            {
                player2.getDeck().addCardToHand(CardManager.getRandomCard());
            }
        }


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
        primaryStage.setTitle("Tugas Besar 2 IF2210 - IQ9");
        primaryStage.setScene(new Scene(root, 850, 830));
        primaryStage.show();
    }

    public static void main(String[] args)
    {
        inisialisasi();
        launch(args);
    }
}