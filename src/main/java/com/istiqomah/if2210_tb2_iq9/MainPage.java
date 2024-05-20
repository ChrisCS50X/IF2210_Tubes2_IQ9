package com.istiqomah.if2210_tb2_iq9;

import com.istiqomah.if2210_tb2_iq9.model.card.*;
import com.istiqomah.if2210_tb2_iq9.model.player.Player;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.Objects;

public class MainPage extends Application {
    public static void inisialisasi()
    {
        Player player1 = Player.createPlayer(100);
        Player player2 = Player.createPlayer(100);
        CardManager cardManager = CardManager.getInstance();
        Animal animal = (Animal) cardManager.getCard("animal", 1);
        Plant plant = (Plant) cardManager.getCard("plant", 1);
        System.out.println(animal.getType());
        System.out.println(plant.getType());

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