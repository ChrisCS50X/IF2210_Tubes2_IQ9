package com.istiqomah.if2210_tb2_iq9;

import com.istiqomah.if2210_tb2_iq9.model.card.*;
import com.istiqomah.if2210_tb2_iq9.model.player.Player;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.List;
import java.util.Objects;

public class MainPage extends Application {
    public static void inisialisasi()
    {
        Player player1 = Player.createPlayer(100);
        Player player2 = Player.createPlayer(100);
        new CardManager();
        for (int i = 1; i <= 3; i++)
        {
            player1.getDeck().addCardToHand(CardManager.getCard("animal","Beruang"));
            player1.getDeck().addCardToHand(CardManager.getCard("plant","Biji Jagung"));
            player1.getDeck().addCardToHand(CardManager.getCard("item","Accelerate"));
            player2.getDeck().addCardToHand(CardManager.getCard("animal","Ayam"));
            player2.getDeck().addCardToHand(CardManager.getCard("plant","Biji Labu"));
            player2.getDeck().addCardToHand(CardManager.getCard("item","Accelerate"));
        }

        // debug ladang player
        for (int i = 0; i < 5; i++)
        {
            for (int j = 0; j < 4; j++)
            {
                if (i % 2 == 0)
                {
                    player1.addCardToLadang(CardManager.getCard("animal", "Ayam"), i, j);
                }
                else
                {
                    player2.addCardToLadang(CardManager.getCard("animal","Sapi"), i, j);
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