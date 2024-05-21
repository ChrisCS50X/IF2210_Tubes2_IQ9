package com.istiqomah.if2210_tb2_iq9;

import com.istiqomah.if2210_tb2_iq9.model.card.*;
import com.istiqomah.if2210_tb2_iq9.model.toko.Toko;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class TokoPage extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/Toko.fxml"));
            Parent root = loader.load();
            new CardManager();

            Toko toko = new Toko();
            Card card1 = CardManager.getCard("product","Stroberi");
            Card card2 = CardManager.getCard("product","Susu");
            Card card3 = CardManager.getCard("product", "Daging Domba");
            Card card4 = CardManager.getCard("product", "Telur");
            Card card5 = CardManager.getCard("product","Sirip Hiu");
            toko.addProduct(card1);
            toko.addProduct(card2);
            toko.addProduct(card3);
            toko.addProduct(card4);
            toko.addProduct(card5);
            toko.addProduct(card1);

            TokoController controller = loader.getController();
            controller.setToko(toko);

            primaryStage.setTitle("Toko Test");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}