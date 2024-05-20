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
        CardManager cardManager = CardManager.getInstance();
        for (int i = 1; i <= 3; i++)
        {
            player1.getDeck().addCardToHand(cardManager.getCard("animal",i));
            player1.getDeck().addCardToHand(cardManager.getCard("plant",i));
            player1.getDeck().addCardToHand(cardManager.getCard("item",i));
            player2.getDeck().addCardToHand(cardManager.getCard("animal",i+1));
            player2.getDeck().addCardToHand(cardManager.getCard("plant",i+1));
            player2.getDeck().addCardToHand(cardManager.getCard("item",i+1));
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