package com.istiqomah.if2210_tb2_iq9;

import com.istiqomah.if2210_tb2_iq9.model.card.*;
import com.istiqomah.if2210_tb2_iq9.model.cardcollection.Deck;
import com.istiqomah.if2210_tb2_iq9.model.player.Player;
import com.istiqomah.if2210_tb2_iq9.model.toko.Toko;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.List;
import java.util.Objects;
import java.util.Random;

public class MainPage extends Application {
    public static Toko toko;
    private static Stage primaryStage;
    public static void inisialisasi()
    {
        new CardManager();
        toko = new Toko ();
        Random rand = new Random();
        Player player1 = Player.createPlayer(0);
        Player player2 = Player.createPlayer(0);

        System.out.println("MainPage: Create player done...");

        player1.getDeck().initMainDeck();
        player2.getDeck().initMainDeck();

        System.out.println("MainPage: Player init main deck done...");
        System.out.println("Player add to hand done...");
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        MainPage.primaryStage = primaryStage;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/newMain.fxml"));
        Parent root = loader.load();
        root.getStylesheets().add(getClass().getResource("css/main.css").toExternalForm());
        primaryStage.setTitle("Tugas Besar 2 IF2210 - IQ9");
        primaryStage.setScene(new Scene(root, 850, 830));
        primaryStage.show();
        MainPageController mainPageController = loader.getController();

        // Load plugins
        String pluginDir = "plugins"; // Update with the correct path
        PluginLoader.loadPlugins(pluginDir);

        // Set the available formats in the controller
        mainPageController.updateAvailableFormats();

        FXMLLoader shuffleLoader = new FXMLLoader(getClass().getResource("fxml/shuffle-view.fxml"));
        StackPane view = shuffleLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Shuffle View");
        stage.setScene(new Scene(view, 450, 430));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.showAndWait();

        mainPageController.setDeckAktifPlayer();
    }

    public static void main(String[] args)
    {
        inisialisasi();
        launch(args);
    }

    public static void closeApplication() {
        primaryStage.close();
    }
}