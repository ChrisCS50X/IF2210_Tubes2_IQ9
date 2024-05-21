package com.istiqomah.if2210_tb2_iq9;

import com.istiqomah.if2210_tb2_iq9.model.card.*;
import com.istiqomah.if2210_tb2_iq9.model.toko.Toko;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class CardTokoPage extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/CardToko.fxml"));
            Parent root = loader.load();
            new CardManager();

            Toko toko = new Toko();
            Card card = CardManager.getCard("product","Daging Domba");
            toko.addProduct(card);

            CardTokoController controller = loader.getController();
            controller.setToko(toko);
            controller.setCardToko(card);

            primaryStage.setTitle("Card Toko Test");
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